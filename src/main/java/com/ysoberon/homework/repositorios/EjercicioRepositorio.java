package com.ysoberon.homework.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysoberon.homework.modelo.Alumno;
import com.ysoberon.homework.modelo.Ejercicio;

 

public interface EjercicioRepositorio extends JpaRepository<Ejercicio, Integer>{
	  List<Ejercicio> findByNombre(String nombre);
}
