package com.ysoberon.homework.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysoberon.homework.modelo.Alumno;
import com.ysoberon.homework.modelo.Ejercicio;
import com.ysoberon.homework.modelo.Plantilla;
import com.ysoberon.homework.repositorios.EjercicioRepositorio;

 


@Service
public class EjercicioServicio implements IEjercicioServicio {
	
	@Autowired
	private EjercicioRepositorio repoEjercicio;

	@Override
	public Ejercicio guardarEjercicio(Ejercicio ejercicio) {
		return repoEjercicio.save(ejercicio);

	}

	@Override
	public  List<Ejercicio>  findEjercicioByNombre(String nombre) {
		// TODO Auto-generated method stub
		return repoEjercicio.findByNombre(nombre);
	}

	@Override
	public List<Ejercicio> findEjerciciosByPlantilla(Plantilla plantilla) {
		// TODO Auto-generated method stub
		return repoEjercicio.findByPlantilla(plantilla);
	}

	@Override
	public Optional<Ejercicio> findEjercicioById(Integer id_ejercicio) {
		return repoEjercicio.findById(id_ejercicio);
	}

	@Override
	public void eliminarEjercicio(Integer idEjercicio) {
		repoEjercicio.deleteById(idEjercicio);
		
	}


}
