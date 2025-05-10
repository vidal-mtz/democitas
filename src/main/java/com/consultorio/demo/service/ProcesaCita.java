package com.consultorio.demo.service;

import java.util.List;

import com.consultorio.demo.dto.CitaDto;
import com.consultorio.demo.dto.ConsultorioDto;
import com.consultorio.demo.dto.DoctorDto;

public interface ProcesaCita {
	String altaCita(CitaDto cita);

	List<DoctorDto> getTodosDoctor();

	List<ConsultorioDto> getTodosConsultorio();
}
