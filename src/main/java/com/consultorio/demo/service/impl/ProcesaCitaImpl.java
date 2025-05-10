package com.consultorio.demo.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.consultorio.demo.dto.CitaDto;
import com.consultorio.demo.dto.ConsultorioDto;
import com.consultorio.demo.dto.DoctorDto;
import com.consultorio.demo.dto.ReporteCitaDto;
import com.consultorio.demo.entity.Cita;
import com.consultorio.demo.entity.Consultorio;
import com.consultorio.demo.entity.Doctor;
import com.consultorio.demo.repository.CitaRepository;
import com.consultorio.demo.repository.ConsultorioRepository;
import com.consultorio.demo.repository.DoctorRepository;
import com.consultorio.demo.service.ProcesaCita;

@Service
@Transactional(readOnly = false)
public class ProcesaCitaImpl implements ProcesaCita {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcesaCitaImpl.class);
	private static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final DateTimeFormatter FORMATTER_TIME = DateTimeFormatter.ofPattern("HH:mm");

	private ConsultorioRepository repConsultorio;
	private DoctorRepository repDoctor;
	private CitaRepository repCita;

	public ProcesaCitaImpl(ConsultorioRepository repConsultorio, DoctorRepository repDoctor, CitaRepository repCita) {
		this.repConsultorio = repConsultorio;
		this.repDoctor = repDoctor;
		this.repCita = repCita;
	}

	@Override
	public String altaCita(CitaDto data) {
		LOGGER.info("cita -> {}", data);

		Optional<Consultorio> searchConsultorio = repConsultorio.findById(data.getConsultorio());
		Optional<Doctor> searchDoctor = repDoctor.findById(data.getDoctor());

		if (!searchConsultorio.isPresent())
			return "No se encontro el consultorio";
		if (!searchDoctor.isPresent())
			return "No se encontro el Doctor";

		Consultorio dataConsultorio = searchConsultorio.get();
		Doctor dataDoctor = searchDoctor.get();

		LocalDate fecha = LocalDate.parse(data.getDia(), FORMATTER_DATE);
		LocalTime hora = LocalTime.parse(data.getHora(), FORMATTER_TIME);

		Cita cita = new Cita();
		cita.setConsultorio(dataConsultorio);
		cita.setDoctor(dataDoctor);
		cita.setHorario(LocalDateTime.of(fecha, hora));
		cita.setDuracion(data.getDuracion());
		cita.setPaciente(data.getPaciente());

		// validaciones
		// No se puede agendar cita en un mismo consultorio a la misma hora.
		if (validaCita(dataConsultorio, cita.getHorario(), cita.getDuracion()))
			return "El horario no esta diponible en el consultorio";

		// No se puede agendar cita para un mismo Dr. a la misma hora.
		if (validaCita(dataDoctor, cita.getHorario(), cita.getDuracion()))
			return "El Doctor no esta disponible en el horario";

		// No se puede agendar cita para un paciente a la una misma hora ni con menos de
		// 2 horas de diferencia para el mismo día.
		if (validaCita(cita.getPaciente(), cita.getHorario(), 60))
			return "El paciente no puede tener citas tan cercanas";

		// Un mismo doctor no puede tener más de 8 citas en el día.
		if (validaCita(dataDoctor, fecha))
			return "El Doctor ya no se puede tener mas citas";

		repCita.saveAndFlush(cita);

		return "Creación de la cita de forma correcta";
	}

	private boolean validaCita(Doctor dataDoctor, LocalDate fecha) {
		List<Cita> lista = repCita.findByDoctorAndHorario(dataDoctor, LocalDateTime.of(fecha, LocalTime.of(0, 0)),
				LocalDateTime.of(fecha, LocalTime.of(23, 59)));

		LOGGER.info("Data por Doctor {}", lista);

		return !lista.isEmpty();
	}

	private boolean validaCita(String paciente, LocalDateTime horario, int duracion) {
		List<Cita> lista = repCita.findByPacienteAndHorario(paciente, horario, horario.plusMinutes(duracion - 1L));

		LOGGER.info("Data por Paciente {}", lista);

		return !lista.isEmpty();
	}

	private boolean validaCita(Consultorio dataConsultorio, LocalDateTime horario, int duracion) {
		List<Cita> lista = repCita.findByConsultorioAndHorario(dataConsultorio, horario,
				horario.plusMinutes(duracion - 1L));

		LOGGER.info("Data por consultorio {}", lista);

		return !lista.isEmpty();
	}

	private boolean validaCita(Doctor dataDoctor, LocalDateTime horario, int duracion) {
		List<Cita> lista = repCita.findByDoctorAndHorario(dataDoctor, horario, horario.plusMinutes(duracion - 1L));

		LOGGER.info("Data por Doctor {}", lista);

		return !lista.isEmpty();
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
			result.add(new ConsultorioDto(tmp.getNumero(), tmp.getNumero() + " en " + tmp.getPiso()));

		return result;
	}

	@Override
	public List<String> getHorarios() {
		int periodoMin = 30;

		List<String> result = new ArrayList<>();
		LocalTime horaInicial = LocalTime.of(8, 0);
		LocalTime horaFinal = LocalTime.of(20, 0);

		while (horaInicial.isBefore(horaFinal)) {
			result.add(horaInicial.toString());
			horaInicial = horaInicial.plusMinutes(periodoMin);
		}

		return result;
	}

	@Override
	public List<String> getDuracion() {
		List<String> result = new ArrayList<>();
		result.add("30");
		result.add("40");
		result.add("50");
		result.add("60");
		result.add("90");
		result.add("120");
		return result;
	}

	@Override
	public LocalDate getFecha(String fechaStr) {
		LocalDate result = null;

		try {
			result = LocalDate.parse(fechaStr, FORMATTER_DATE);
		} catch (Exception ex) {
			result = LocalDate.now();
		}

		return result;
	}

	public List<ReporteCitaDto> convertirCitas(List<Cita> lista) {
		LOGGER.info("Registros encontrados {}", lista.size());

		List<ReporteCitaDto> result = new ArrayList<>();
		for (Cita tmp : lista)
			result.add(new ReporteCitaDto(tmp.getCitaId(), //
					tmp.getConsultorio().getNumero() + " en " + tmp.getConsultorio().getPiso(), //
					tmp.getDoctor().getNombre() + " " + tmp.getDoctor().getApPaterno() + " - "
							+ tmp.getDoctor().getEspecialidad(), //
					tmp.getHorario() + " a " + tmp.getHorario().toLocalTime().plusMinutes(tmp.getDuracion()), //
					tmp.getPaciente()));

		return result;
	}

	@Override
	public List<ReporteCitaDto> getConsultaCitas(LocalDate fechaBus) {
		LocalDateTime fInicial = LocalDateTime.of(fechaBus, LocalTime.of(0, 0));
		LocalDateTime fFinal = LocalDateTime.of(fechaBus, LocalTime.of(23, 59));

		LOGGER.info("Buscando en el rango {} {}", fInicial, fFinal);

		return convertirCitas(repCita.findByHorario(fInicial, fFinal));
	}

	@Override
	public List<ReporteCitaDto> getConsultaCitas(long doctorId, LocalDate fechaBus) {
		return convertirCitas(repCita.findByDoctorIdAndHorario(doctorId, LocalDateTime.of(fechaBus, LocalTime.of(0, 0)),
				LocalDateTime.of(fechaBus, LocalTime.of(23, 59))));
	}

}
