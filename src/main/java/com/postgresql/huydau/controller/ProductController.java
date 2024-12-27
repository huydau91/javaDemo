package com.postgresql.huydau.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.huydau.dto.ProductResDto;
import com.postgresql.huydau.repo.entity.ProductEntity;
import com.postgresql.huydau.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getListProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int pageSize) {

        Page<ProductEntity> products = productService.getAllProducts(page, pageSize);

        return ResponseEntity.ok(new ProductResDto(products.getContent(), products.getTotalElements(), page, pageSize));
    }
}
