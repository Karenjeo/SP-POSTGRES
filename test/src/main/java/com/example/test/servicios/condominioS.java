package com.example.test.servicios;

import com.example.test.modelos.condominio;
import com.example.test.repositorios.condominioR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class condominioS {
    @Autowired
    public condominioR condominioR;

    public List<condominio> findAll(){
        return condominioR.findAll();
    };
    public Optional<condominio> findById(Long id_condominio){
        return  condominioR.findById(id_condominio);
    }
    public condominio save(condominio condominio) {
        return  condominioR.save(condominio);
    }
    public void deleteById(Long id_condominio) {
        condominioR.deleteById(id_condominio);
    }
    public condominio update(condominio condominio) {
        // Verificar si el usuario ya existe en la base de datos
        Optional<condominio> existingUCond = condominioR.findById(condominio.getId_condominio());
        if (existingUCond.isPresent()) {
            // Actualizar los campos del usuario existente
            existingUCond.get().setNombre(condominio.getNombre());
            existingUCond.get().setDireccion(condominio.getDireccion());
            existingUCond.get().setCiudad(condominio.getCiudad());
            existingUCond.get().setPrecio(condominio.getPrecio());
            // Guardar y devolver el usuario actualizado
            return condominioR.save(existingUCond.get());
        } else {
            // Manejar el caso en que el usuario no existe
            // Puedes lanzar una excepción, devolver null, o manejarlo de otra manera según tus necesidades
            return null;
        }
    }
}
