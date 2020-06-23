
package com.aikido.reservas.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aikido.reservas.entity.Clase;
import com.aikido.reservas.entity.Usuario;
import com.aikido.reservas.repository.ClaseRepository;
import com.aikido.reservas.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/reservas/api")
@CrossOrigin(origins = { "http://localhost:4200","http://localhost:8080", "https://reservas-aikido.herokuapp.com" }, methods = {
		RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PATCH })
public class ApiRestController {
	@Autowired
	private UsuarioRepository userRepository;
	@Autowired
	private ClaseRepository claseRepository;


	@PostMapping(value="/clase")
	public ResponseEntity<Usuario> crearClase(@RequestBody Clase clase) {
		log.info("Creamos la nueva clase para el día  {} y hora {}", clase.getDia(),clase.getHora());
		Clase newClase= claseRepository.save(clase);
		if(newClase==null) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);

		}else {
			return new ResponseEntity<>(  HttpStatus.CREATED);
		}
	}

	@GetMapping(value = "/usuario/findByEmail")
	public ResponseEntity<Usuario> findByEmail(@RequestParam String email) {
		log.info("Entramos en /findByEmail {} ",email);
		Usuario foundUser = userRepository.findByEmail(email);
		if(foundUser==null) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(foundUser, HttpStatus.OK);
		}
	}



	@GetMapping(value = "/usuario")
	public ResponseEntity<List<Usuario>> findAllUsuarios() {
		log.info("Entramos en findAllUsuarios");;
		Iterable<Usuario> foundUser = userRepository.findAll();
		if(foundUser.iterator().hasNext()) {
			List<Usuario> resultado= new ArrayList<Usuario> ();
			foundUser.iterator().forEachRemaining(s->resultado.add(s));
			return new ResponseEntity<>(resultado, HttpStatus.OK);

		}else {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);

		}
	}
	
	@PostMapping(value="/usuario/{email}/clase")
	public ResponseEntity<Usuario> anadirClase(@PathVariable ("email")  String email, @RequestBody Clase clase) {
		log.info("Creamos la nueva clase para el día  {} y hora {}", clase.getDia(),clase.getHora());
		Usuario usuario=userRepository.findByEmail(email);
		log.info("Añadimos la clase al usuario {}",usuario.getEmail());
		usuario.getReservas().add(clase);
		userRepository.save(usuario);
		return new ResponseEntity<>(  HttpStatus.CREATED);
		
	}
	@DeleteMapping(value="/usuario/{email}/clase/{claseId}")
	public ResponseEntity<Usuario> anadirClase(@PathVariable ("email")  String email, @PathVariable("claseId") String claseId) {
		Usuario usuario=userRepository.findByEmail(email);
		log.info("Eliminamos la clase con id [] al usuario {}",claseId,usuario.getEmail());
		ArrayList<Clase> reservas=usuario.getReservas();
		Iterator<Clase> it=reservas.iterator();
		while(it.hasNext()) {
			Clase actual= (Clase) it.next();
			if(actual.getId().equals(claseId)) {
				it.remove();
			}
		}
		userRepository.save(usuario);
		return new ResponseEntity<>(  HttpStatus.CREATED);
		
	}
	
	@GetMapping(value = "/clase")
	public ResponseEntity<List<Clase>> findAllClases() {
		log.info("Entramos en findAllClases");;
		Iterable<Clase> foundClase = claseRepository.findAll();
		if(foundClase.iterator().hasNext()) {
			List<Clase> resultado= new ArrayList<Clase> ();
			foundClase.iterator().forEachRemaining(s->resultado.add(s));
			return new ResponseEntity<>(resultado, HttpStatus.OK);

		}else {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);

		}
	}
	@GetMapping(value = "/clase/findByProfesor")
	public ResponseEntity<List<Clase>> findClasesByProfesor(@RequestParam String profesor) {
		log.info("Entramos en findClasesByProfesor");;
		Iterable<Clase> foundClase = claseRepository.findByProfesor(profesor);
		if(foundClase.iterator().hasNext()) {
			List<Clase> resultado= new ArrayList<Clase> ();
			foundClase.iterator().forEachRemaining(s->resultado.add(s));
			return new ResponseEntity<>(resultado, HttpStatus.OK);

		}else {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);

		}
	}

}
