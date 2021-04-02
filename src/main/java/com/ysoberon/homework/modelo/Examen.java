package com.ysoberon.homework.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "examen")
public class Examen {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_examen;
	private Double nota;
	private Date fecha;
	@ManyToOne
	@JoinColumn(name="id_plantilla") 
	private Plantilla plantilla;
	@ManyToOne
	@JoinColumn(name="id_alumno") 
	private Alumno alumno;
	
	public Examen() {
		super();
	}
	public Integer getId_examen() {
		return id_examen;
	}
	public void setId_examen(Integer id_examen) {
		this.id_examen = id_examen;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Plantilla getPlantilla() {
		return plantilla;
	}
	public void setPlantilla(Plantilla plantilla) {
		this.plantilla = plantilla;
	}
	
	
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	@Override
	public String toString() {
		return "Examen [id_examen=" + id_examen + ", nota=" + nota + ", fecha=" + fecha + ", plantilla=" + plantilla
				+ "]";
	}
	
	
	

}
