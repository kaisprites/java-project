package com.mega.p3.kids;

public class KidsVOWithChannel extends KidsVO {
	String channel_title;

	public String getChannel_title() {
		return channel_title;
	}

	public void setChannel_title(String channel_title) {
		this.channel_title = channel_title;
	}

	@Override
	public String toString() {
		return "KidsVONamed [channel_title=" + channel_title + ", video_id=" + video_id + ", video_leng=" + video_leng
				+ ", channel_id=" + channel_id + "]";
	}
	
}
