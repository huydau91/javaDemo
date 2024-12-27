package com.postgresql.huydau.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.postgresql.huydau.repo.entity.ProductEntity;

public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

}
