package com.aikido.reservas.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * EncryptorConfiguration.java
 * 
 * @author Vector ITC Group
 *
 */
@Configuration
public class EncryptorConfiguration {

	@Value("${encryptor.password:nwe2018.}")
	private String encryptorPassword;

	@Bean
	public Encryptor createEncryptor() {
		return new Encryptor(encryptorPassword);
	}

}
