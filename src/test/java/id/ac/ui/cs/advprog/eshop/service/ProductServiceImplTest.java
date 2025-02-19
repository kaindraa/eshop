package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("test-id");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testDeleteProduct() {
        String productId = "test-product-id";

        doNothing().when(productRepository).delete(productId);

        productServiceImpl.delete(productId);

        verify(productRepository, times(1)).delete(productId);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productServiceImpl.create(product);

        assertNotNull(createdProduct);
        assertEquals("test-id", createdProduct.getProductId());
        assertEquals("Test Product", createdProduct.getProductName());

        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindById() {
        String productId = "test-product-id";
        Product expectedProduct = new Product();

        when(productRepository.findById(productId)).thenReturn(expectedProduct);

        Product actualProduct = productServiceImpl.findById(productId);

        assertNotNull(actualProduct);
        assertEquals(expectedProduct, actualProduct);

        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testEditProduct() {
        Product existingProduct = new Product();
        String existingProductId = "eb558e9f-1c39-460e-8860-71af6af63bd";
        existingProduct.setProductId(existingProductId);
        existingProduct.setProductName("test-old-name");
        existingProduct.setProductQuantity(100);

        Product submittedProduct = new Product();
        submittedProduct.setProductName("test-new-name");
        submittedProduct.setProductQuantity(200);

        when(productRepository.findById(existingProductId)).thenReturn(existingProduct);

        productServiceImpl.edit(existingProductId, submittedProduct);

        assertEquals(existingProductId, existingProduct.getProductId());
        assertEquals("test-new-name", existingProduct.getProductName());
        assertEquals(200, existingProduct.getProductQuantity());

        verify(productRepository, times(1)).findById(existingProductId);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        product1.setProductId("id-1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(5);

        Product product2 = new Product();
        product2.setProductId("id-2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(15);

        Iterator<Product> iterator = Arrays.asList(product1, product2).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> allProducts = productServiceImpl.findAll();

        assertNotNull(allProducts);
        assertEquals(2, allProducts.size());
        assertEquals("Product 1", allProducts.get(0).getProductName());
        assertEquals("Product 2", allProducts.get(1).getProductName());

        verify(productRepository, times(1)).findAll();
    }
}
