package kshitij.pocs.scloud.productservice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

/*
TODO
1. Write test cases
2. Pass product without ID ?
3. Enable Caching
4. Configure RabbitMQ
5. Throw NoDataFound exception
 */




@RestController
@RequestMapping("/product")
class ProductController(val productService: ProductService){
    @GetMapping()
    fun sayHello(@PathVariable("message") message:String):ResponseEntity<List<Product>>{
        return ResponseEntity.ok(productService.getProducts())
    }
    @GetMapping("/{id}")
    fun getProductById(@PathVariable("id") id: String ): ResponseEntity<Product>{
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @PostMapping
    fun createProduct(@RequestBody product:Product):ResponseEntity<URI> {
        val id= productService.save(product).id
        return ResponseEntity.created(URI.create("/product/$id")).build()
    }
    @PutMapping("/{id}")
    fun updateProduct(@PathVariable("id") id :String,@RequestBody product: Product) :ResponseEntity<Product>{
        return ResponseEntity.ok(productService.updateProduct(id,product))
    }
    @GetMapping("/category/{categoryId}")
    fun getProductsByCategory(@PathVariable("categoryId") categoryId:Long):ResponseEntity<List<Product>>{
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId))
    }
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable("id") id:String) :ResponseEntity<Any>{
        productService.delete(id)
        return ResponseEntity.status(HttpStatus.ACCEPTED).build()
    }

}