package com.ysoberon.homework.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "curso")
public class Curso {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private   Integer id_curso;
	private String nombre;
	
	
	public Integer getId_curso() {
		return id_curso;
	}


	public void setId_curso(Integer id_curso) {
		this.id_curso = id_curso;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Curso() {
		super();
	}
	
	
	
	
	

}
