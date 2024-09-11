package com.example.AplicacionJPA.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.io.Serializable;

@Entity
@Table(name = "detalle_factura")
@Data
@NoArgsConstructor
@Audited
@AllArgsConstructor
@Builder
public class DFactura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "subtotal")
    private int subtotal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_articulo")
    private Articulo articulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_factura")
    private Factura factura;


    public DFactura(int cantidad, int subtotal) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public DFactura(int cantidad, int subtotal, Articulo articulo) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.articulo = articulo;
    }
}
