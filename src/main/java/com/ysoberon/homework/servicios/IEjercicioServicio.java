package com.ysoberon.homework.servicios;

import java.util.List;

import com.ysoberon.homework.modelo.Ejercicio;

public interface IEjercicioServicio {
	
	void guardarEjercicio(Ejercicio ejercicio);
	public List<Ejercicio> findEjercicioByNombre(String  nombre);

}
