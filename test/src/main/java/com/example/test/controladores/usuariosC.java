package com.example.test.controladores;

import com.example.test.modelos.usuarios;
import com.example.test.servicios.personalS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/apiU")
public class usuariosC {
    @Autowired
    private personalS personalS;


    @GetMapping
    public List<usuarios> findAll(){
        return personalS.findAll();
    };

    @GetMapping("/{id_personal}")
    public Optional<usuarios> findById(
            @PathVariable Long id_personal ){
        return personalS.findById(id_personal);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("agregarP")
    public usuarios create (
            @RequestBody usuarios usuarios){
        return personalS.save(usuarios);
    }

    @DeleteMapping("/{id_personal}")
    public void deleteById(@PathVariable Long id_personal) {
        personalS.deleteById(id_personal);
    }

    @PutMapping("/{id_personal}")
    public usuarios update(@PathVariable Long id_personal, @RequestBody usuarios usuarios) {
        // Verificar si el usuario existe
        Optional<usuarios> existingUsuario = personalS.findById(id_personal);

        if (existingUsuario.isPresent()) {
            usuarios.setId_personal(id_personal);
            return personalS.save(usuarios);
        } else {
            // Manejar el caso en que el usuario no existe
            return null;
        }
    }
}
