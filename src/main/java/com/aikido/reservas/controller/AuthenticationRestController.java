
package com.aikido.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aikido.reservas.entity.Usuario;
import com.aikido.reservas.model.AuthenticateUser;
import com.aikido.reservas.model.TokenData;
import com.aikido.reservas.repository.UsuarioRepository;
import com.aikido.reservas.utils.Encryptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("reservas")
@CrossOrigin(origins = { "http://localhost:4200","http://localhost:8080", "https://aikido-grados.herokuapp.com" }, methods = {
		RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PATCH })
public class AuthenticationRestController {
	@Autowired
	private UsuarioRepository authenticationRepository;

	@Autowired
	private Encryptor encryptor;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<TokenData> authenticate(@RequestBody AuthenticateUser user) {
		log.info("Autenticamos usuario:{} con pasword {} ", user.getEmail(), user.getPassword());
		Usuario foundUser = authenticationRepository.findByEmail(user.getEmail());
		if (foundUser!=null){
			log.info("Found: " + foundUser.getEmail());
			if(encryptor.decrypt(foundUser.getPassword()).equals(user.getPassword())){
				TokenData tokenData = new TokenData();
				tokenData.setId(foundUser.getEmail());
				tokenData.setName(foundUser.getNombre());
				tokenData.setToken("Token");
				return new ResponseEntity<>(tokenData, HttpStatus.OK);
			}else {
				log.info("Password password bd:  '{}' recibida;  '{}'",encryptor.decrypt(foundUser.getPassword()),user.getPassword() );
			}
		} 
		log.info("No se ha encontrado al usuario");
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@PostMapping(value = "/register")
	public ResponseEntity<Usuario> register(@RequestBody Usuario user) {
		Usuario newUser = new Usuario();
		newUser.setNombre(user.getNombre());
		newUser.setPassword(encryptor.encrypt(user.getPassword()));
		newUser.setEmail(user.getEmail());
		newUser.setApellido(user.getApellido());
		log.info("Guardamos el usuario '{}' con password '{}'", newUser.getEmail(), newUser.getPassword());
		authenticationRepository.save(newUser);

		Usuario foundUser = authenticationRepository.findByEmail(newUser.getEmail());
		log.info("Found: " + foundUser.getNombre());

		return new ResponseEntity<>(foundUser, HttpStatus.OK);

	}

}
