package com.aikido.reservas.repository;

import org.springframework.data.repository.CrudRepository;

import com.aikido.reservas.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

	public Usuario findByNombre(String nombre);
	public Usuario findByEmail(String email);

}
