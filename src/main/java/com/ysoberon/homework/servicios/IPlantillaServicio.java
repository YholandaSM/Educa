package com.ysoberon.homework.servicios;

import java.util.List;

import com.ysoberon.homework.modelo.Alumno;
import com.ysoberon.homework.modelo.Plantilla;
import com.ysoberon.homework.modelo.Usuario;

public interface IPlantillaServicio {
	
	public void guardar(Plantilla plantilla);
	public List<Plantilla> findPlantillasByUsuario(Usuario usuario);
	public List<Plantilla> findPlantillasByNombre(String nombre);
	void eliminarPlantilla(Integer idPlantilla);
	Plantilla findById (Integer idPlantilla);
	//List<Plantilla> findPlantillasByAlumno(Integer idAlumno);

}
