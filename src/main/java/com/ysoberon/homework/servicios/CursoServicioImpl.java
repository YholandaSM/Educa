package com.ysoberon.homework.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysoberon.homework.modelo.Curso;
import com.ysoberon.homework.repositorios.CategoriaRepositorio;
import com.ysoberon.homework.repositorios.CursoRepositorio;


@Service
public class CursoServicioImpl implements ICursoServicio {
	
	@Autowired
	private CursoRepositorio cursoRepositorio;

	@Override
	public List<Curso> buscarTodos() {
		// TODO Auto-generated method stub
		return cursoRepositorio.findAll();
	}

}
