package com.consultorio.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.consultorio.demo.dto.CitaDto;
import com.consultorio.demo.dto.ConsultorioDto;
import com.consultorio.demo.dto.DoctorDto;
import com.consultorio.demo.entity.Consultorio;
import com.consultorio.demo.entity.Doctor;
import com.consultorio.demo.repository.CitaRepository;
import com.consultorio.demo.repository.ConsultorioRepository;
import com.consultorio.demo.repository.DoctorRepository;
import com.consultorio.demo.service.ProcesaCita;

@Service
public class ProcesaCitaImpl implements ProcesaCita {

	private ConsultorioRepository repConsultorio;
	private DoctorRepository repDoctor;
	private CitaRepository repCita;

	public ProcesaCitaImpl(ConsultorioRepository repConsultorio, DoctorRepository repDoctor, CitaRepository repCita) {
		this.repConsultorio = repConsultorio;
		this.repDoctor = repDoctor;
		this.repCita = repCita;
	}

	@Override
	public String altaCita(CitaDto cita) {

		return null;
	}

	@Override
	public List<DoctorDto> getTodosDoctor() {
		List<Doctor> lista = repDoctor.findAll();

		List<DoctorDto> result = new ArrayList<>();
		for (Doctor tmp : lista)
			result.add(new DoctorDto(tmp.getId(), tmp.getNombre() + " " + tmp.getApPaterno()));

		return result;
	}

	@Override
	public List<ConsultorioDto> getTodosConsultorio() {
		List<Consultorio> lista = repConsultorio.findAll();

		List<ConsultorioDto> result = new ArrayList<>();
		for (Consultorio tmp : lista)
			result.add(new ConsultorioDto(tmp.getNumero(), tmp.getNumero() + " piso " + tmp.getPiso()));

		return result;
	}

}
