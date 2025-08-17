package com.ensat.controllers;

import com.ensat.entities.Product;
import com.ensat.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Product controller.
 */
@Controller   // <-- use @Controller instead of @RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * List all products.
     */
    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("products", productService.listAllProducts());
        System.out.println("Returning products:");
        return "products";   // maps to templates/products.html
    }

    /**
     * View a specific product by its id.
     */
    @GetMapping("/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productshow";   // maps to templates/productshow.html
    }

    /**
     * Show product edit form.
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productform";   // maps to templates/productform.html
    }

    /**
     * New product.
     */
    @GetMapping("/new")
    public String newProduct(Model model) {
        System.out.println("new Product from opening.....");
        model.addAttribute("product", new Product());
        return "productform"; // must match productform.html
    }
    /**
     * Save product to database.
     */
    @PostMapping("")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/products/" + product.getId();
    }

    /**
     * Delete product by its id.
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
