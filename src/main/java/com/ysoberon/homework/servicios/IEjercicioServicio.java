package com.ysoberon.homework.servicios;

import java.util.List;

import com.ysoberon.homework.modelo.Ejercicio;
import com.ysoberon.homework.modelo.Plantilla;

public interface IEjercicioServicio {
	
	void guardarEjercicio(Ejercicio ejercicio);
	public Ejercicio findEjercicioById(Integer  id_ejercicio);
	public List<Ejercicio> findEjercicioByNombre(String  nombre);
	public List<Ejercicio> findEjerciciosByPlantilla(Plantilla plantilla);

}
