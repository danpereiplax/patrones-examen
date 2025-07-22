package cl.patrones.examen.descuentos;

import java.math.BigDecimal;

public class DescuentoMiercoles implements DescuentoStrategy {
    @Override
    public BigDecimal calcular(Producto producto, boolean esEmpleado) {
        if ("taladros percutores".equalsIgnoreCase(producto.getCategoria())) {
            return producto.getPrecio().multiply(new BigDecimal("0.10"));
        }
        return BigDecimal.ZERO;
    }
}
