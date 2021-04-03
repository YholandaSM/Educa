package com.ysoberon.homework.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;

import com.ysoberon.homework.modelo.Alumno;
import com.ysoberon.homework.modelo.Usuario;
import com.ysoberon.homework.repositorios.AlumnoRepositorio;

@Service
public class AlumnoServicioImpl implements IAlumnoServicio {

	@Autowired
	private AlumnoRepositorio repoAlumno;

	@Override
	public void guardarAlumno(Alumno alumno) {
		repoAlumno.save(alumno);

	}

	@Override
	public List<Alumno> findAlumnosByUsuario(Usuario usuario) {
		return repoAlumno.findByUsuario(usuario);
	}

	@Override
	public void eliminarAlumno(Integer idAlumno) {
		repoAlumno.deleteById(idAlumno);

	}

	@Override
	public Alumno findById(Integer idAlumno) {
		Optional<Alumno> optional = repoAlumno.findById(idAlumno);

		if (optional != null) {

			return optional.get();
		}

		return null;
	}

}
