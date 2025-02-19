package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.controller.HomepageController;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class HomepageControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService; // Mock service to prevent context errors

    @InjectMocks
    private HomepageController homepageController; // Inject the mock into the controller

    @Test
    void testHomepage() throws Exception {
        // Manually initialize MockMvc (without loading full application context)
        mockMvc = MockMvcBuilders.standaloneSetup(homepageController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())  // Ensure HTTP 200 response
                .andExpect(view().name("Homepage")); // Ensure correct view is returned
    }
}
