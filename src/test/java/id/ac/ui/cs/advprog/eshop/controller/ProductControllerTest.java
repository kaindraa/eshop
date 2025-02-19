package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProduct() throws Exception {
        mockMvc.perform(post("/product/create")
                        .flashAttr("product", new Product()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        String productId = "test-id";

        mockMvc.perform(get("/product/delete/" + productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).delete(productId);
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = new Product();
        when(productService.findById("test-id")).thenReturn(product);

        mockMvc.perform(get("/product/edit/test-id"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"));

        verify(productService, times(1)).findById("test-id");
    }

    @Test
    void testEditProduct() throws Exception {
        mockMvc.perform(post("/product/edit/test-id")
                        .flashAttr("product", new Product()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).edit(eq("test-id"), any(Product.class));
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> productList = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"));

        verify(productService, times(1)).findAll();
    }
}
