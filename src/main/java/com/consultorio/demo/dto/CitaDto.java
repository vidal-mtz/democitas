package com.consultorio.demo.dto;

public class CitaDto {
	private Long citaId;

	private String consultorio;
	private Long doctor;
	private String dia;
	private String hora;
	private int duracion;
	private String paciente;

	public Long getCitaId() {
		return citaId;
	}

	public void setCitaId(Long citaId) {
		this.citaId = citaId;
	}

	public String getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(String consultorio) {
		this.consultorio = consultorio;
	}

	public Long getDoctor() {
		return doctor;
	}

	public void setDoctor(Long doctor) {
		this.doctor = doctor;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CitaDto [citaId=");
		builder.append(citaId);
		builder.append(", consultorio=");
		builder.append(consultorio);
		builder.append(", doctor=");
		builder.append(doctor);
		builder.append(", dia=");
		builder.append(dia);
		builder.append(", hora=");
		builder.append(hora);
		builder.append(", duracion=");
		builder.append(duracion);
		builder.append(", paciente=");
		builder.append(paciente);
		builder.append("]");
		return builder.toString();
	}
}
