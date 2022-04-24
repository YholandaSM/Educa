package com.ysoberon.homework.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "respuesta")
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_respuesta;

	private String enunciado;

	private boolean correcta;

	/*@JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID", insertable = false, updatable = false)
	private Integer id_ejercicio;*/

	@ManyToOne
	@JoinColumn(name = "id_ejercicio")
	private Ejercicio ejercicio;

	public Respuesta() {
		super();
	}

	public boolean getCorrecta() {
		return correcta;
	}

	public Integer getId_respuesta() {
		return id_respuesta;
	}

	public void setId_respuesta(Integer id_respuesta) {
		this.id_respuesta = id_respuesta;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public boolean isCorrecta() {
		return correcta;
	}

	public void setCorrecta(boolean correcta) {
		this.correcta = correcta;
	}

	public Ejercicio getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(Ejercicio ejercicio) {
		this.ejercicio = ejercicio;
	}

}
