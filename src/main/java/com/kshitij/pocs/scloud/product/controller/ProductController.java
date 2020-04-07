package com.kshitij.pocs.scloud.product.controller;

import com.kshitij.pocs.scloud.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }


    @GetMapping("/product/{id}")
    ResponseEntity getProduct(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
    }
    @GetMapping("/products")
    ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(this.productService.getAllProducts());
    }
    @GetMapping("/products/category")
    ResponseEntity<?> getProductByCategoryId(@RequestParam String categoryId){
        log.info("Got the Request Parma as "+categoryId);
        return ResponseEntity.ok(this.productService.getProductByCategory(Long.valueOf(categoryId)));
    }
}
