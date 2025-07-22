package cl.patrones.examen.descuentos;

import java.math.BigDecimal;

public class SinDescuento implements DescuentoStrategy {
    @Override
    public BigDecimal calcular(Producto producto, boolean esEmpleado) {
        return BigDecimal.ZERO;
    }
}
