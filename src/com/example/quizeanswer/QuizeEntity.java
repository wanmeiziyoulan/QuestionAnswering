package com.example.quizeanswer;

import java.io.Serializable;

public class QuizeEntity implements Serializable{
	 
	/**��������ID**/
	private int quizeId;
	/**��������**/
	private String quizeType;
	/**������Ŀ**/
	private String quizeTitle;
	/**�����������**/
	private String quizeContent;
	private String quizeTime;
	public int getQuizeId() {
		return quizeId;
	}
	public void setQuizeId(int quizeId) {
		this.quizeId = quizeId;
	}
	public String getQuizeTitle() {
		return quizeTitle;
	}
	public void setQuizeTitle(String quizeTitle) {
		this.quizeTitle = quizeTitle;
	}
	public String getQuizeContent() {
		return quizeContent;
	}
	public void setQuizeContent(String quizeContent) {
		this.quizeContent = quizeContent;
	}
	public String getQuizeTime() {
		return quizeTime;
	}
	public void setQuizeTime(String quizeTime) {
		this.quizeTime = quizeTime;
	}
	public String getQuizeType() {
		return quizeType;
	}
	public void setQuizeType(String quizeType) {
		this.quizeType = quizeType;
	}

	

	

}

