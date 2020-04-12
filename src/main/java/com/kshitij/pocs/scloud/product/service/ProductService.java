package com.kshitij.pocs.scloud.product.service;

import com.kshitij.pocs.scloud.product.entity.Product;
import com.kshitij.pocs.scloud.product.exceptions.ProductNotFoundException;
import com.kshitij.pocs.scloud.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    private ProductRepository productRepository;
    private MessageProducerService messageProducerService;


    public ProductService(ProductRepository productRepository,MessageProducerService messageProducerService){
        this.productRepository=productRepository;
        this.messageProducerService=messageProducerService;
    }

    public Product getProductById(String id) {
        return this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }
    public Iterable<Product> getAllProducts(){
        return this.productRepository.findAll();
    }
    public Product createProduct(Product product){
        Product createdProduct = this.productRepository.save(product);
        this.messageProducerService.sendMessage(product,Boolean.FALSE,"product.update");
        return createdProduct;
    }
    public List<Product> getProductByCategory(Long categoryId){
        return this.productRepository.getProductByCategory(categoryId);
    }

    public Product updateProduct(String id, Product product) {
        //Product existingProd=this.productRepository.findById(id);
        Product updatedProduct =this.productRepository.save(product);
        this.messageProducerService.sendMessage(updatedProduct,Boolean.FALSE,"product.update");
        return updatedProduct;
    }
}
