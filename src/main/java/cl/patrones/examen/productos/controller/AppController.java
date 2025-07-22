package cl.patrones.examen.productos.controller;

import cl.patrones.examen.productos.domain.Producto;
import cl.patrones.examen.productos.service.ProductoService;
import cl.patrones.examen.descuentos.DescuentoService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AppController {

    private final ProductoService productoService;
    private final DescuentoService descuentoService;

    public AppController(ProductoService productoService, DescuentoService descuentoService) {
        this.productoService = productoService;
        this.descuentoService = descuentoService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        // Verifica si el usuario tiene el rol de EMPLEADO
        final boolean esEmpleado;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            esEmpleado = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(role -> role.equals("ROLE_EMPLEADO"));
        } else {
            esEmpleado = false;
        }

        // Se obtienen los productos sin necesidad de cast
        List<? extends Producto> productos = productoService.getProductos();

        // Aplica descuentos y arma nueva lista con precios finales
        List<Producto> productosConDescuento = productos.stream().map(p -> {
            Producto dto = new Producto(p.getNombre(), p.getCategoria(), p.getPrecio(), p.getImagen());
            BigDecimal descuento = descuentoService.aplicarDescuento(p, esEmpleado);
            dto.setPrecioFinal(p.getPrecio().subtract(descuento));
            return dto;
        }).collect(Collectors.toList());

        model.addAttribute("productos", productosConDescuento);
        return "inicio";
    }
}
