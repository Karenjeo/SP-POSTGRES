package com.example.test.controladores;

import com.example.test.modelos.bloque;
import com.example.test.servicios.bloqueS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/apiB")
public class bloqueC {
    @Autowired
    private bloqueS bloqueS;

    @GetMapping
    public List<bloque> findAll(){
        return bloqueS.findAll();
    };

    @GetMapping("/{id_bloque}")
    public Optional<bloque> findById(
            @PathVariable Long id_bloque ){
        return bloqueS.findById(id_bloque);
    }
    //http://localhost:8080/apiR/agregarR
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("agregarB")
    public bloque create (
            @RequestBody bloque bloque ){
        return bloqueS.save(bloque);
    }

    @DeleteMapping("/{id_bloque}")
    public void deleteById(@PathVariable Long id_bloque) {
        bloqueS.deleteById(id_bloque);
    }
    //http://localhost:8080/apiR/2
    @PutMapping("/{id_bloque}")
    public bloque update(@PathVariable Long id_bloque, @RequestBody bloque bloque) {
        // Verificar si el usuario existe
        Optional<bloque> existingUsuario = bloqueS.findById(id_bloque);

        if (existingUsuario.isPresent()) {
            bloque.setId_bloque(id_bloque);
            return bloqueS.save(bloque);
        } else {
            // Manejar el caso en que el rol no existe
            return null;
        }
    }
}
