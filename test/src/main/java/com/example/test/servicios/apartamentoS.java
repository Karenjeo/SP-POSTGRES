package com.example.test.servicios;

import com.example.test.modelos.apartamento;
import com.example.test.repositorios.apartamentoR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class apartamentoS {
    @Autowired
    public apartamentoR apartamentoR;

    public List<apartamento> findAll(){
        return apartamentoR.findAll();
    };
    public Optional<apartamento> findById(Long id_bloque){
        return  apartamentoR.findById(id_bloque);
    }
    public apartamento save(apartamento condominio) {
        return  apartamentoR.save(condominio);
    }
    public void deleteById(Long id_apartamento) {
        apartamentoR.deleteById(id_apartamento);
    }
    public apartamento update(apartamento apartamento) {
        // Verificar si el usuario ya existe en la base de datos
        Optional<apartamento> existingUCond = apartamentoR.findById(apartamento.getId_apartamento());
        if (existingUCond.isPresent()) {
            // Actualizar los campos del usuario existente
            existingUCond.get().setNumero_bano(apartamento.getNumero_bano());
            existingUCond.get().setNumeroHabitaciones(apartamento.getNumeroHabitaciones());
            existingUCond.get().setMetros_cudrados(apartamento.getMetros_cudrados());
            // Guardar y devolver el usuario actualizado
            return apartamentoR.save(existingUCond.get());
        } else {
            // Manejar el caso en que el usuario no existe
            // Puedes lanzar una excepción, devolver null, o manejarlo de otra manera según tus necesidades
            return null;
        }
    }
}
