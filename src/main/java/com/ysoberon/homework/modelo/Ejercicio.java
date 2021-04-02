package com.ysoberon.homework.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ejercicio")
public class Ejercicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_ejercicio;
	private String nombre;
	@ManyToOne
	@JoinColumn(name="id_tipo")
	private Tipo tipo;
	private String enunciado;
	private String respuesta_ok;
	private String respuesta_ko;
	@ManyToOne
	@JoinColumn(name="id_plantilla")
	private Plantilla plantilla;
	

}
