package controller;

import Dao.ProductosRepository;
import model.Productos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/licores")
public class ProductosController {

    @Autowired
    private ProductosRepository productosRepository;

    //Agregar productos
    @GetMapping(value = "/agregar")
    public String agregarProducto(Model model) {
        model.addAttribute("producto", new Productos());
        return "productos/agregar_producto";
    }

    //Mostrar productos
    @GetMapping(value = "/mostrar")
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", productosRepository.findAll());
        return "productos/ver_productos";
    }

    //Editar Productos
    @GetMapping(value = "/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        model.addAttribute("producto", productosRepository.findById(id).orElse(null));
        return "productos/editar_producto";
    }

    //Agregar
    @PostMapping(value = "/agregar")
    public String guardarProducto(@ModelAttribute @Valid Productos productos, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "productos/agregar_producto";
        }
        if (productosRepository.findFirstByCodigo(productos.getCodigo()) != null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/productos/agregar";
        }
        productosRepository.save(productos);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos/agregar";
    }

    //Editar
    @PostMapping(value = "/editar/{id}")
    public String actualizarProducto(@ModelAttribute @Valid Productos productos, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (productos.getId() != null) {
                return "productos/editar_producto";
            }
            return "redirect:/productos/mostrar";
        }
        Productos posibleProductoExistente = productosRepository.findFirstByCodigo(productos.getCodigo());

        if (posibleProductoExistente != null && !posibleProductoExistente.getId().equals(productos.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/productos/agregar";
        }
        productosRepository.save(productos);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos/mostrar";
    }

    //Eliminar
    @PostMapping(value = "/eliminar")
    public String eliminarProducto(@ModelAttribute Productos productos, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        productosRepository.deleteById(productos.getId());
        return "redirect:/productos/mostrar";
    }


}
