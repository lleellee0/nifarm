package com.my.sketchbook;

import com.google.gson.Gson;

public class MessageBean {
	private String message;
	private String type;
	private String to;
		
	public static MessageBean convertMessage(String source) {
			
		MessageBean message = new MessageBean();
		Gson gson = new Gson();
			
		message = gson.fromJson(source, MessageBean.class);
			
		return message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
