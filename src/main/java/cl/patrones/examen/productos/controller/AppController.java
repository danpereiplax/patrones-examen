package cl.patrones.examen.productos.controller;

import cl.patrones.examen.descuentos.DescuentoService;
import cl.patrones.examen.descuentos.Producto;
import cl.patrones.examen.productos.service.ProductoService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class AppController {

    private final ProductoService productoService;
    private final DescuentoService descuentoService;

    public AppController(ProductoService productoService) {
        this.productoService = productoService;
        this.descuentoService = new DescuentoService();
    }

    @GetMapping("/")
    public String inicio(Model model) {
        var productosOriginales = productoService.getProductos();

        // Obtener si el usuario tiene el rol ROLE_EMPLEADO
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final boolean esEmpleado;
        if (principal instanceof UserDetails user) {
            esEmpleado = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_EMPLEADO"));
        } else {
            esEmpleado = false;
        }

        // Aplicar descuento con Strategy + Decorator
        List<Producto> productosConDescuento = productosOriginales.stream()
            .map(p -> {
                Producto dto = new Producto(
                    p.getNombre(),
                    p.getCategoria().getNombre(),
                    BigDecimal.valueOf(p.getPrecioLista())
                );
                BigDecimal descuento = descuentoService.aplicarDescuento(dto, esEmpleado);
                return new Producto(
                    dto.getNombre(),
                    dto.getCategoria(),
                    dto.getPrecio().subtract(descuento)
                );
            })
            .toList();

        model.addAttribute("productos", productosConDescuento);
        return "inicio";
    }
}
