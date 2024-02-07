package com.example.test.repositorios;

import com.example.test.modelos.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface personalR extends JpaRepository<usuarios,Long> {
}
