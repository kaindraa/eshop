package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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
                PaymentMethod.BANK_TRANSFER.getValue(),
                PaymentStatus.REJECTED.getValue(),
                paymentData
        );

        assertEquals("3c011546-313a-4471-b897-8888e7b9cede", payment.getId());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());

    }

    @Test
    void testCreateValidPaymentDefaultStatus(){

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC");

        Payment payment = new Payment("3c011546-313a-4471-b897-8888e7b9cede",
                PaymentMethod.BANK_TRANSFER.getValue(),
                paymentData
        );

        assertEquals("3c011546-313a-4471-b897-8888e7b9cede", payment.getId());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.WAITING.getValue(), payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());

    }

    @Test
    void testCreatePaymentInvalidMethod(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("3c011546-313a-4471-b897-8888e7b9cede",
                    "invalid-voucher",
                    PaymentStatus.REJECTED.getValue(),
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
                    PaymentMethod.BANK_TRANSFER.getValue(),
                    "invalid-status",
                    paymentData
            );
        });
    }

    @Test
    void testSetStatusToSuccess(){

        Map<String, String> paymentData = new HashMap<>();

        Payment payment = new Payment("3c011546-313a-4471-b897-8888e7b9cede",
                PaymentMethod.BANK_TRANSFER.getValue(),
                paymentData
        );

        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus(){

        Map<String, String> paymentData = new HashMap<>();

        Payment payment = new Payment("3c011546-313a-4471-b897-8888e7b9cede",
                PaymentMethod.BANK_TRANSFER.getValue(),
                paymentData
        );

        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }
}
