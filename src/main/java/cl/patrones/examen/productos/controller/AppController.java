package cl.patrones.examen.productos.controller;

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
    final boolean esEmpleado;

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails userDetails) {
        esEmpleado = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_EMPLEADO"));
    } else {
        esEmpleado = false;
    }

    var productosOriginales = productoService.getProductos();

    List<cl.patrones.examen.descuentos.Producto> productosConDescuento = productosOriginales.stream().map(p -> {
    String nombre = p.getNombre();
    String categoria = p.getCategoria().getNombre(); // ← CORREGIDO
    BigDecimal precio = BigDecimal.valueOf(p.getPrecioLista()); // ← conversión segura

    String imagen = p.getImagen();

    cl.patrones.examen.descuentos.Producto dto =
        new cl.patrones.examen.descuentos.Producto(nombre, categoria, precio, null, imagen);

    BigDecimal descuento = descuentoService.aplicarDescuento(dto, esEmpleado);
    dto.setPrecioFinal(precio.subtract(descuento));
    return dto;
}).collect(Collectors.toList());


    model.addAttribute("productos", productosConDescuento);
    return "inicio";
}

}


