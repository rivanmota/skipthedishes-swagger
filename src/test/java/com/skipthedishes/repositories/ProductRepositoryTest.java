package com.skipthedishes.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.skipthedishes.configuration.RepositoryConfiguration;
import com.skipthedishes.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class ProductRepositoryTest {
	
    private ProductRepository productRepository;
    
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Test
    public void testSaveProduct(){
        
        Product product = new Product();
        product.setId(1L);
        product.setDescription("Test");
        product.setPrice(1.99);
        
        assertNull(product.getId());
        productRepository.save(product);
        assertNotNull(product.getId()); 
        
        Product objectFromDB = productRepository.findOne(product.getId());
        assertNotNull(objectFromDB);
        
        //should equal
        assertEquals(product.getId(), objectFromDB.getId());
        assertEquals(product.getDescription(), objectFromDB.getDescription());
        
        objectFromDB.setDescription("Changed Description");
        productRepository.save(objectFromDB);
        
        Product fetchedUpdatedProduct = productRepository.findOne(objectFromDB.getId());
        assertEquals(objectFromDB.getDescription(), fetchedUpdatedProduct.getDescription());
        
    }
}
