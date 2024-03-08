package com.example.test.controladores;


import com.example.test.modelos.apartamento;
import com.example.test.servicios.apartamentoS;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/apiA")
public class apartamentoC {
    @Autowired
    private apartamentoS apartamentoS;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public List<apartamento> findAll(){
        return apartamentoS.findAll();
    };

    @GetMapping("/{id_apartamento}")
    public Optional<apartamento> findById(
            @PathVariable Long id_apartamento ){
        return apartamentoS.findById(id_apartamento);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("agregarA")
    public apartamento create(@Valid @RequestBody apartamento apartamento, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Manejar errores de validación
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos de apartamento no válidos", new ValidationException("Validation Failed"));
        }

        try {
            return apartamentoS.save(apartamento);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el apartamento", e);
        }
    }



    @DeleteMapping("/{id_apartamento}")
    public void deleteById(@PathVariable Long id_apartamento) {
        apartamentoS.deleteById(id_apartamento);
    }
    //http://localhost:8080/apiR/2
    @PutMapping("/{id_apartamento}")
    public apartamento update(@PathVariable Long id_apartamento, @RequestBody apartamento apartamento) {
        // Verificar si el usuario existe
        Optional<apartamento> existingUsuario = apartamentoS.findById(id_apartamento);

        if (existingUsuario.isPresent()) {
            apartamento.setId_apartamento(id_apartamento);
            return apartamentoS.save(apartamento);
        } else {
            // Manejar el caso en que el rol no existe
            return null;
        }
    }
}
