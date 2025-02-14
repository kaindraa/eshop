package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    private Product sampleProduct;

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
    void testUpdateProduct() {
        productRepository = new ProductRepository();
        sampleProduct = new Product();
        sampleProduct.setProductName("Lemari");
        sampleProduct.setProductQuantity(10);
        productRepository.create(sampleProduct);
        sampleProduct.setProductName("Meja");
        sampleProduct.setProductQuantity(15);

        Product updatedProduct = productRepository.save(sampleProduct);
        assertNotNull(updatedProduct);
        assertEquals("Meja", updatedProduct.getProductName());
        assertEquals(15, updatedProduct.getProductQuantity());
    }

    @Test
    void testUpdateProductNotFound() {
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId(UUID.randomUUID().toString());
        nonExistentProduct.setProductName("Kursi");
        nonExistentProduct.setProductQuantity(7);

        Product updatedProduct = productRepository.save(nonExistentProduct);
        assertNull(updatedProduct);
    }

    @Test
    void testDeleteProduct() {
        productRepository = new ProductRepository();
        sampleProduct = new Product();
        sampleProduct.setProductName("Lemari");
        sampleProduct.setProductQuantity(100);
        productRepository.create(sampleProduct);
        String idToDelete = sampleProduct.getProductId();
        productRepository.delete(sampleProduct.getProductId());
        assertNull(productRepository.findById(idToDelete));
    }
    @Test
    void testDeleteProductNotFound() {
        productRepository = new ProductRepository();
        sampleProduct = new Product();
        sampleProduct.setProductName("Lemari");
        sampleProduct.setProductQuantity(10);
        productRepository.create(sampleProduct);
        String fakeId = UUID.randomUUID().toString();
        productRepository.delete(fakeId);
        assertNotNull(productRepository.findById(sampleProduct.getProductId()));
    }
}
