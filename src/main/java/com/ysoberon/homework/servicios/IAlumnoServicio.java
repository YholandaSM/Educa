package com.ysoberon.homework.servicios;

import java.util.List;

import com.ysoberon.homework.modelo.Alumno;
import com.ysoberon.homework.modelo.Usuario;

 

public interface IAlumnoServicio {
	
	void guardarAlumno(Alumno alumno);
	public List<Alumno> findAlumnosByUsuario(Usuario usuario);
	void eliminarAlumno(Integer idAlumno);
	Alumno findById (Integer idAlumno);

}
