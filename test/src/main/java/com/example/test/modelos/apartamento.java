package com.example.test.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "apartamento")
public class apartamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_apartamento;


    private String numero_bano;

    private int numeroHabitaciones;


    private double metros_cudrados;

    private byte[] imagen;

    private String estado;


    @ManyToOne(optional = false)
    @JoinColumn(name="id_condominio", nullable = false,
            referencedColumnName = "id_condominio")
    private condominio condominio;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_bloque", nullable = false,
            referencedColumnName = "id_bloque")
    private bloque bloque;

}
