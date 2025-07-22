package cl.patrones.examen.descuentos;

import java.math.BigDecimal;

public class DescuentoMartes implements DescuentoStrategy {
    @Override
    public BigDecimal calcular(Producto producto, boolean esEmpleado) {
        if ("esmeriles angulares".equalsIgnoreCase(producto.getCategoria())) {
            return producto.getPrecio().multiply(new BigDecimal("0.08"));
        }
        return BigDecimal.ZERO;
    }
}
