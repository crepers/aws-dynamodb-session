package com.crepers12.sample.model;

import java.io.Serializable;

public class User implements Serializable {
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
