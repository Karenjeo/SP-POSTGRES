package com.example.test.repositorios;

import com.example.test.modelos.mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface mantenimientoR extends JpaRepository<mantenimiento,Long> {
}
