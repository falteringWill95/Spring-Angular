package com.springAngular.backend.service.Implementation;

import com.springAngular.backend.entity.Product;
import com.springAngular.backend.repository.ProductRepository;
import com.springAngular.backend.service.IHomeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class HomeServiceImpl implements IHomeService {

    private final ProductRepository productRepository;


    @Override
    public Product addProduct(Product p) {
        log.info("Adding product old way");
        if (p.getTitle() == null || p.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Product title cannot be empty");
        }
        try {
            return productRepository.save(p);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add product", e);
        }
    }

    @Override
    public Product editProduct(Product p) {
        log.info("Editing product old way");
        if (p.getId_product() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        if (p.getTitle() == null || p.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Product title cannot be empty");
        }
        try {
            return productRepository.save(p);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update product", e);
        }
    }

    @Override
    public void deleteProduct(Long idProduct) {
        log.info("Deleting product old way");
        Optional<Product> product = productRepository.findById(idProduct);

        product.ifPresent(p -> {
            productRepository.delete(p);
            log.info("Product with id " + idProduct + " has been deleted");
        });
    }

    @Override
    public List<Product> retrieveAll() {
        log.info("Retriving product old way");
        return productRepository.findAll();
    }
}
