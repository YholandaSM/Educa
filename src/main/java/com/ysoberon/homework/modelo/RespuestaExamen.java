package com.ysoberon.homework.modelo;

import java.util.List;

import javax.persistence.Entity;


public class RespuestaExamen {
	
	private Ejercicio ejercicio;
	private Respuesta respuesta;
	private Respuesta respuestaCorrecta;
	private Boolean correcta;
	
	public Ejercicio getEjercicio() {
		return ejercicio;
	}
	
	public void setEjercicio(Ejercicio ejercicio) {
		this.ejercicio = ejercicio;
	}
	
	public Respuesta getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	public Respuesta getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	public void setRespuestaCorrecta(Respuesta respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}

	public Boolean getCorrecta() {
		return correcta;
	}
	
	public void setCorrecta(Boolean correcta) {
		this.correcta= correcta;
	}
	
	
	
	
	
}
