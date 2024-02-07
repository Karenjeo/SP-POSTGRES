package com.example.test.servicios;

import com.example.test.modelos.mantenimiento;
import com.example.test.repositorios.mantenimientoR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class mantenimientoS {
    @Autowired
    public mantenimientoR mantenimientoR;

    public List<mantenimiento> findAll(){
        return mantenimientoR.findAll();
    };
    public Optional<mantenimiento> findById(Long id_mantenimiento){
        return  mantenimientoR.findById(id_mantenimiento);
    }
    public mantenimiento save(mantenimiento mantenimiento) {
        return  mantenimientoR.save(mantenimiento);
    }
    public void deleteById(Long id_mantenimiento) {
        mantenimientoR.deleteById(id_mantenimiento);
    }
    public mantenimiento update(mantenimiento mantenimiento) {
        // Verificar si el usuario ya existe en la base de datos
        Optional<mantenimiento> existingMan = mantenimientoR.findById(mantenimiento.getId_mantenimiento());
        if (existingMan.isPresent()) {
            // Actualizar los campos del usuario existente
            existingMan.get().setDescripcion(mantenimiento.getDescripcion());
            existingMan.get().setCosto(mantenimiento.getCosto());
            existingMan.get().setFecha_solicitud(mantenimiento.getFecha_solicitud());
            existingMan.get().setEstado_mantenimeinto(mantenimiento.getEstado_mantenimeinto());
            // Guardar y devolver el usuario actualizado
            return mantenimientoR.save(existingMan.get());
        } else {
            // Manejar el caso en que el usuario no existe
            // Puedes lanzar una excepción, devolver null, o manejarlo de otra manera según tus necesidades
            return null;
        }
    }
}
