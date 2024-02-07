package com.example.test.controladores;

import com.example.test.modelos.condominio;
import com.example.test.servicios.condominioS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(path = "/apiC")
public class condominioC {
    @Autowired
    private condominioS condominioS;

    @GetMapping
    public List<condominio> findAll(){
        return condominioS.findAll();
    };

    @GetMapping("/{id_condominio}")
    public Optional<condominio> findById(
            @PathVariable Long id_condominio ){
        return condominioS.findById(id_condominio);
    }
    //http://localhost:8080/apiR/agregarR
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("agregarC")
    public condominio create (
            @RequestBody condominio condominio ){
        return condominioS.save(condominio);
    }

    @DeleteMapping("/{id_condominio}")
    public void deleteById(@PathVariable Long id_condominio) {
        condominioS.deleteById(id_condominio);
    }
    //http://localhost:8080/apiR/2
    @PutMapping("/{id_condominio}")
    public condominio update(@PathVariable Long id_condominio, @RequestBody condominio condominio) {
        // Verificar si el usuario existe
        Optional<condominio> existingUsuario = condominioS.findById(id_condominio);

        if (existingUsuario.isPresent()) {
            condominio.setId_condominio(id_condominio);
            return condominioS.save(condominio);
        } else {
            // Manejar el caso en que el rol no existe
            return null;
        }
    }
}
