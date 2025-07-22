package cl.patrones.examen.descuentos;

import java.math.BigDecimal;

public class Producto {
    private String nombre;
    private String categoria;
    private BigDecimal precio;
    private BigDecimal precioFinal;
    private String imagen;

    public Producto(String nombre, String categoria, BigDecimal precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public Producto(String nombre, String categoria, BigDecimal precio, BigDecimal precioFinal, String imagen) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.precioFinal = precioFinal;
        this.imagen = imagen;
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

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public String getImagen() {
        return imagen;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }
}
