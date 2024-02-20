package com.bs23.simpleapp.web.rest;

import com.bs23.simpleapp.domain.Order;
import com.bs23.simpleapp.repository.OrderRepository;
import com.bs23.simpleapp.service.OrderService;
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
 * REST controller for managing {@link com.bs23.simpleapp.domain.Order}.
 */
@RestController
@RequestMapping("/test/api/orders")
public class OrderResource {

    private final Logger log = LoggerFactory.getLogger(OrderResource.class);

    private static final String ENTITY_NAME = "simpleecommerceOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderService orderService;

    private final OrderRepository orderRepository;

    public OrderResource(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @PostMapping("")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) throws URISyntaxException {
        log.debug("REST request to save Order : {}", order);
        if (order.getId() != null) {
            throw new BadRequestAlertException("A new order cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Order result = orderService.save(order);
        return ResponseEntity
            .created(new URI("/test/api/orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable(value = "id", required = false) final UUID id, @RequestBody Order order)
        throws URISyntaxException {
        log.debug("REST request to update Order : {}, {}", id, order);

        RestUtil.check(id, order.getId(), ENTITY_NAME, orderRepository);

        Order result = orderService.update(order);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, order.getId().toString()))
            .body(result);
    }

    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Order> partialUpdateOrder(@PathVariable(value = "id", required = false) final UUID id, @RequestBody Order order)
        throws URISyntaxException {
        log.debug("REST request to partial update Order partially : {}, {}", id, order);

        RestUtil.check(id, order.getId(), ENTITY_NAME, orderRepository);

        Optional<Order> result = orderService.partialUpdate(order);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, order.getId().toString())
        );
    }

    @GetMapping("")
    public List<Order> getAllOrders() {
        log.debug("REST request to get all Orders");
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") UUID id) {
        log.debug("REST request to get Order : {}", id);
        Optional<Order> order = orderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") UUID id) {
        log.debug("REST request to delete Order : {}", id);
        orderService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
