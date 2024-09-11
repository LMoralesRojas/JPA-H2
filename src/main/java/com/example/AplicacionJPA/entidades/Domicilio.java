package com.example.AplicacionJPA.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.io.Serializable;

@Entity
@Table(name = "domicilio")
@Data
@NoArgsConstructor
@Audited
@Builder
@AllArgsConstructor
public class Domicilio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_calle")
    private String nombreCalle;

    @Column(name = "numero")
    private int numero;

    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;


    public Domicilio(String nombreCalle, int numero) {
        this.nombreCalle = nombreCalle;
        this.numero = numero;
    }

    public Domicilio(String nombreCalle, int numero, Cliente cliente) {
        this.nombreCalle = nombreCalle;
        this.numero = numero;
        this.cliente = cliente;
    }
}
