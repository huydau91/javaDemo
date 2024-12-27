package com.postgresql.huydau.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.postgresql.huydau.repo.ProductRepo;
import com.postgresql.huydau.repo.entity.ProductEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepo productRepoProductRepo;

    public Page<ProductEntity> getAllProducts(int page, int pageSize) {
        return productRepoProductRepo.findAll(PageRequest.of(page, pageSize));
    }
}
