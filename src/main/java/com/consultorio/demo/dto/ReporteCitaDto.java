package com.consultorio.demo.dto;

public class ReporteCitaDto {
	private Long citaId;

	private String consultorio;
	private String doctor;
	private String horario;
	private String paciente;

	public ReporteCitaDto(Long citaId, String consultorio, String doctor, String horario, String paciente) {
		this.citaId = citaId;
		this.consultorio = consultorio;
		this.doctor = doctor;
		this.horario = horario;
		this.paciente = paciente;
	}

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

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
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
		builder.append("ReporteCitaDto [citaId=");
		builder.append(citaId);
		builder.append(", consultorio=");
		builder.append(consultorio);
		builder.append(", doctor=");
		builder.append(doctor);
		builder.append(", horario=");
		builder.append(horario);
		builder.append(", paciente=");
		builder.append(paciente);
		builder.append("]");
		return builder.toString();
	}
}
