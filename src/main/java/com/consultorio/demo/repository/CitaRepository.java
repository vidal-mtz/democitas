package com.consultorio.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.consultorio.demo.entity.Cita;
import com.consultorio.demo.entity.Consultorio;
import com.consultorio.demo.entity.Doctor;

public interface CitaRepository extends JpaRepository<Cita, Long> {

	@Query(value = "SELECT c FROM Cita c WHERE c.consultorio = :consultorio and c.horario between :fechaInicial and :fechaFinal")
	List<Cita> findByConsultorioAndHorario(@Param("consultorio") Consultorio consultorio,
			@Param("fechaInicial") LocalDateTime fInicial, @Param("fechaFinal") LocalDateTime fFinal);

	@Query(value = "SELECT c FROM Cita c WHERE c.doctor = :doctor and c.horario between :fechaInicial and :fechaFinal")
	List<Cita> findByDoctorAndHorario(@Param("doctor") Doctor dataDoctor, @Param("fechaInicial") LocalDateTime fInicial,
			@Param("fechaFinal") LocalDateTime fFinal);

	@Query(value = "SELECT c FROM Cita c WHERE c.paciente = :paciente and c.horario between :fechaInicial and :fechaFinal")
	List<Cita> findByPacienteAndHorario(@Param("paciente") String paciente,
			@Param("fechaInicial") LocalDateTime fInicial, @Param("fechaFinal") LocalDateTime fFinal);

	@Query(value = "SELECT c FROM Cita c WHERE c.horario between :fechaInicial and :fechaFinal")
	List<Cita> findByHorario(@Param("fechaInicial") LocalDateTime fInicial, @Param("fechaFinal") LocalDateTime fFinal);

	@Query(value = "SELECT c FROM Cita c WHERE c.doctor.id = :doctorId and c.horario between :fechaInicial and :fechaFinal")
	List<Cita> findByDoctorIdAndHorario(@Param("doctorId") long doctor, @Param("fechaInicial") LocalDateTime fInicial,
			@Param("fechaFinal") LocalDateTime fFinal);
}
