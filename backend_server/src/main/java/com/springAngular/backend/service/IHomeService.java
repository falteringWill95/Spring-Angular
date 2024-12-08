package com.springAngular.backend.service;

import com.springAngular.backend.entity.Product;

import java.util.List;

public interface IHomeService {

    Product addProduct(Product p);

    Product editProduct(Product p);

    void deleteProduct(Long idProduct);

    List<Product> retrieveAll();
}
