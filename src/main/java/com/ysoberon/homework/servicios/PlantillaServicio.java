package com.ysoberon.homework.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysoberon.homework.modelo.Plantilla;
import com.ysoberon.homework.modelo.Usuario;
import com.ysoberon.homework.repositorios.PlantillaRepositorio;

@Service
public class PlantillaServicio implements IPlantillaServicio {
	
	@Autowired
	private PlantillaRepositorio plantillaRepositorio;

	@Override
	public void guardar(Plantilla plantilla) {
		plantillaRepositorio.save(plantilla);
		
	}

	@Override
	public List<Plantilla> findPlantillasByUsuario(Usuario usuario) {
		 
		return plantillaRepositorio.findByUsuario(usuario);
	}

	@Override
	public void eliminarPlantilla(Integer idPlantilla) {
		plantillaRepositorio.deleteById(idPlantilla);
		
	}

	@Override
	public Plantilla findById(Integer idPlantilla) {
		Optional<Plantilla> optional = plantillaRepositorio.findById(idPlantilla);

		if (optional != null) {

			return optional.get();
		}

		return null;
	}

	@Override
	public List<Plantilla> findPlantillasByNombre(String nombre) {
		// TODO Auto-generated method stub
		return plantillaRepositorio.findByNombre(nombre);
	}

	/*@Override
	public List<Plantilla> findPlantillasByAlumno(Integer idAlumno) {
		// TODO Auto-generated method stub
		return plantillaRepositorio.findPlantillasByAlumno(idAlumno);
	}
*/
}
