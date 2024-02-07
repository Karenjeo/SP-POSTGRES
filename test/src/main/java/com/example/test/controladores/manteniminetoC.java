package com.example.test.controladores;

import com.example.test.modelos.mantenimiento;
import com.example.test.servicios.mantenimientoS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(path = "/apiM")
public class manteniminetoC {
    @Autowired
    private mantenimientoS mantenimientoS;
    //http://localhost:8080/apiM
    @GetMapping
    public List<mantenimiento> findAll(){
        return mantenimientoS.findAll();
    };
//http://localhost:8080/apiM/3
    @GetMapping("/{id_mantenimiento}")
    public Optional<mantenimiento> findById(
            @PathVariable Long id_mantenimiento ){
        return mantenimientoS.findById(id_mantenimiento);
    }
    //http://localhost:8080/apiM/agregarM
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("agregarM")
    public mantenimiento create (
            @RequestBody mantenimiento mantenimiento ){
        return mantenimientoS.save(mantenimiento);
    }
//
    @DeleteMapping("/{id_mantenimiento}")
    public void deleteById(@PathVariable Long id_mantenimiento) {
        mantenimientoS.deleteById(id_mantenimiento);
    }
    //http://localhost:8080/apiM/3
    @PutMapping("/{id_mantenimiento}")
    public mantenimiento update(@PathVariable Long id_mantenimiento, @RequestBody mantenimiento mantenimiento) {
        // Verificar si el usuario existe
        Optional<mantenimiento> existingUsuario = mantenimientoS.findById(id_mantenimiento);

        if (existingUsuario.isPresent()) {
            mantenimiento.setId_mantenimiento(id_mantenimiento);
            return mantenimientoS.save(mantenimiento);
        } else {
            // Manejar el caso en que el rol no existe
            return null;
        }
    }
}
