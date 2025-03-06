package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    Map<String, String> paymentData = new HashMap<>();

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC");


        Payment payment1 = new Payment("3c011546-313a-4471-b897-8888e7b9cede",
                PaymentMethod.BANK_TRANSFER.getValue(),
                paymentData
        );
        payments.add(payment1);

        Payment payment2 = new Payment("519cc7cd-a4ac-4206-b36e-ae6de8a5b45e",
                PaymentMethod.VOUCHER_CODE.getValue(),
                PaymentStatus.REJECTED.getValue(),
                paymentData
        );
        payments.add(payment2);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(0);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData().get("voucherCode"), findResult.getPaymentData().get("voucherCode"));
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(0);
        paymentRepository.save(payment);

        Map<String, String> updatedData = new HashMap<>();

        Payment updatedPayment = new Payment(payment.getId(), payment.getMethod(), PaymentStatus.SUCCESS.getValue(), paymentData);
        Payment result = paymentRepository.save(updatedPayment);

        Payment findResult = paymentRepository.getPayment(payment.getId());
        assertEquals(updatedPayment.getId(), result.getId());
        assertEquals(updatedPayment.getId(), findResult.getId());
        assertEquals(updatedPayment.getMethod(), findResult.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), findResult.getStatus());
        assertEquals(updatedPayment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testGetPaymentByValidId() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.getPayment(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertEquals(payments.get(1).getPaymentData().get("voucherCode"), findResult.getPaymentData().get("voucherCode"));
    }

    @Test
    void testGetPaymentByInvalidId() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.getPayment("invalid-id");
        assertNull(findResult);
    }

    @Test
    void testGetAllPayments() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> allPayments = paymentRepository.getAllPayments();
        assertEquals(2, allPayments.size());

        assertEquals(payments.get(0).getId(), allPayments.get(0).getId());
        assertEquals(payments.get(1).getId(), allPayments.get(1).getId());

        assertEquals(payments.get(0).getPaymentData().get("voucherCode"), allPayments.get(0).getPaymentData().get("voucherCode"));
        assertEquals(payments.get(1).getPaymentData().get("voucherCode"), allPayments.get(1).getPaymentData().get("voucherCode"));
    }
}
