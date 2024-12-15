package com.minimizing.system;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Marca la clase como una entidad de JPA (Java Persistence API)
@Entity
public class Producto {

    // Define el campo 'id' como la clave primaria de la entidad, y se generará automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Define los atributos del producto
    private String nombre;  // Nombre del producto
    private int cantidad;   // Cantidad disponible del producto
    private double precio;  // Precio del producto

    // Constructor vacío (sin parámetros) para la creación de instancias sin inicialización de atributos
    public Producto() {
    }

    // Constructor con parámetros para crear un producto con valores iniciales
    public Producto(String nombre, int cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Getter para obtener el valor del campo 'id'
    public Long getId() {
        return id;
    }

    // Setter para modificar el valor del campo 'id'
    public void setId(Long id) {
        this.id = id;
    }

    // Getter para obtener el valor del campo 'nombre'
    public String getNombre() {
        return nombre;
    }

    // Setter para modificar el valor del campo 'nombre'
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para obtener el valor del campo 'cantidad'
    public int getCantidad() {
        return cantidad;
    }

    // Setter para modificar el valor del campo 'cantidad'
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Getter para obtener el valor del campo 'precio'
    public double getPrecio() {
        return precio;
    }

    // Setter para modificar el valor del campo 'precio'
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
