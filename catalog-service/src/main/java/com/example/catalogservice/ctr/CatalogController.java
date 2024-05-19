package com.example.catalogservice.ctr;


import com.example.catalogservice.entity.CatalogEntity;
import com.example.catalogservice.service.CatalogService;
import com.example.catalogservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;
    private final Environment env;
    private final ModelMapper mapper;



    @GetMapping("/health-check")
    private String healthCheck() {

        return String.format("It's Working Service on Port %s",
                env.getProperty("local.server.port"));
    }


    @GetMapping("/catalogs")
    private ResponseEntity<List<ResponseCatalog>> catalogs() {

        Iterable<CatalogEntity> allCatalogs = catalogService.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();

        allCatalogs.forEach(v -> {
            result.add(mapper.map(v, ResponseCatalog.class));
        });

        return ResponseEntity.ok(result);

    }

}
