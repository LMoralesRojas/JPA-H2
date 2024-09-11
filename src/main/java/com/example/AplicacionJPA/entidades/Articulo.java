package com.example.AplicacionJPA.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articulo")
@Data
@NoArgsConstructor
@Audited
@AllArgsConstructor
@Builder
public class Articulo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "denominacion")
    private String denominacion;

    @Column(name = "precio")
    private int precio;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DFactura> dFacturas = new ArrayList<DFactura>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "articulo_categoria", joinColumns = @JoinColumn (name = "articulo_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<Categoria>();

    public Articulo(int cantidad, int precio, String denominacion) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.denominacion = denominacion;
    }
}
