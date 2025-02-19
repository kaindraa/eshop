package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete(productId);

        assertNull(productRepository.findById(productId));
    }

    @Test
    void testFindById() {
        Product product = new Product();
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product findProduct = productRepository.findById(productId);

        assertEquals(product, findProduct);
    }

    @Test
    void testFindByIdNeg(){
        Product findProduct = productRepository.findById("not-existing-product-id");
        assertNull(findProduct);

    }

    @Test
    void testSave_UpdateExistingProduct() {
        Product existingProduct = new Product();
        existingProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd");
        existingProduct.setProductName("Laptop");
        existingProduct.setProductQuantity(10);

        productRepository.create(existingProduct);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd");
        updatedProduct.setProductName("Gaming Laptop");
        updatedProduct.setProductQuantity(20);

        Product result = productRepository.save(updatedProduct);

        assertNotNull(result);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd", result.getProductId());
        assertEquals("Gaming Laptop", result.getProductName());
        assertEquals(20, result.getProductQuantity());
    }

    @Test
    void testSave_NonExistentProduct() {
        Product newProduct = new Product();
        newProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd");
        newProduct.setProductName("Smartphone");
        newProduct.setProductQuantity(15);

        Product result = productRepository.save(newProduct);


        assertNull(result);
    }

}
