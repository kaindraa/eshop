package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class EshopApplicationTests {

    @Test
    void eshopApplicationClassExists() throws Exception {
        Class<?> clazz = Class.forName("id.ac.ui.cs.advprog.eshop.EshopApplication");
        assertNotNull(clazz.getDeclaredConstructor().newInstance());
    }

    @Test
    void mainMethodRunsSpringApplication() {
        try (var mockStatic = Mockito.mockStatic(SpringApplication.class)) {
            mockStatic.when(() -> SpringApplication.run(EshopApplication.class, new String[]{}))
                    .thenReturn(null);

            EshopApplication.main(new String[]{});

            mockStatic.verify(() -> SpringApplication.run(EshopApplication.class, new String[]{}), times(1));
        }
    }
}
