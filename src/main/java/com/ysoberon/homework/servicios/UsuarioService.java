package com.ysoberon.homework.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysoberon.homework.modelo.Usuario;
import com.ysoberon.homework.repositorios.UsuarioRepositorio;

 

@Service
public class UsuarioService implements IUsuarioServicio {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public void guardar(Usuario usuario) {
		usuarioRepositorio.save(usuario);
		
	}

	@Override
	public Usuario buscarPorEmail(String email) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.findByEmail(email);
	}

}
