package com.bs23.simpleapp.web.rest;

import com.bs23.simpleapp.web.rest.errors.BadRequestAlertException;
import java.util.Objects;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public class RestUtil {

    public static void check(UUID id, UUID requestId, String entityName, JpaRepository<?, UUID> repository) {
        if (requestId == null) throw new BadRequestAlertException("Invalid id", entityName, "idnull");

        if (!Objects.equals(id, requestId)) throw new BadRequestAlertException("Invalid ID", entityName, "idinvalid");

        if (!repository.existsById(id)) throw new BadRequestAlertException("Entity not found", entityName, "idnotfound");
    }
}
