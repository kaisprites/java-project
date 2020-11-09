package com.mega.p3.kids;

public class UserControlVO {
	String video_id;
	String user_id;
	public String getVideo_id() {
		return video_id;
	}
	@Override
	public String toString() {
		return "UserControlVO [video_id=" + video_id + ", user_id=" + user_id + "]";
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
