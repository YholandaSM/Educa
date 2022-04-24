package com.ysoberon.homework.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysoberon.homework.modelo.Tipo;
import com.ysoberon.homework.repositorios.TipoRepositorio;

@Service
public class TipoServicioImpl implements ITipoServicio{
	
	@Autowired
	private TipoRepositorio tipoRepositorio;

	@Override
	public List<Tipo> buscarTodos() {
		// TODO Auto-generated method stub
		return tipoRepositorio.findAll();
	}

}
