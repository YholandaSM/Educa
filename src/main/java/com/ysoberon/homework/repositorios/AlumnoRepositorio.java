package com.ysoberon.homework.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysoberon.homework.modelo.Alumno;
import com.ysoberon.homework.modelo.Plantilla;
import com.ysoberon.homework.modelo.Usuario;

 
public interface AlumnoRepositorio extends JpaRepository<Alumno, Integer> {
	
	  List<Alumno> findByNombre(String nombre);
	  List<Alumno> findByUsuario(Usuario usuario);
	  Optional<Alumno> findById(Integer id);
	

}
