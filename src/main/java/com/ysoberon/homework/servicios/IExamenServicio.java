package com.ysoberon.homework.servicios;

import java.util.List;

import com.ysoberon.homework.modelo.Alumno;
import com.ysoberon.homework.modelo.Examen;
import com.ysoberon.homework.modelo.Plantilla;

public interface IExamenServicio {
	
	public List<Examen> findByAlumnoAndPlantilla(Alumno alumno,Plantilla plantilla);
	void guardarExamen(Examen examen);

}
