package com.consultorio.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_CONSULTORIO")
public class Consultorio {

	@Id
	@Column(name = "NUM_CONSULTORIO")
	private String numero;

	@Column(name = "PISO")
	private String piso;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Consultorio [numero=");
		builder.append(numero);
		builder.append(", piso=");
		builder.append(piso);
		builder.append("]");
		return builder.toString();
	}
}
