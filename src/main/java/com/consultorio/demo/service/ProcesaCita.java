package com.consultorio.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.consultorio.demo.dto.CitaDto;
import com.consultorio.demo.dto.ConsultorioDto;
import com.consultorio.demo.dto.DoctorDto;
import com.consultorio.demo.dto.ReporteCitaDto;

public interface ProcesaCita {
	String altaCita(CitaDto cita);

	List<String> getHorarios();

	List<String> getDuracion();

	List<DoctorDto> getTodosDoctor();

	List<ConsultorioDto> getTodosConsultorio();

	LocalDate getFecha(String fechaStr);

	List<ReporteCitaDto> getConsultaCitas(LocalDate fechaBus);

	List<ReporteCitaDto> getConsultaCitas(long doctorId, LocalDate fechaBus);
}
