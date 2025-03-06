package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentRepository {

    private final List<Payment> payments = new ArrayList<>();

    public Payment save(Payment payment) {
        payments.removeIf(existing -> existing.getId().equals(payment.getId()));
        payments.add(payment);
        return payment;
    }

    public Payment getPayment(String Id) {
        return payments.stream()
                .filter(payment -> payment.getId().equals(Id))
                .findFirst().orElse(null);
    }

    public List<Payment> getAllPayments() {
        return List.copyOf(payments);
    }
}
