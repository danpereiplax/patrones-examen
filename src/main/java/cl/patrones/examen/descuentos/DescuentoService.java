package cl.patrones.examen.descuentos;

import java.math.BigDecimal;

public interface DescuentoService {
    BigDecimal aplicarDescuento(Producto producto, boolean esEmpleado);
}
