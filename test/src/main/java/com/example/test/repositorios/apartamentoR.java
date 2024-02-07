package com.example.test.repositorios;

import com.example.test.modelos.apartamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface apartamentoR extends JpaRepository<apartamento,Long> {
}
