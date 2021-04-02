package com.ysoberon.homework.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysoberon.homework.modelo.Categoria;
import com.ysoberon.homework.repositorios.CategoriaRepositorio;

@Service
public class CategoriaServicioimpl implements ICategoriaServicio {
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;

	@Override
	public List<Categoria> buscarTodas() {
		// TODO Auto-generated method stub
		return categoriaRepositorio.findAll();
	}
	
	 

}
