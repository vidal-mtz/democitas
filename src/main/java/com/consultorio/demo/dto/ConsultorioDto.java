package com.consultorio.demo.dto;

public class ConsultorioDto {
	private String numero;
	private String descripcion;

	public ConsultorioDto(String numero, String descripcion) {
		super();
		this.numero = numero;
		this.descripcion = descripcion;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
