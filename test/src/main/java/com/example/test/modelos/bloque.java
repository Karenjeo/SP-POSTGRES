package com.example.test.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bloque")
public class bloque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_bloque;

     private String nombre;


    private int numeroPisos;

}

