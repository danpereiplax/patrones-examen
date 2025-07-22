package cl.patrones.examen.descuentos;

import java.math.BigDecimal;

public class TestDescuentos {
    public static void main(String[] args) {
        // Cambiar la fecha del sistema para probar otros dias
        Producto compresor = new Producto("Compresor A", "compresores de aire", new BigDecimal("100000"));
        Producto esmeril = new Producto("Esmeril B", "esmeriles angulares", new BigDecimal("80000"));
        Producto taladro = new Producto("Taladro C", "taladros percutores", new BigDecimal("60000"));
        Producto martillo = new Producto("Martillo", "otros", new BigDecimal("50000"));

        DescuentoService service = new DescuentoServiceImpl();

        System.out.println("=== Pruebas para usuario NORMAL ===");
        System.out.println("Compresor: $" + service.aplicarDescuento(compresor, false));
        System.out.println("Esmeril:   $" + service.aplicarDescuento(esmeril, false));
        System.out.println("Taladro:   $" + service.aplicarDescuento(taladro, false));
        System.out.println("Martillo:  $" + service.aplicarDescuento(martillo, false));

        System.out.println("\n=== Pruebas para usuario EMPLEADO ===");
        System.out.println("Compresor: $" + service.aplicarDescuento(compresor, true));
        System.out.println("Esmeril:   $" + service.aplicarDescuento(esmeril, true));
        System.out.println("Taladro:   $" + service.aplicarDescuento(taladro, true));
        System.out.println("Martillo:  $" + service.aplicarDescuento(martillo, true));
    }
}
