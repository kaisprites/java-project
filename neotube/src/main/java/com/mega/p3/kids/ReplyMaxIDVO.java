package com.mega.p3.kids;

public class ReplyMaxIDVO {
	@Override
	public String toString() {
		return "ReplyMaxIDVO [reply_id=" + reply_id + ", category=" + category + "]";
	}
	String reply_id;
	String category;
	public String getReply_id() {
		return reply_id;
	}
	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
