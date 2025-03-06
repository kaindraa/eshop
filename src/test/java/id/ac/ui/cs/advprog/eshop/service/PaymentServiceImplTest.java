package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    Order testOrder;
    Map<String, String> validVoucherPaymentData;
    Map<String, String> invalidVoucherPaymentData;
    Map<String, String> validBankPaymentData;
    Map<String, String> invalidBankPaymentData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Product product2 = new Product();
        product2.setProductId("c4c30537-bb7f-40d8-8c75-2e76e2083bf7");
        product2.setProductName("Sabun Bagus");
        product2.setProductQuantity(3);
        products.add(product2);

        testOrder = new Order(
                "3ef6107a-44ee-4ae5-a427-ead6ba3b4c83",
                products,
                1708560000L,
                "Adpro Wow"
        );
        testOrder.setStatus(OrderStatus.WAITING_PAYMENT.getValue());

        validVoucherPaymentData = new HashMap<>();
        validVoucherPaymentData.put("voucherCode", "ESHOP1234ABC5678");

        invalidVoucherPaymentData = new HashMap<>();
        invalidVoucherPaymentData.put("voucherCode", "ESHOP12ABC");

        validBankPaymentData = new HashMap<>();
        validBankPaymentData.put("bankName", "BCA");
        validBankPaymentData.put("referenceCode", "INV12345");

        invalidBankPaymentData = new HashMap<>();
        invalidBankPaymentData.put("bankName", "BCA");
    }

    @Test
    void testAddPaymentValidOrderAndMethodVoucherCode() {
        Payment savedPayment = new Payment(UUID.randomUUID().toString(),
                PaymentMethod.VOUCHER_CODE.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                validVoucherPaymentData
        );

        doReturn(savedPayment).when(paymentRepository).save(any(Payment.class));
        Payment result = paymentService.addPayment(
                testOrder,
                PaymentMethod.VOUCHER_CODE.getValue(),
                validVoucherPaymentData
        );

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertNotNull(result);
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), testOrder.getStatus());
    }

    @Test
    void testAddPaymentValidOrderAndMethodBankTransfer() {
        Payment savedPayment = new Payment(UUID.randomUUID().toString(),
                PaymentMethod.BANK_TRANSFER.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                validBankPaymentData
        );

        doReturn(savedPayment).when(paymentRepository).save(any(Payment.class));
        Payment result = paymentService.addPayment(
                testOrder,
                PaymentMethod.BANK_TRANSFER.getValue(),
                validBankPaymentData
        );

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertNotNull(result);
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), testOrder.getStatus());
    }

    @Test
    void testAddPaymentVoucherCodeAndInvalidData() {
        Payment savedPayment = new Payment(UUID.randomUUID().toString(),
                PaymentMethod.VOUCHER_CODE.getValue(),
                PaymentStatus.REJECTED.getValue(),
                invalidVoucherPaymentData
        );

        doReturn(savedPayment).when(paymentRepository).save(any(Payment.class));
        Payment result = paymentService.addPayment(
                testOrder,
                PaymentMethod.VOUCHER_CODE.getValue(),
                invalidVoucherPaymentData
        );

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertNotNull(result);
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), testOrder.getStatus());
    }

    @Test
    void testSetStatusWithValidStatus() {
        Payment existingPayment = new Payment(UUID.randomUUID().toString(),
                PaymentMethod.VOUCHER_CODE.getValue(),
                PaymentStatus.WAITING.getValue(),
                validVoucherPaymentData
        );

        doReturn(existingPayment).when(paymentRepository).getPayment(existingPayment.getId());

        Payment updatedPayment = new Payment(
                existingPayment.getId(),
                existingPayment.getMethod(),
                PaymentStatus.SUCCESS.getValue(),
                existingPayment.getPaymentData()
        );
        doReturn(updatedPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(existingPayment, PaymentStatus.SUCCESS.getValue());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
    }

    @Test
    void testSetStatusWithInvalidStatus() {
        Payment existingPayment = new Payment(UUID.randomUUID().toString(),
                PaymentMethod.BANK_TRANSFER.getValue(),
                PaymentStatus.WAITING.getValue(),
                validBankPaymentData
        );

        doReturn(existingPayment).when(paymentRepository).getPayment(existingPayment.getId());

        Payment result = paymentService.setStatus(existingPayment, "UNKNOWN_STATUS");

        verify(paymentRepository, times(0)).save(any(Payment.class));
        assertEquals(PaymentStatus.WAITING.getValue(), result.getStatus());
    }

    @Test
    void testGetPaymentWithValidId() {
        String validPaymentId = UUID.randomUUID().toString();
        Payment validPayment = new Payment(validPaymentId, PaymentMethod.VOUCHER_CODE.getValue(), PaymentStatus.SUCCESS.getValue(), validVoucherPaymentData);

        when(paymentRepository.getPayment(validPaymentId)).thenReturn(validPayment);

        Payment result = paymentService.getPayment(validPaymentId);

        assertNotNull(result);
        assertEquals(validPaymentId, result.getId());
    }

    @Test
    void testGetPaymentWithNonUsedValidId() {
        String nonUsedPaymentId = UUID.randomUUID().toString();

        when(paymentRepository.getPayment(nonUsedPaymentId)).thenReturn(null);

        Payment result = paymentService.getPayment(nonUsedPaymentId);

        assertNull(result);
    }

    @Test
    void testGetAllPayments() {
        Payment p1 = new Payment("3c31bd31-b2fe-4998-afcc-c2f65aea2168",
                PaymentMethod.VOUCHER_CODE.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                validVoucherPaymentData
        );

        Payment p2 = new Payment("50b18a6d-67d1-4639-a381-e19e4ace8c9b",
                PaymentMethod.BANK_TRANSFER.getValue(),
                PaymentStatus.WAITING.getValue(),
                validBankPaymentData
        );

        List<Payment> paymentList = Arrays.asList(p1, p2);

        doReturn(paymentList).when(paymentRepository).getAllPayments();

        List<Payment> result = paymentService.getAllPayments();

        verify(paymentRepository, times(1)).getAllPayments();

        assertEquals(2, result.size());

        assertEquals(p1.getId(), result.get(0).getId());
        assertEquals(p1.getMethod(), result.get(0).getMethod());
        assertEquals(p1.getStatus(), result.get(0).getStatus());
        assertEquals(p1.getPaymentData().get("voucherCode"), result.get(0).getPaymentData().get("voucherCode"));

        assertEquals(p2.getId(), result.get(1).getId());
        assertEquals(p2.getMethod(), result.get(1).getMethod());
        assertEquals(p2.getStatus(), result.get(1).getStatus());
        assertEquals(p2.getPaymentData().get("bankName"), result.get(1).getPaymentData().get("bankName"));
    }

}
