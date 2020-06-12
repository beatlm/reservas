package com.aikido.reservas.model;

import org.springframework.data.annotation.Id;

import lombok.Data;
@Data
public class TokenData {
	@Id
	private String id;

	private String name;
	private String token;
	
}
