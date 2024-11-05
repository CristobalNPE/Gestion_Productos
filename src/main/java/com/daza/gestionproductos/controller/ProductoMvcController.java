package com.daza.gestionproductos.controller;

import com.daza.gestionproductos.dto.ProductoCreateDTO;
import com.daza.gestionproductos.dto.ProductoDTO;
import com.daza.gestionproductos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoMvcController {

    private final ProductoService productoService;

    @GetMapping
    public String listarProductos(Pageable pageable, Model model){
        Page<ProductoDTO> productoDTOS = productoService.listarProductos(pageable);
        model.addAttribute("productos", productoDTOS);
        return "productos";
    }

    @GetMapping("/crear-producto")
    public String crearProducto(Model model){
        model.addAttribute("productoCreateDTO", new ProductoCreateDTO());
        return "crearProducto";
    }

    @PostMapping("/crear-producto")
    public String crearProducto(@ModelAttribute ProductoCreateDTO productoCreateDTO){
        productoService.createProducto(productoCreateDTO);
        return "redirect:/productos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarProducto(@PathVariable  Long id){
        productoService.deleteProducto(id);
        return "redirect:/productos";
    }

}
