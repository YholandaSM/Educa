package com.ysoberon.homework.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysoberon.homework.modelo.Usuario;

 

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
	
	Usuario findByEmail(String email);

}
