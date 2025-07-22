package cl.patrones.examen.descuentos;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DescuentoService {

    public BigDecimal aplicarDescuento(Producto producto, boolean esEmpleado) {
        DescuentoStrategy baseStrategy = obtenerEstrategiaDelDia();

        DescuentoStrategy strategy = new EmpleadoDescuentoDecorator(baseStrategy);
        return strategy.calcular(producto, esEmpleado);
    }

    private DescuentoStrategy obtenerEstrategiaDelDia() {
        DayOfWeek dia = LocalDate.now().getDayOfWeek();
        return switch (dia) {
            case MONDAY -> new DescuentoLunes();
            case TUESDAY -> new DescuentoMartes();
            case WEDNESDAY -> new DescuentoMiercoles();
            default -> new SinDescuento();
        };
    }
}
