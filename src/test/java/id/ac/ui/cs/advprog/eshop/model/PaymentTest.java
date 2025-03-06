package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentTest {


    @Test
    void testCreateValidPayment(){

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC");

        Payment payment = new Payment("3c011546-313a-4471-b897-8888e7b9cede",
                "VOUCHER_CODE",
                "REJECTED",
                paymentData
        );

        assertEquals("3c011546-313a-4471-b897-8888e7b9cede", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());

    }

    @Test
    void testCreatePaymentInvalidMethod(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("3c011546-313a-4471-b897-8888e7b9cede",
                    "invalid-voucher",
                    "REJECTED",
                    paymentData
            );
        });

    }

    @Test
    void testCreatePaymentInvalidStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("3c011546-313a-4471-b897-8888e7b9cede",
                    "VOUCHER_CODE",
                    "invalid-status",
                    paymentData
            );
        });





    }
}
