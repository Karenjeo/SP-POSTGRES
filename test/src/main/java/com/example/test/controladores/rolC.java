package com.example.test.controladores;

import com.example.test.modelos.rol;
import com.example.test.servicios.rolS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/apiR")
public class rolC {
    @Autowired
    private rolS rolS;

    @GetMapping
    public List<rol> findAll(){
        return rolS.findAll();
    };

    @GetMapping("/{id_rol}")
    public Optional<rol> findById(
            @PathVariable Long id_rol ){
        return rolS.findById(id_rol);
    }
//http://localhost:8080/apiR/agregarR
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("agregarR")
    public rol create (
            @RequestBody rol rol ){
        return rolS.save(rol);
    }

    @DeleteMapping("/{id_rol}")
    public void deleteById(@PathVariable Long id_rol) {
        rolS.deleteById(id_rol);
    }
//http://localhost:8080/apiR/2
    @PutMapping("/{id_rol}")
    public rol update(@PathVariable Long id_rol, @RequestBody rol rol) {
        // Verificar si el usuario existe
        Optional<rol> existingUsuario = rolS.findById(id_rol);

        if (existingUsuario.isPresent()) {
            rol.setId_rol(id_rol);
            return rolS.save(rol);
        } else {
            // Manejar el caso en que el rol no existe
            return null;
        }
    }
}
