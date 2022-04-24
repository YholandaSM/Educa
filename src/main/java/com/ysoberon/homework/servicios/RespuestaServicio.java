package com.ysoberon.homework.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysoberon.homework.modelo.Ejercicio;
import com.ysoberon.homework.modelo.Respuesta;
import com.ysoberon.homework.repositorios.AlumnoRepositorio;
import com.ysoberon.homework.repositorios.RespuestaRepositorio;

@Service
public class RespuestaServicio implements IRespuestaServicio {
	
	@Autowired
	private RespuestaRepositorio repoRespuesta;

	@Override
	public void guardarRespuesta(Respuesta respuesta) {
		repoRespuesta.save(respuesta);

	}

	@Override
	public List<Respuesta> findRespuestasByEjercicio(Ejercicio ejercicio) {
		return repoRespuesta.findByEjercicio(ejercicio);
	}

	@Override
	public Optional<Respuesta> findRespuestaById(Integer id_respuesta) {
		return repoRespuesta.findById(id_respuesta);
	}
	
	@Override
	public List<Respuesta> findByCorrectaAndEjercicio(boolean correcta,Ejercicio ejercicio){
		return repoRespuesta.findByCorrectaAndEjercicio(correcta,ejercicio);
	}

/*	@Override
	public List<Respuesta> obtenerRespuestaCorrecta(Boolean correcta) {
		return repoRespuesta.obtenerRespuestaCorrecta(correcta);
	}*/

}
