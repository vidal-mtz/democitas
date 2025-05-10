package com.consultorio.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.consultorio.demo.dto.ConsultorioDto;
import com.consultorio.demo.dto.DoctorDto;
import com.consultorio.demo.service.ProcesaCita;

@Controller
@RequestMapping("/")
public class CitaController {

	private ProcesaCita procesa;

	public CitaController(ProcesaCita procesa) {
		this.procesa = procesa;
	}

	
	@GetMapping
	public ModelAndView listCita() {
		ModelAndView modelo = new ModelAndView("index");
		
		List<DoctorDto> listDoctores = procesa.getTodosDoctor();
		List<ConsultorioDto> listConsultorios = procesa.getTodosConsultorio();
		
		modelo.addObject("doctores", listDoctores);
		modelo.addObject("consultorios", listConsultorios);
		return modelo;
	}
}
