package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {

    private List<Payment> payments = new ArrayList<>();

    public Payment save(Payment Payment) {
        return null;
    }
    public Payment getPayment(String id) {
        return null;
    }

    public List<Payment> getAllPayments() {
        return null;
    }
}

