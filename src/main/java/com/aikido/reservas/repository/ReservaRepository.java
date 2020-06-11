
package com.aikido.reservas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aikido.reservas.entity.Reserva;


public interface ReservaRepository extends CrudRepository<Reserva,String> {
	
	public List<Reserva> findByProfesor(String profesor);
	@Query("select b from Book b " +
            "where b.from between ?1 and ?2 and b.to between ?1 and ?2")
	public List<Reserva> findByDiasBetween(LocalDate from, LocalDate to);
	

	public List<Reserva> findAll();

}
