package com.aikido.reservas.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;

import lombok.Data;
@Data
public class Reserva {
	@Id
	private String profesor;

	private LocalDate dia;
	
	private LocalTime hora;
	
	private int plazasTotales;
	
 
	

}
