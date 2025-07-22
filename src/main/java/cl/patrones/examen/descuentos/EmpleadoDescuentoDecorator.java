package cl.patrones.examen.descuentos;

import java.math.BigDecimal;

public class EmpleadoDescuentoDecorator implements DescuentoStrategy {

    private final DescuentoStrategy baseStrategy;

    public EmpleadoDescuentoDecorator(DescuentoStrategy baseStrategy) {
        this.baseStrategy = baseStrategy;
    }

    @Override
    public BigDecimal calcular(Producto producto, boolean esEmpleado) {
        BigDecimal descuentoBase = baseStrategy.calcular(producto, false); // calcular sin considerar que es empleado
        BigDecimal descuentoEmpleado = esEmpleado
            ? producto.getPrecio().multiply(new BigDecimal("0.05"))
            : BigDecimal.ZERO;

        // Se aplica solo el mayor
        return descuentoBase.max(descuentoEmpleado);
    }
}
