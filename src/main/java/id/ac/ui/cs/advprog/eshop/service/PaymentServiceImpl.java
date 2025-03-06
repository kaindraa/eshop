package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        PaymentStatus paymentStatus;

        if (PaymentMethod.VOUCHER_CODE.getValue().equals(method)) {
            paymentStatus = validateVoucherPayment(paymentData);
        } else if (PaymentMethod.BANK_TRANSFER.getValue().equals(method)) {
            paymentStatus = validateBankTransferPayment(paymentData);
        } else {
            paymentStatus = PaymentStatus.REJECTED;
        }

        order.setStatus(paymentStatus == PaymentStatus.SUCCESS ? OrderStatus.SUCCESS.getValue() : OrderStatus.FAILED.getValue());

        Payment payment = new Payment(UUID.randomUUID().toString(), method, paymentStatus.getValue(), paymentData);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        if (PaymentStatus.contains(status)) {
            payment.setStatus(status);
            return paymentRepository.save(payment);
        }
        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.getPayment(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }

    private PaymentStatus validateVoucherPayment(Map<String, String> paymentData) {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP")
                && voucherCode.chars().filter(Character::isDigit).count() == 8) {
            return PaymentStatus.SUCCESS;
        }
        return PaymentStatus.REJECTED;
    }

    private PaymentStatus validateBankTransferPayment(Map<String, String> paymentData) {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");
        if (bankName != null && !bankName.isEmpty() && referenceCode != null && !referenceCode.isEmpty()) {
            return PaymentStatus.SUCCESS;
        }
        return PaymentStatus.REJECTED;
    }
}
