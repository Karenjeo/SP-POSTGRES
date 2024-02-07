package com.example.test.servicios;

import com.example.test.modelos.bloque;
import com.example.test.repositorios.bloqueR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class bloqueS {
    @Autowired
    public bloqueR bloqueR;

    public List<bloque> findAll(){
        return bloqueR.findAll();
    };
    public Optional<bloque> findById(Long id_bloque){
        return  bloqueR.findById(id_bloque);
    }
    public bloque save(bloque bloque) {
        return  bloqueR.save(bloque);
    }
    public void deleteById(Long id_bloque) {
        bloqueR.deleteById(id_bloque);
    }
    public bloque update(bloque bloque) {
        // Verificar si el usuario ya existe en la base de datos
        Optional<bloque> existingUCond = bloqueR.findById(bloque.getId_bloque());
        if (existingUCond.isPresent()) {
            // Actualizar los campos del usuario existente
            existingUCond.get().setNombre(bloque.getNombre());
            existingUCond.get().setNumeroPisos(bloque.getNumeroPisos());
            // Guardar y devolver el usuario actualizado
            return bloqueR.save(existingUCond.get());
        } else {
            // Manejar el caso en que el usuario no existe
            // Puedes lanzar una excepción, devolver null, o manejarlo de otra manera según tus necesidades
            return null;
        }
    }
}
