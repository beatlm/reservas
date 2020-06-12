
package com.aikido.reservas.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("reservas/api")
@CrossOrigin(origins = { "http://localhost:4200","http://localhost:8080", "https://aikido-grados.herokuapp.com" }, methods = {
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


}