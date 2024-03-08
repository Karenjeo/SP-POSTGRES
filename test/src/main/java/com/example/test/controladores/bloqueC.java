package com.example.test.controladores;

import com.example.test.modelos.bloque;
import com.example.test.servicios.bloqueS;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
    public bloque create(@RequestBody bloque bloque) {
        try {
            if (bloque.getNumeroPisos() < 0) {
                throw new com.example.proyectou2.excepciones.InvalidPisosException("numeroPisos no puede ser negativo");
            }
            return bloqueS.save(bloque);
        } catch (com.example.proyectou2.excepciones.InvalidPisosException e) {
             rabbitTemplate.convertAndSend(com.example.proyectou2.config.RabbitMQConfigBinding.DEAD_LETTER_EXCHANGE, "dead_letter", bloque);
            throw e;
        }
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
