package com.bs23.simpleapp.service;

import com.bs23.simpleapp.domain.Category;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link com.bs23.simpleapp.domain.Category}.
 */
public interface CategoryService {
    Category save(Category category);
    Category update(Category category);
    Optional<Category> partialUpdate(Category category);
    List<Category> findAll();
    Optional<Category> findOne(UUID id);
    void delete(UUID id);
}
