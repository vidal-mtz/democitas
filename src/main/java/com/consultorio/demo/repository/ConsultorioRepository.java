package com.consultorio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consultorio.demo.entity.Consultorio;

public interface ConsultorioRepository extends JpaRepository<Consultorio, String> {

}
