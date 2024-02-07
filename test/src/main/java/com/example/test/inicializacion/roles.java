package com.example.test.inicializacion;

import com.example.test.modelos.rol;
import com.example.test.servicios.rolS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class roles implements CommandLineRunner {
    @Autowired
    private rolS rolS;

    @Override
    public void run(String... args) throws Exception {
        // Verificar si ya existen roles por defecto en la base de datos
        if (rolS.findById(1L).orElse(null) == null && rolS.findById(2L).orElse(null) == null) {
            // Si no existen los roles, crearlos por defecto
            rol rol1 = new rol();
            rol1.setNombreRol("personal-Limpieza");
            rol1.setDescripcion("Descripción del rol de personal de limpieza");
            rol1.setFechaCreacion("2022-01-01"); // Asigna la fecha que desees
            rol1.setTipo("limpieza");
            rol1.setEstado(true);

            rol rol2 = new rol();
            rol2.setNombreRol("Admin");
             rol2.setDescripcion("Descripción del rol de administrador");
            rol2.setFechaCreacion("2022-01-01");
            rol2.setTipo("administrador");
            rol2.setEstado(true);

            rol rol3 = new rol();
            rol3.setNombreRol("Usuario");
            rol3.setDescripcion("Usuario para compras");
            rol3.setFechaCreacion("2022-01-01");
            rol3.setTipo("usuario");
            rol3.setEstado(true);

            rolS.save(rol1);
            rolS.save(rol2);
            rolS.save(rol3);
        }
    }
}
