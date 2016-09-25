package com.example.quizeanswer;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AnsAdapter extends BaseAdapter{

	private ArrayList<AnsEntity> ansList=new ArrayList<AnsEntity>();
	private Context context;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	
	public AnsAdapter(Context context,ArrayList<AnsEntity> anslist){
		this.context=context;
		this.ansList=anslist;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	}
	
	/**
	 * 定义元素
	 * @author Administrator
	 *
	 */
	public final class ViewHolder{
		TextView userName;
		TextView ansContent;
	    TextView answerTime;
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ansList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return ansList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		AnsEntity  ansentity=ansList.get(position);
		if(convertView==null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.activity_answer_item, null);
			
		//	holder.userName=(TextView)convertView.findViewById(R.id.username);
			holder.ansContent=(TextView)convertView.findViewById(R.id.anscontent);
			holder.answerTime=(TextView)convertView.findViewById(R.id.anstime);
			
		//	holder.userName.setText("用户");
			holder.ansContent.setText(ansentity.getAnswerContent());
			holder.answerTime.setText(ansentity.getAnswerTime());
			
			convertView.setTag(holder);
			
		}
		return convertView;
	}

}
