
package com.aikido.reservas.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aikido.reservas.entity.Usuario;
import com.aikido.reservas.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = { "http://localhost:4200","http://localhost:8080", "https://aikido-grados.herokuapp.com" }, methods = {
		RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PATCH })
public class UserRestController {
	@Autowired
	private UsuarioRepository userRepository;


	@RequestMapping(value = "/findByEmail", method = RequestMethod.GET)
	public ResponseEntity<Usuario> findByEmail(@RequestParam String email) {
		Usuario foundUser = userRepository.findByEmail(email);
		if(foundUser==null) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(foundUser, HttpStatus.OK);
		}
	}

	

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAll() {
		Iterable<Usuario> foundUser = userRepository.findAll();
		if(foundUser.iterator().hasNext()) {
			List<Usuario> resultado= new ArrayList<Usuario> ();
			foundUser.iterator().forEachRemaining(s->resultado.add(s));
			return new ResponseEntity<>(resultado, HttpStatus.OK);

		}else {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);

		}
	}

	
}
