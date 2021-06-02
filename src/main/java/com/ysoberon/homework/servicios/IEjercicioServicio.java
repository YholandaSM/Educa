package com.ysoberon.homework.servicios;

import java.util.List;
import java.util.Optional;

import com.ysoberon.homework.modelo.Ejercicio;
import com.ysoberon.homework.modelo.Plantilla;

public interface IEjercicioServicio {
	
	Ejercicio guardarEjercicio(Ejercicio ejercicio);
	public Optional<Ejercicio> findEjercicioById(Integer  id_ejercicio);
	public List<Ejercicio> findEjercicioByNombre(String  nombre);
	public List<Ejercicio> findEjerciciosByPlantilla(Plantilla plantilla);
	void eliminarEjercicio(Integer idEjercicio);

}
