package com.ysoberon.homework.modelo;

import java.util.List;

public class RespuestasForm {
	
	private List<Respuesta> respuestas;
	
	private Integer respuestaCorrecta; 

	public Integer getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	public void setRespuestaCorrecta(Integer respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}

	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}
	
	
	

}
