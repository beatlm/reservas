package com.aikido.reservas.model;

import org.springframework.data.annotation.Id;

public class TokenData {
	@Id
	private String id;

	private String name;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
