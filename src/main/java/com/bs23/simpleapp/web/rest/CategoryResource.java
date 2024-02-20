package com.bs23.simpleapp.web.rest;

import com.bs23.simpleapp.domain.Category;
import com.bs23.simpleapp.repository.CategoryRepository;
import com.bs23.simpleapp.service.CategoryService;
import com.bs23.simpleapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.bs23.simpleapp.domain.Category}.
 */
@RestController
@RequestMapping("/test/api/categories")
public class CategoryResource {

    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    private static final String ENTITY_NAME = "simpleecommerceCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoryService categoryService;

    private final CategoryRepository categoryRepository;

    public CategoryResource(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) throws URISyntaxException {
        log.debug("REST request to save Category : {}", category);
        if (category.getId() != null) {
            throw new BadRequestAlertException("A new category cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Category result = categoryService.save(category);
        return ResponseEntity
            .created(new URI("/test/api/categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody Category category
    ) throws URISyntaxException {
        log.debug("REST request to update Category : {}, {}", id, category);

        RestUtil.check(id, category.getId(), ENTITY_NAME, categoryRepository);

        //        checkCategory(id, category.getId());

        Category result = categoryService.update(category);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, category.getId().toString()))
            .body(result);
    }

    //    private void checkCategory(UUID id, UUID catId){
    //        if (catId == null)
    //            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    //
    //        if (!Objects.equals(id, catId))
    //            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    //
    //        if (!categoryRepository.existsById(id))
    //            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    //    }

    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Category> partialUpdateCategory(
        @PathVariable(value = "id", required = false) final UUID id,
        @RequestBody Category category
    ) throws URISyntaxException {
        log.debug("REST request to partial update Category partially : {}, {}", id, category);

        //        checkCategory(id, category.getId());

        RestUtil.check(id, category.getId(), ENTITY_NAME, categoryRepository);

        Optional<Category> result = categoryService.partialUpdate(category);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, category.getId().toString())
        );
    }

    @GetMapping("")
    public List<Category> getAllCategories() {
        log.debug("REST request to get all Categories");
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") UUID id) {
        log.debug("REST request to get Category : {}", id);
        Optional<Category> category = categoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") UUID id) {
        log.debug("REST request to delete Category : {}", id);
        categoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
