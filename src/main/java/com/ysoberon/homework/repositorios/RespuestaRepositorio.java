package com.ysoberon.homework.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.ysoberon.homework.modelo.Alumno;
import com.ysoberon.homework.modelo.Ejercicio;
import com.ysoberon.homework.modelo.Plantilla;
import com.ysoberon.homework.modelo.Respuesta;

public interface RespuestaRepositorio extends JpaRepository<Respuesta, Integer>{
	
	 List<Respuesta> findByEjercicio(Ejercicio ejercicio);
	 List<Respuesta> findByCorrectaAndEjercicio(boolean correcta,Ejercicio ejercicio);
}
