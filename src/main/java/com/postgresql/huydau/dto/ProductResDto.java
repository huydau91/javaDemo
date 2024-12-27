package com.postgresql.huydau.dto;

import java.util.List;

import com.postgresql.huydau.repo.entity.ProductEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductResDto {
    private final List<ProductEntity> products;
    private final long totalProducts;
    private final int page;
    private final int pageSize;
}
