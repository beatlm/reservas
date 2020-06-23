package com.aikido.reservas.model;

import org.springframework.data.annotation.Id;

import lombok.Data;
@Data
public class TokenData {
	@Id
	private String email;

	private String nombre;
	
	private String apellido;
	
	private String token;
	
}
