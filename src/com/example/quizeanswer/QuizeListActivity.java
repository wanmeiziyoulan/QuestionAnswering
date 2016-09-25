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

import com.example.quizeanswer.QuizeActivity.MyTask;
import com.quize.web.SendDataThread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class QuizeListActivity extends Activity {

	private ArrayList<QuizeEntity> quizeList=new ArrayList <QuizeEntity>();
	private QuizeAdapter quizeadapter;
	private ListView quizeListView;
	private EditText etsearch;
	private String searchQuizeUrl="http://115.29.151.149:8051/api/json/searchQuize";
	private QuizeEntity example1;
	private Button askButton;	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quize_list);
        
        initData();
        initView();
        initItemListener();
        initAskListener();
        new MyTask().execute(searchQuizeUrl);
    }
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//    	// TODO Auto-generated method stub
//        super.onListItemClick(l, v, position, id);
//        l.getItemAtPosition(position);
//        QuizeEntity selobj=quizeList.get(position);
//		int quizeId=selobj.getQuizeId();
//		
//		System.out.println("quizeId="+quizeId);
//		Intent intent = new Intent();
//		intent.putExtra("quizeId", quizeId);
//		
//        intent.setClass(QuizeListActivity.this, QuizeActivity.class);
//        startActivity(intent);
//    }
    private void initItemListener() {
  	// TODO Auto-generated method stub
		quizeListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				QuizeEntity selobj=quizeList.get(arg2);
				int quizeId=selobj.getQuizeId();
				
				System.out.println("quizeId+++"+quizeId);
				Intent intent = new Intent();
				intent.putExtra("quizeId", quizeId);
				
	            intent.setClass(QuizeListActivity.this, QuizeActivity.class);
	            startActivity(intent);
			}
			
		});
	}
    private void initAskListener() {
  	// TODO Auto-generated method stub
    	askButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//	new MyThread().start();
				ShowConnectDialog();
			}		
			
		});
	}
	private void initView() {
		// TODO Auto-generated method stub
		quizeListView=(ListView)findViewById(R.id.quizels);
		askButton=(Button)findViewById(R.id.askbtn);

	}
	private void initData() {
		// TODO Auto-generated method stub
		QuizeEntity example=new QuizeEntity();
		
		example.setQuizeTitle("我是问题1的标题");
		example.setQuizeContent("我是问题1的内容");
		example.setQuizeTime("");
		quizeList.add(example);
	}
//	class MyThread extends Thread{
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			super.run();
//			ShowConnectDialog();
//		}
//	}
	private void ShowConnectDialog() {
		// TODO Auto-generated method stub	 
		 	/**	final LinearLayout loginLayout1 = (LinearLayout) context.getLayoutInflater().
		 	 * inflate(R.layout.dialog, (ViewGroup )context.findViewById (R.id.dialog_ll)); **/
				
		 LinearLayout loginLayout1 = (LinearLayout) this.getLayoutInflater().inflate(  
		               R.layout.dialog, null); 
		 		  // adView.  
		 
				  final TextView title = (TextView) loginLayout1.findViewById(R.id.title);
				  title.setText("请输入");
				  final EditText  dTitle= (EditText) loginLayout1.findViewById(R.id.dtitle); 
//				  final ImageView  dimg= (ImageView) loginLayout1.findViewById(R.id.dimg);  
			      final EditText dContent = (EditText) loginLayout1.findViewById(R.id.dcontent);  
				  AlertDialog.Builder builder = new AlertDialog.Builder(this);
				  builder.setView(loginLayout1);
				  builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					  @Override
					  public void onClick(DialogInterface dialog, int which) {
				      //处理确定按钮
						    System.out.println("Dialog");
							String Title=dTitle.getText().toString();
							System.out.println("Dialog");
							String Content=dContent.getText().toString();
						//	String type=popbutton.getText().toString();
							
							QuizeEntity quize=new QuizeEntity();
							quize.setQuizeContent(Content);
							quize.setQuizeTitle(Title);
							new SendDataThread(quize,"http://115.29.151.149:8051/api/json/quize");
					  }
				  });				  
				  builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					  @Override
					  public void onClick(DialogInterface dialog, int which) {
						  // 处理取消按钮						   
					  }
				  });
				  builder.create().show();
	 }
	/**
	 * MyTask继承线程池AsyncTask用来网络数据请求、json解析、数据更新等操作。
	 */
	class MyTask extends AsyncTask<String, Void, String> {
		private CharSequence quizetitle;
		private TextView quizeTitle;

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
					quizeList = JSONAnalysis(result);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				quizeTime.setText(quizecontent);
//				quizeContent.setText(quizecontent);
//				quizeTitle.setText(quizetitle);
				quizeadapter=new QuizeAdapter(QuizeListActivity.this,quizeList);
				quizeListView.setAdapter(quizeadapter);
				quizeadapter.notifyDataSetChanged();
			} else if (result == null) {
				Toast.makeText(QuizeListActivity.this, "请求数据失败...",
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
		HttpGet get = new HttpGet(searchQuizeUrl);
		HttpClient client = new DefaultHttpClient();
		StringBuilder builder = null;
		try {
			HttpResponse response = client.execute(get);
			System.out.println("response");
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
	public ArrayList<QuizeEntity> JSONAnalysis(String result) throws JSONException {
		quizeList.clear();
		System.out.println("quize"+result);
		JSONArray array=new JSONArray(result);	
		if(array.length()>0){
			for(int i = 0; i < array.length(); i++){
				JSONObject quizeitem=array.getJSONObject(i);
				QuizeEntity example1=new QuizeEntity();
				String title=quizeitem.getString("quizeTitle");
				String content=quizeitem.getString("quizeContent");
				String time=quizeitem.getString("quizeTime");
				int quizeid=quizeitem.getInt("quizeId");
				example1.setQuizeTitle(title);
				example1.setQuizeContent(content);
				example1.setQuizeTime(time);
				example1.setQuizeId(quizeid);
				quizeList.add(example1);
			}
			
		}	
		return quizeList;
	}
	public void setBack(View view){
		QuizeListActivity.this.finish();
//		Intent intent = new Intent();
//        intent.setClass(QuizeListActivity.this, MainActivity.class);
//        startActivity(intent);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
