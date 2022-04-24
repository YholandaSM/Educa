package com.ysoberon.homework.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysoberon.homework.modelo.Alumno;
import com.ysoberon.homework.modelo.Examen;
import com.ysoberon.homework.modelo.Plantilla;
import com.ysoberon.homework.repositorios.ExamenRepositorio;

@Service
public class ExamenServicio implements IExamenServicio {
	
	@Autowired
	private ExamenRepositorio repoExamen;

	@Override
	public List<Examen> findByAlumnoAndPlantilla(Alumno alumno, Plantilla plantilla) {
		// TODO Auto-generated method stub
		return repoExamen.findByAlumnoAndPlantilla(alumno, plantilla);
	}

	@Override
	public void guardarExamen(Examen examen) {
		repoExamen.save(examen);
		
	}

}
