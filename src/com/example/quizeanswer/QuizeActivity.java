package com.example.quizeanswer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pulltorefreshtest.RefreshableView;
import com.example.pulltorefreshtest.RefreshableView.PullToRefreshListener;
import com.quize.web.Constant;
import com.quize.web.SendAnsThread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class QuizeActivity extends Activity {

	private ListView ansListView;
	private ArrayList<AnsEntity> anslist=new ArrayList<AnsEntity>();
	private AnsAdapter ansadapter;
	
	private AnsEntity example;
	private int value1;
	private String quizeurl;
	private Button post;
	private TextView quizeTitle;
	private String quizecontent;
	private String TEMP_INFO;
	private String quizetitle;
	private EditText ansContent;
	private String quizetype;
	private RefreshableView refreshableView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		 Intent intent = getIntent();  
	        //从Intent当中根据key取得value  
		 Bundle bun= intent.getExtras();
		 value1=bun.getInt("quizeId"); 	     
	     System.out.println("value1的值为="+value1);	     
	  //   quizeurl="http://115.29.151.149:8051/api/json/quizeId=1";
	     quizeurl="http://115.29.151.149:8051/api/json/quizeId="+value1;

	     setContentView(R.layout.activity_answer_list);
	     iniData();
	     initView();
	     SharedPreferences sp = getSharedPreferences(TEMP_INFO, MODE_WORLD_READABLE);  
	     //从SharedPreferences获得数据  
	     String content = sp.getString("info_content", "");	    
			
		/**refreshableView.setOnRefreshListener(new PullToRefreshListener() {
				@Override
				public void onRefresh() {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					refreshableView.finishRefreshing();
				}
		}, 0);*/
		 new MyTask().execute(quizeurl);
		 
		 
		post.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				/**anscontent.clearFocus();
				anscontent.setFocusable(false);*/
				//页面跳转，上传answer数据
				String content=ansContent.getText().toString();	
				ansContent.clearFocus();
				ansContent.setFocusable(false);
				AnsEntity answer=new AnsEntity();
				answer.setAnswerContent(content);
				answer.setQuizeId(value1);
				
				new SendAnsThread(answer,"http://115.29.151.149:8051/api/json/answer");
			    QuizeActivity.this.finish();
			
			}
		});
	     
	}

	private void initView() {
		// TODO Auto-generated method stub
		ansListView=(ListView)findViewById(R.id.anslist);
//		refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);		
		post=(Button)findViewById(R.id.post);
		quizeTitle=(TextView)findViewById(R.id.quizetitle);
		ansContent=(EditText)findViewById(R.id.anscontent);
		//ansTime=(TextView)findViewById(R.id.anstime);
	}

	private void iniData() {
		// TODO Auto-generated method stub
		
		AnsEntity example=new AnsEntity();
		example.setAnswerContent("我是答案");
		anslist.add(example);
		anslist.add(example);
		anslist.add(example);
		anslist.add(example);
	}

	/**
	 * MyTask继承线程池AsyncTask用来网络数据请求、json解析、数据更新等操作。
	 */
	class MyTask extends AsyncTask<String, Void, String> {
		//private CharSequence quizetitle;

		/**
		 * 数据请求前显示dialog。
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		/**
		 * 在doInBackground方法中，做一些诸如网络请求等耗时操作。
		 */
		@Override
		protected String doInBackground(String... params) {
			return RequestData();
		}

		/**
		 * 在该方法中，主要进行一些数据的处理，更新。
		 */
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (result != null) {
				// 如果获取的result数据不为空，那么对其进行JSON解析。并显示在手机屏幕上。
				try {
					anslist = JSONAnalysis(result);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				quizeTitle.setText(quizetitle);				
				ansadapter=new AnsAdapter(QuizeActivity.this,anslist);
				ansListView.setAdapter(ansadapter);
				ansadapter.notifyDataSetChanged();
			} else if (result == null) {
				Toast.makeText(QuizeActivity.this, "请求数据失败...",
						Toast.LENGTH_LONG).show();
			}
		
		}
	}
	
	/**
	 * 网络数据请求
	 * @return
	 */
	public String RequestData() {
		System.out.println("RequestData前");
		HttpGet get = new HttpGet(quizeurl);
		HttpClient client = new DefaultHttpClient();
		StringBuilder builder = null;
		try {
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				System.out.println("StatusCode"+response.getStatusLine().getStatusCode());
				InputStream inputStream = response.getEntity().getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream));
				builder = new StringBuilder();
				String s = null;
				for (s = reader.readLine(); s != null; s = reader.readLine()) {
					builder.append(s);
					System.out.println("RequestData中");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	/**
	 * JSON解析
	 * @param result
	 * @return
	 * @throws JSONException 
	 */
	public ArrayList<AnsEntity> JSONAnalysis(String result) throws JSONException {
		anslist.clear();
		System.out.println("quizeanswer"+result);
		JSONObject item=new JSONObject(result);
		quizecontent=item.getString("quizeContent");
		quizetitle=item.getString("quizeTitle");
		System.out.println(quizetitle);
		quizetype=item.getString("quizeType");
		System.out.println("quizecontent"+quizecontent);
		JSONArray array=item.getJSONArray("quizeAnswer");
		if(array.length()>0){
			for(int i = 0; i < array.length(); i++){
				JSONObject ansitem=array.getJSONObject(i);
				AnsEntity example=new AnsEntity();
				String content=ansitem.getString("answerContent");
				String time=ansitem.getString("answerTime");
				int answerid=ansitem.getInt("answerId");
				example.setAnswerContent(content);
				example.setAnswerTime(time);
				example.setAnswerId(answerid);
				anslist.add(example);
			}			
		}

	
		return anslist;
	}
	public void setBack(View view){
		QuizeActivity.this.finish();
		/*Intent intent = new Intent();
        intent.setClass(QuizeActivity.this, MainActivity.class);
        startActivity(intent);*/
	}
	

//	public void newEdit(View view){
//		finish();
//		Intent intent = new Intent();
//        intent.setClass(QuizeActivity.this, AskActivity.class);
//        startActivity(intent);
//	}
//	public void onStop() {  
//        super.onStop();  
//        
//        SharedPreferences.Editor editor = getSharedPreferences(TEMP_INFO, MODE_WORLD_WRITEABLE).edit();           
//        editor.putString("info_content", example.getAnswerContent().toString());         
//        editor.commit();  
//    } 

}
