package com.aikido.reservas.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;
@Data
public class Usuario {
	@Id
	private String email;
	
	private String password;

	private String nombre;
	
	private String apellido;
	
	private List<Clase> reservas;
	

}
