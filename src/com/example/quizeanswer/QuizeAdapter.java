package com.example.quizeanswer;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizeAdapter extends BaseAdapter  {

	private ArrayList<QuizeEntity> quizeList=new ArrayList<QuizeEntity>();
	private Context context;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	
	public QuizeAdapter(Context context,ArrayList<QuizeEntity> quizelist){
		this.context=context;
		this.quizeList=quizelist;
		 mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	}
	
	/**
	 * 定义元素
	 * @author Administrator
	 *
	 */
	public final class ViewHolder{
		TextView title;
		TextView type;
		TextView content;
		ImageView pic;
		public TextView starId;
		public TextView time;
	}
	
	/**
	 * 返回item的个数
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return quizeList.size();
	}

	
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return quizeList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		QuizeEntity quize=quizeList.get(position);
		if(convertView==null){
			holder=new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.activity_quize_item, null);
			holder.title=(TextView)convertView.findViewById(R.id.qtitle);
			holder.content=(TextView)convertView.findViewById(R.id.qcontent);
//			holder.type=(TextView)convertView.findViewById(R.id.qbutton);
			holder.pic=(ImageView)convertView.findViewById(R.id.qimg);
			holder.time=(TextView)convertView.findViewById(R.id.updateTime);
			convertView.setTag(holder);
		}else
		{
			holder = (ViewHolder)convertView.getTag();  
		}
		
		holder.title.setText(quize.getQuizeTitle());
		holder.content.setText(quize.getQuizeContent());
		holder.time.setText(quize.getQuizeTime());
//		holder.starId.setText(quize.getStarId()+"");
//		holder.pic.setText(quize.getContent_pic());
		return convertView;
	}

}
