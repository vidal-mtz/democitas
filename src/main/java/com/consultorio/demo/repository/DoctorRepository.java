package com.consultorio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consultorio.demo.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
