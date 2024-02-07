package com.example.test.servicios;

import com.example.test.modelos.rol;
import com.example.test.repositorios.rolR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class rolS {
    @Autowired
    public rolR rolR;

    public List<rol> findAll(){
        return rolR.findAll();
    };
    public Optional<rol> findById(Long id_rol){
        return  rolR.findById(id_rol);
    }
    public rol save(rol rol) {
        return  rolR.save(rol);
    }
    public void deleteById(Long id_rol) {
        rolR.deleteById(id_rol);
    }
    public rol update(rol rol) {
        // Verificar si el usuario ya existe en la base de datos
        Optional<rol> existingURol = rolR.findById(rol.getId_rol());
        if (existingURol.isPresent()) {
            // Actualizar los campos del usuario existente
            existingURol.get().setEstado(rol.getEstado());
            // Guardar y devolver el usuario actualizado
            return rolR.save(existingURol.get());
        } else {
            // Manejar el caso en que el usuario no existe
            // Puedes lanzar una excepción, devolver null, o manejarlo de otra manera según tus necesidades
            return null;
        }
    }

}
