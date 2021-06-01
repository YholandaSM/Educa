package com.ysoberon.homework.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysoberon.homework.modelo.Alumno;
import com.ysoberon.homework.modelo.Examen;
import com.ysoberon.homework.modelo.Plantilla;

 

public interface ExamenRepositorio extends JpaRepository<Examen, Integer>{
	
	List<Examen> findByAlumnoAndPlantilla(Alumno alumno,Plantilla plantilla);
	 

}
