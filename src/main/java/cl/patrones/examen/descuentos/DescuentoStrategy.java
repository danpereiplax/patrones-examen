package cl.patrones.examen.descuentos;

import java.math.BigDecimal;

public interface DescuentoStrategy {
    BigDecimal calcular(Producto producto, boolean esEmpleado);
}
