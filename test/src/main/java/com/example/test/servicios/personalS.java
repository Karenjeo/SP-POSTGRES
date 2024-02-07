package com.example.test.servicios;

import com.example.test.modelos.usuarios;
import com.example.test.repositorios.personalR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class personalS {
    @Autowired
    public personalR personalR;
    public List<usuarios> findAll(){
        return personalR.findAll();
    };
    public Optional<usuarios> findById(Long id_usuario){
        return  personalR.findById(id_usuario);
    }
    public usuarios save(usuarios usuarios) {
        return  personalR.save(usuarios);
    }
    public void deleteById(Long id_usuario) {
        personalR.deleteById(id_usuario);
    }
    public usuarios update(usuarios usuarios) {
        // Verificar si el usuario ya existe en la base de datos
        Optional<usuarios> existingUsuario = personalR.findById(usuarios.getId_personal());

        if (existingUsuario.isPresent()) {
            // Actualizar los campos del usuario existente
            existingUsuario.get().setNombre(usuarios.getNombre());
            existingUsuario.get().setTelefono(usuarios.getTelefono());
            existingUsuario.get().setCorreo_electronico(usuarios.getCorreo_electronico());
            existingUsuario.get().setDireccion(usuarios.getDireccion());
            existingUsuario.get().setRol(usuarios.getRol());
            // Guardar y devolver el usuario actualizado
            return personalR.save(existingUsuario.get());
        } else {
            // Manejar el caso en que el usuario no existe
            // Puedes lanzar una excepción, devolver null, o manejarlo de otra manera según tus necesidades
            return null;
        }
    }


}
