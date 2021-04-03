package com.ysoberon.homework.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.PostMapping;

@Entity
@Table(name = "alumno")
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_alumno;
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	private String nombre;
	private Integer edad;
	private String imagen;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "alumno_plantilla", joinColumns = @JoinColumn(name = "id_alumno"), inverseJoinColumns = @JoinColumn(name = "id_plantilla"))	 
	private List<Plantilla> plantillas;

	public Alumno() {
		super();
	}
   /**
    * 
    * @param plantilla
    */
 
	public void agregarPlantilla(Plantilla plantilla) {
		if (plantillas == null) {
			plantillas = new ArrayList<Plantilla>();
		}

		plantillas.add(plantilla);

	}

	public Integer getId_alumno() {
		return id_alumno;
	}

	public void setId_alumno(Integer id_alumno) {
		this.id_alumno = id_alumno;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<Plantilla> getPlantillas() {
		return plantillas;
	}

	public void setPlantillas(List<Plantilla> plantillas) {
		this.plantillas = plantillas;
	}
	
	

	@Override
	public String toString() {
		return "Alumno [id_alumno=" + id_alumno + ", usuario=" + usuario + ", nombre=" + nombre + ", edad=" + edad
				+ ", imagen=" + imagen + ", getId_alumno()=" + getId_alumno() + ", getUsuario()=" + getUsuario()
				+ ", getNombre()=" + getNombre() + ", getEdad()=" + getEdad() + ", getImagen()=" + getImagen()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
