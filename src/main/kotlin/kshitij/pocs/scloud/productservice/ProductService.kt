package kshitij.pocs.scloud.productservice

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/*
TODO
1. Serialize message as JSON and then send to rabbit MQ
2. Deserialise message and then Recieve in Product Search Service
 */

@Service
class ProductService(val productRepository: ProductRepository, val messageProducerService: MessageProducerService){
    fun getProductById(id:String) : Product {
        return productRepository.findById(id).get();
    }
    fun save(product: Product):Product {
        val product= productRepository.save(product)
        messageProducerService.sendMessage(product,false,"product.update.updated")
        return product
    }
    fun updateProduct(id: String, product: Product): Product {
        val existingProduct= productRepository.findByIdOrNull(id)
        existingProduct?.category = product.category
        existingProduct?.name =product.name
        return productRepository.save(existingProduct!!)
    }
    fun getProducts(): List<Product> {
        return productRepository.findAll()
    }
    fun getProductsByCategory(categoryId: Long): List<Product> {
        return productRepository.getProductByCategory(categoryId)
    }
    fun delete(id: String) {
        val existingProduct = productRepository.findByIdOrNull(id)
        existingProduct ?: ProductNotFoundException("Product with id $id not found")
        productRepository.deleteById(existingProduct?.id!!)
        messageProducerService.sendMessage(existingProduct!!,true,"product.update.deleted")
    }
}