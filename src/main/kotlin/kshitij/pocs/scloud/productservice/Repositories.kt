package kshitij.pocs.scloud.productservice

import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<Product, String>{
    fun getProductByCategory(categoryId:Long):List<Product>

}