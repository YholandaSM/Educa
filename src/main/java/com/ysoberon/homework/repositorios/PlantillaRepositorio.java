package com.ysoberon.homework.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysoberon.homework.modelo.Alumno;
import com.ysoberon.homework.modelo.Plantilla;
import com.ysoberon.homework.modelo.Usuario;

 

public interface PlantillaRepositorio extends JpaRepository<Plantilla, Integer> {
	
	  List<Plantilla> findByUsuario(Usuario usuario);

	  Optional<Plantilla> findById(Integer id);
}
