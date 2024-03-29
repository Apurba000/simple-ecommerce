package com.bs23.simpleapp.service;

import com.bs23.simpleapp.domain.Order;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link com.bs23.simpleapp.domain.Order}.
 */
public interface OrderService {
    /**
     * Save a order.
     *
     * @param order the entity to save.
     * @return the persisted entity.
     */
    Order save(Order order);

    /**
     * Updates a order.
     *
     * @param order the entity to update.
     * @return the persisted entity.
     */
    Order update(Order order);

    /**
     * Partially updates a order.
     *
     * @param order the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Order> partialUpdate(Order order);

    /**
     * Get all the orders.
     *
     * @return the list of entities.
     */
    List<Order> findAll();

    /**
     * Get the "id" order.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Order> findOne(UUID id);

    /**
     * Delete the "id" order.
     *
     * @param id the id of the entity.
     */
    void delete(UUID id);
}
