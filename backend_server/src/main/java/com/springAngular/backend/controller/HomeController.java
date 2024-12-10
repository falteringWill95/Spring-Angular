package com.springAngular.backend.controller;

import com.springAngular.backend.entity.Product;
import com.springAngular.backend.service.IHomeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class HomeController {

    private IHomeService homeService;


    @GetMapping("/all-products")
    public List<Product> getProducts() {
        return homeService.retrieveAll();
    }

    @PostMapping("/add-product")
    public Product addProduct(@RequestBody Product p) {
        return homeService.addProduct(p);
    }

    @PutMapping("/edit-product")
    public Product editProduct(@RequestBody Product p) {
        return homeService.editProduct(p);
    }

    @DeleteMapping("/delete-product/{idProduct}")
    public void deleteProduct(@PathVariable("idProduct") Long id) {
        homeService.deleteProduct(id);
    }

}
