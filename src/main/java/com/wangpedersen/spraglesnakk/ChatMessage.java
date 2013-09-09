package com.wangpedersen.spraglesnakk;

import java.io.Serializable;

public class ChatMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;
	private String message;

	public ChatMessage(String message) {
		setMessage(message);
	}
	
	public ChatMessage(String username, String message) {
		setUsername(username);
		setMessage(message);
	}
	
	public ChatMessage() {
		
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
