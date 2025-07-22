package cl.patrones.examen.descuentos;

import java.math.BigDecimal;

public class DescuentoLunes implements DescuentoStrategy {
    @Override
    public BigDecimal calcular(Producto producto, boolean esEmpleado) {
        if ("compresores de aire".equalsIgnoreCase(producto.getCategoria())) {
            return producto.getPrecio().multiply(new BigDecimal("0.06"));
        }
        return BigDecimal.ZERO;
    }
}
