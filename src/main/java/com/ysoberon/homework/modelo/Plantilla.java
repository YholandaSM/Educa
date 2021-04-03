package com.ysoberon.homework.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "plantilla")
public class Plantilla {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_plantilla;

	// private Categoria categoria;
	private String nombre;
	private String detalles;
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private Categoria categoria;
	@ManyToOne
	@JoinColumn(name="id_curso") 
	private Curso curso;
	@ManyToOne
	@JoinColumn(name="id_usuario") 
	private Usuario usuario;

	public Plantilla() {
		super();
	}

	public Plantilla(Categoria categoria, String nombre, String detalles) {
		super();

		this.nombre = nombre;
		this.detalles = detalles;
	}

	public Integer getId_plantilla() {
		return id_plantilla;
	}

	public void setId_plantilla(Integer id_plantilla) {
		this.id_plantilla = id_plantilla;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return "Plantilla [id_plantilla=" + id_plantilla + ", nombre=" + nombre + ", detalles=" + detalles
				+ ", categoria=" + categoria + ", curso=" + curso + ", usuario=" + usuario + "]";
	}

}
