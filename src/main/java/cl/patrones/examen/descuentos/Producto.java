package cl.patrones.examen.descuentos;

import java.math.BigDecimal;

public class Producto {
    private String nombre;
    private String categoria;
    private BigDecimal precio;

    public Producto(String nombre, String categoria, BigDecimal precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }
}
