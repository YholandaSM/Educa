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
	private String respuestas_ko;
	@ManyToOne
	@JoinColumn(name="id_plantilla")
	private Plantilla plantilla;
	
	
	
	public Ejercicio() {
		super();
	}
	
	
	public Integer getId_ejercicio() {
		return id_ejercicio;
	}
	public void setId_ejercicio(Integer id_ejercicio) {
		this.id_ejercicio = id_ejercicio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	 
	 
	 
 
	public String getRespuesta_ok() {
		return respuesta_ok;
	}
	public void setRespuesta_ok(String respuesta_ok) {
		this.respuesta_ok = respuesta_ok;
	}
	public String getRespuestas_ko() {
		return respuestas_ko;
	}
	public void setRespuestas_ko(String respuestas_ko) {
		this.respuestas_ko = respuestas_ko;
	}
	public Plantilla getPlantilla() {
		return plantilla;
	}
	public void setPlantilla(Plantilla plantilla) {
		this.plantilla = plantilla;
	}
	
	
	
	

}
