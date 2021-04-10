package com.ysoberon.homework.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysoberon.homework.modelo.Alumno;
import com.ysoberon.homework.modelo.Ejercicio;
import com.ysoberon.homework.modelo.Plantilla;

 

public interface EjercicioRepositorio extends JpaRepository<Ejercicio, Integer>{
	  List<Ejercicio> findByNombre(String nombre);
	  List<Ejercicio> findByPlantilla(Plantilla plantilla);
}
