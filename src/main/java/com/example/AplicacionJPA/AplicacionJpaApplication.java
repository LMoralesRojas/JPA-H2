package com.example.AplicacionJPA;

import com.example.AplicacionJPA.entidades.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

@SpringBootApplication
public class AplicacionJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplicacionJpaApplication.class, args);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try{
			entityManager.getTransaction().begin();
			// Uso de @Builder para crear instancias de las entidades

			// Crear cliente y domicilio usando Builder
			Domicilio dom = Domicilio.builder()
					.nombreCalle("San Martin")
					.numero(1222)
					.build();

			Cliente cliente = Cliente.builder()
					.nombre("Pablo")
					.apellido("Muñoz")
					.dni(15245778)
					.domicilio(dom)
					.build();

			// Establecer la relación bidireccional
			dom.setCliente(cliente);

			// Crear artículos usando Builder
			Articulo art1 = Articulo.builder()
					.cantidad(200)
					.precio(1000)
					.denominacion("Yogurt Ser sabor Frutilla")
					.build();

			Articulo art2 = Articulo.builder()
					.cantidad(300)
					.precio(1500)
					.denominacion("Detergente Magistral")
					.build();

			// Añadir categorías a los artículos
			Categoria perecederos = new Categoria("Perecedero");
			Categoria lacteos = new Categoria("Lacteos");
			Categoria limpieza = new Categoria("Limpieza");

			art1.getCategorias().add(perecederos);
			art1.getCategorias().add(lacteos);

			art2.getCategorias().add(perecederos);
			art2.getCategorias().add(limpieza);

			// Crear detalles de factura usando Builder
			DFactura det1 = DFactura.builder()
					.articulo(art1)
					.cantidad(2)
					.subtotal(40)
					.build();

			DFactura det2 = DFactura.builder()
					.articulo(art2)
					.cantidad(1)
					.subtotal(80)
					.build();

			// Crear factura y agregar detalles usando Builder
			Factura factura1 = Factura.builder()
					.numero(12)
					.fecha("10/08/2020")
					.cliente(cliente)
					.detalles(List.of(det1, det2)) // Añadir detalles de factura
					.build();

			// Persistir entidades
			entityManager.persist(cliente);
			entityManager.persist(factura1);

			//Metodo unidireccional
			//Cliente cliente = new Cliente("Pepe", "Sancho",2345678);
			//Domicilio domicilio = new Domicilio("Algarrovo",1200);
			//cliente.setDomicilio(domicilio);
			//domicilio.setCliente(cliente);


			/* //Metodo de declaracion bidireccional
			   //Esta Es la declaracion de los dtos en a la table pero sin usar el metodo @Builder
			Domicilio dom = entityManager.find(Domicilio.class, 1L);
			Cliente cliente = entityManager.find(Cliente.class, 1L);

			Factura factura1 = new Factura();

			factura1.setNumero(12);
			factura1.setFecha("10/08/2020");

			Domicilio domicilio1 = new Domicilio("San Martin", 1222);
			Cliente cliente1 = new Cliente ("Pablo", "Muñoz", 15245778);
			cliente1.setDomicilio(domicilio1);
			domicilio1.setCliente(cliente1);

			factura1.setCliente(cliente1);

			Categoria perecederos = new Categoria("Perecedero");
			Categoria lacteos =  new Categoria("Lacteos");
			Categoria limpieza = new Categoria("Limpieza");

			Articulo art1 =  new Articulo(200, 1000, "Yogurt Ser sabor Frutilla");
			Articulo art2 =  new Articulo(300, 1500, "Detergente Magistral");

			art2.getCategorias().add(perecederos);
			art2.getCategorias().add(lacteos);
			lacteos.getArticulos().add(art1);
			perecederos.getArticulos().add(art1);

			art2.getCategorias().add(limpieza);
			limpieza.getArticulos().add(art2);

			DFactura det1 = new DFactura();

			det1.setArticulo(art1);
			det1.setCantidad(2);
			det1.setSubtotal(40);

			art1.getDFacturas().add(det1);
			factura1.getDetalles().add(det1);
			det1.setFactura(factura1);

			DFactura det2 = new DFactura();

			det2.setArticulo(art2);
			det2.setCantidad(1);
			det2.setSubtotal(80);

			art2.getDFacturas().add(det2);
			factura1.getDetalles().add(det2);
			det2.setFactura(factura1);

			factura1.setTotal(120);
			*/

			entityManager.persist(factura1);

			System.out.println("Cliente de domicilio" + dom.getCliente().getDni());
			System.out.println("Domicilio de cliente" + cliente.getDomicilio().getNombreCalle());


			entityManager.persist(cliente);
			entityManager.getTransaction().commit();


		}catch(Exception e){
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		entityManagerFactory.close();
	}
}
