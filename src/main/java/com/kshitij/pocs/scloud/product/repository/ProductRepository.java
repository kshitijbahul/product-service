package com.kshitij.pocs.scloud.product.repository;

import com.kshitij.pocs.scloud.product.entity.Product;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    @Cacheable("ProductCategoryCache")
    List<Product> getProductByCategory(Long categoryId);
}
