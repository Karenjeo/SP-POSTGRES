package com.example.test.modelos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "condominios")

public class condominio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_condominio;


    private String nombre;

     private String direccion;

     private String ciudad;

     private double precio;



}
