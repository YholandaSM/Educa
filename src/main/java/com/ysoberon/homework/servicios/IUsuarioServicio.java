package com.ysoberon.homework.servicios;

import com.ysoberon.homework.modelo.Usuario;

public interface IUsuarioServicio {
	
	public void guardar(Usuario usuario);
	Usuario buscarPorEmail(String email);
	

}
