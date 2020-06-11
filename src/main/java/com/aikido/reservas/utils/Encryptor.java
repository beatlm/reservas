package com.aikido.reservas.utils;

import java.util.Base64;

import org.jasypt.util.text.BasicTextEncryptor;

import lombok.extern.slf4j.Slf4j;

/**
 * Encryptor.java
 * 
 * @author Vector ITC Group
 *
 */
@Slf4j
public class Encryptor {

	private BasicTextEncryptor basicTextEncryptor = null;

	public Encryptor(String encryptorPassword) {
		this.basicTextEncryptor = new BasicTextEncryptor();
		this.basicTextEncryptor.setPassword(encryptorPassword);
	}

	public String decrypt(String message) {
		log.info("Desencriptando '{}'", message);
		return new String(basicTextEncryptor.decrypt(new String(Base64.getDecoder().decode(message))));
	}

	public String encrypt(String pass) {
		return new String(Base64.getEncoder().encode(basicTextEncryptor.encrypt(pass).getBytes()));
	}

}
