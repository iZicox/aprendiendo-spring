package com.fundamentos.practica.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fundamentos.practica.entities.Product;
import com.fundamentos.practica.repositories.ProductRepository;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping({ "/", "/list", "/products" })
    public String todosLosProducts(Model model) {
        model.addAttribute("productos", repository.findAll());
        return "index";
    }

    @GetMapping("/producto/{id}")
    public String paginaProducto(@PathVariable("id") Long id, Model model) {
        Product producto = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("No encontrado"));
        model.addAttribute("producto", producto);
        return "producto";
    }
    

    @GetMapping("/categoria/{cat}")
    public String productosPorCategoria(@PathVariable("cat") String cat, Model model) {
        List<Product> productos = repository.findByCategoriaIgnoreCase(cat);
        model.addAttribute("productos", productos);
        model.addAttribute("categoriaActual", capitalizar(cat));
        return "index";
    }

    @ModelAttribute("categorias")
    public List<String> categories() {

        return repository.listarCategorias();
    }

    /**
     * metodo para capitalizar una palabra
     * 
     * @param texto
     * @return
     */
    public static String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }

        return texto.substring(0, 1).toUpperCase() +
                texto.substring(1).toLowerCase();
    }

}
