package com.aikido.reservas.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;

import lombok.Data;
@Data
public class Clase {
	@Id
	 
	private int id;
	private String profesor;

	private LocalDate dia;

	private LocalTime hora;

	private int plazasTotales;




}
