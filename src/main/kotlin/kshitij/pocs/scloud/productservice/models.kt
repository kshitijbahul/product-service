package kshitij.pocs.scloud.productservice

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import javax.annotation.Generated

@Document
data class Product(@Id @Generated val id : String, var name:String, var category:Long) : Serializable


data class ProductUpdateMessage(val product: Product,val isDeleted: Boolean): Serializable