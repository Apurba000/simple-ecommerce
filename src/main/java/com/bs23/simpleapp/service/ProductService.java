package com.bs23.simpleapp.service;

import com.bs23.simpleapp.domain.Product;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.bs23.simpleapp.domain.Product}.
 */
public interface ProductService {
    Product save(Product product);
    Product update(Product product);
    Optional<Product> partialUpdate(Product product);
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findOne(UUID id);
    void delete(UUID id);
}
