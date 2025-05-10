package com.consultorio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consultorio.demo.entity.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long> {

}
