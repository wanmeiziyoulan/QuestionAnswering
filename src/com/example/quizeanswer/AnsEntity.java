package com.example.quizeanswer;

import java.io.Serializable;

public class AnsEntity implements Serializable {

	private int quizeId;
	private String answerTime;
	private String answerContent;
	private int answerId;
	public int getQuizeId() {
		return quizeId;
	}
	public void setQuizeId(int quizeId) {
		this.quizeId = quizeId;
	}
	public String getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerid) {
		this.answerId = answerid;
	}
	
	
}
