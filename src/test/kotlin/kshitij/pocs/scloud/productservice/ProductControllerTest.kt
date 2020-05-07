package kshitij.pocs.scloud.productservice

import org.hamcrest.CoreMatchers.isA
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest{

    @Autowired
    private lateinit var testRestTemplate:TestRestTemplate

    @Test
    fun `Test if we get a response from Base controller`() {
        val response = testRestTemplate.getForEntity("/product/Message", String::class.java)
        assertNotNull(response)
        assertEquals(response?.statusCode,HttpStatus.OK)
        assertEquals(response?.body,"Back with the Message")
    }

    @Test
    fun `get product by id`(){
        val response = testRestTemplate.getForEntity("/product/1", String::class.java)
        assertThat(response?.statusCode,equalTo(HttpStatus.OK))
        //assertEquals(response?.body,ResponseEntity<Product>)
    }
}