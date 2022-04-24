package com.ysoberon.homework.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.ysoberon.homework.modelo.Ejercicio;
import com.ysoberon.homework.modelo.Plantilla;
import com.ysoberon.homework.modelo.Respuesta;

public interface IRespuestaServicio {
	
	void guardarRespuesta(Respuesta respuesta);
	public Optional<Respuesta> findRespuestaById(Integer id_ejercicio);
	public List<Respuesta> findRespuestasByEjercicio(Ejercicio ejercicio);
//	public List<Respuesta> obtenerRespuestaCorrecta(Boolean correcta);
	public List<Respuesta> findByCorrectaAndEjercicio(boolean correcta,Ejercicio ejercicio);
	
}
