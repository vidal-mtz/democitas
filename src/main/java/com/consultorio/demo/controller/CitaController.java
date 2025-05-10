package com.consultorio.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.consultorio.demo.dto.CitaDto;
import com.consultorio.demo.dto.ConsultorioDto;
import com.consultorio.demo.dto.DoctorDto;
import com.consultorio.demo.dto.ReporteCitaDto;
import com.consultorio.demo.service.ProcesaCita;

@Controller
@RequestMapping("/")
public class CitaController {
	private ProcesaCita procesa;

	public CitaController(ProcesaCita procesa) {
		this.procesa = procesa;
	}

	@GetMapping
	public ModelAndView listCita(@RequestParam(defaultValue = "-1") long doctorId, @RequestParam(defaultValue = "") String fechaConsulta) {
		ModelAndView modelo = new ModelAndView("index");

		List<String> listHoras = procesa.getHorarios();
		List<String> listDura = procesa.getDuracion();
		List<DoctorDto> listDoctores = procesa.getTodosDoctor();
		List<ConsultorioDto> listConsultorios = procesa.getTodosConsultorio();

		modelo.addObject("horas", listHoras);
		modelo.addObject("duracion", listDura);
		modelo.addObject("doctores", listDoctores);
		modelo.addObject("consultorios", listConsultorios);

		List<DoctorDto> busDoctor = new ArrayList<>();
		busDoctor.add(new DoctorDto(-1, "Total los doctores"));
		busDoctor.addAll(listDoctores);
		modelo.addObject("busDoctor", busDoctor);

		List<ReporteCitaDto> listCitas = null;

		LocalDate fechaBus = LocalDate.now();
		if (!fechaConsulta.isEmpty())
			fechaBus = procesa.getFecha(fechaConsulta);

		if (doctorId != -1L)
			listCitas = procesa.getConsultaCitas(doctorId, fechaBus);
		else
			listCitas = procesa.getConsultaCitas(fechaBus);

		modelo.addObject("listaCitas", listCitas);

		modelo.addObject("selDoctor", doctorId);
		modelo.addObject("fechaBus", fechaBus.toString());
		
		return modelo;
	}

	@PostMapping("/agregarCita")
	public String agregarCita(@ModelAttribute("citaData") CitaDto data, RedirectAttributes attributes) {

		attributes.addFlashAttribute("message", procesa.altaCita(data));

		return "redirect:/";
	}

}
