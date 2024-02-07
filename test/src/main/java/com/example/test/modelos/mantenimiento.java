package com.example.test.modelos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mantenimiento")
public class mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mantenimiento;

    private String descripcion;
    private double costo;
    private String fecha_solicitud;
    private String estado_mantenimeinto;


    @ManyToOne(optional = false)
    @JoinColumn(name="id_personal", nullable = false,
            referencedColumnName = "id_personal")
    private usuarios usuarios;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_condominio", nullable = false,
            referencedColumnName = "id_condominio")
    private condominio condominio;



}