package com.skipthedishes.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.skipthedishes.configuration.RepositoryConfiguration;
import com.skipthedishes.model.Product;

@RunWith(SpringRunner.class)
@DataJpaTest 
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class ProductRepositoryTest {
	
	@Autowired
    private ProductRepository productRepository;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void saveShouldPersistData(){
        Product product = new Product();
		        product.setDescription("Test");
		        product.setPrice(1.99);
        
        assertNull(product.getProductId());
        
        productRepository.save(product);
        
        assertNotNull(product.getProductId()); 
        
        Assertions.assertThat(product.getDescription()).isEqualTo("Test");
        Assertions.assertThat(product.getPrice()).isEqualTo(1.99);
    }
    
    @Test
    public void updateShouldChangeAndPersistData(){
    	Product product = new Product();
    	product.setDescription("Test");
    	product.setPrice(1.99);
    	productRepository.save(product);
    	
    	Product objectFromDB = productRepository.findOne(product.getProductId());
    	assertNotNull(objectFromDB);
    	
    	objectFromDB.setDescription("Changed Description");
    	productRepository.save(objectFromDB);
    	
    	Product updatedProduct = productRepository.findOne(objectFromDB.getProductId());
    	assertEquals(objectFromDB.getDescription(), updatedProduct.getDescription());
    }
    
    @Test
    public void deleteShouldRemoveData(){
    	Product product = new Product();
    	product.setDescription("Test");
    	product.setPrice(1.99);
    	productRepository.save(product);
    	productRepository.delete(product);
    	
    	Assertions.assertThat(productRepository.findOne(product.getProductId())).isNull();;
    }
    
    @Test
    public void findByNameIgnoreCaseContainingShouldIgnoreCase(){
    	Product product1 = new Product();
    	product1.setName("test 1");
    	product1.setPrice(1.99);
    	productRepository.save(product1);
    	
    	Product product2 = new Product();
    	product2.setName("Test 2");
    	product2.setPrice(2.59);
    	productRepository.save(product2);
    	
    	
    	List<Product> listFromDB = productRepository.findByNameIgnoreCaseContaining("test");
    	Assertions.assertThat(listFromDB.size()).isEqualTo(2);
    	
    }
    
    
}
