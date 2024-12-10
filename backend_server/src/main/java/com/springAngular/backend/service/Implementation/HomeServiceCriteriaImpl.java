package com.springAngular.backend.service.Implementation;

import com.springAngular.backend.entity.Product;
import com.springAngular.backend.service.IHomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Slf4j
@Primary
public class HomeServiceCriteriaImpl implements IHomeService {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Product addProduct(Product p) {
        entityManager.persist(p);
        return p;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Product editProduct(Product p) {
        if (p.getId_product() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        if (p.getTitle() == null || p.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Product title cannot be empty");
        }
        entityManager.persist(p);
        return p;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteProduct(Long idProduct) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root root = criteriaQuery.from(Product.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id_product"), idProduct));
        criteriaQuery.select(root);
        Product p = (Product) entityManager.createQuery(criteriaQuery).getSingleResult();
        entityManager.remove(p);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> retrieveAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
