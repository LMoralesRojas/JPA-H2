# Aplicación JPA con Hibernate Envers

Este proyecto es una aplicación de ejemplo que utiliza **Spring Boot**, **JPA** y **Hibernate Envers** para manejar operaciones de persistencia con entidades, relaciones bidireccionales y auditoría de los cambios en las entidades.

## Descripción

La aplicación permite la creación y persistencia de entidades como `Cliente`, `Domicilio`, `Articulo`, `Factura` y otras, con relaciones bidireccionales entre ellas. Además, se ha agregado funcionalidad de auditoría utilizando Hibernate Envers para registrar revisiones de las entidades.

### Principales características:
- Uso de **JPA** para la gestión de entidades.
- Relación bidireccional entre `Cliente` y `Domicilio`.
- Persistencia de `Factura` con detalles asociados (artículos).
- Integración con **Hibernate Envers** para auditoría de cambios en las entidades.
- Uso del patrón **Builder** para la creación de instancias de las entidades.

## Instalación

### Prerrequisitos
- **Java 17** (o superior)
- **Maven** o **Gradle**
- **Spring Boot**
- **MySQL** u otra base de datos (opcional)

### Pasos de instalación
1. Clona el repositorio:
    ```bash
    git clone https://github.com/usuario/aplicacion-jpa-envers.git
    cd aplicacion-jpa-envers
    ```

2. Configura la base de datos en el archivo `application.properties` o `application.yml` según la base de datos que estés utilizando.

3. Ejecuta la aplicación:
    - Si usas **Maven**:
      ```bash
      mvn spring-boot:run
      ```
    - Si usas **Gradle**:
      ```bash
      gradle bootRun
      ```

## Uso

Una vez ejecutada la aplicación, se crean instancias de las entidades `Cliente`, `Domicilio`, `Articulo`, y `Factura` con sus detalles asociados. Además, el módulo de auditoría registra las revisiones realizadas sobre las entidades auditadas.

Para persistir las entidades y ejecutar las transacciones:
- Los clientes tienen asignados un domicilio.
- Los artículos pueden pertenecer a varias categorías.
- Cada factura tiene una lista de artículos comprados por el cliente.

### Ejemplo de creación de entidades
```java
// Crear un cliente con domicilio
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

// Crear artículos y agregarlos a la factura
Articulo art1 = Articulo.builder()
    .cantidad(200)
    .precio(1000)
    .denominacion("Yogurt Ser sabor Frutilla")
    .build();

Factura factura1 = Factura.builder()
    .numero(12)
    .fecha("10/08/2020")
    .cliente(cliente)
    .detalles(List.of(det1, det2))
    .build();
