package com.example.test.modelos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_personal;

     private String nombre;

     private Long  cedula;
     private String contra;


    private String telefono;


    private String correo_electronico;

     private String direccion;

     private String fechaNacimiento;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_rol", nullable = false, referencedColumnName = "id_rol")
    private rol rol;


}
