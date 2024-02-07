package com.example.test.inicializacion;

import com.example.test.modelos.rol;
import com.example.test.modelos.usuarios;
import com.example.test.servicios.personalS;
import com.example.test.servicios.rolS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component

public class usuario implements CommandLineRunner {
    @Autowired
    private personalS personalS;
    @Autowired
    private rolS rolS;

    @Override
    public void run(String... args) throws Exception {
         if (personalS.findById(1L).orElse(null) == null && personalS.findById(2L).orElse(null) == null) {
             usuarios personal1 = new usuarios();
            personal1.setNombre("Juan");
            personal1.setCedula(2350675787L);
            personal1.setContra("1234");
            personal1.setTelefono("0987654321");
            personal1.setCorreo_electronico("admin@espe.com");
            personal1.setDireccion("Quito");
            personal1.setFechaNacimiento("2022-01-01");

             rol rolUsuario = rolS.findById(2L).orElse(null);

            if (rolUsuario != null && rolUsuario.getEstado()) {
                 personal1.setRol(rolUsuario);

                 personalS.save(personal1);
            } else {

            }
        }
    }
}

