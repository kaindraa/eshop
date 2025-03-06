package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {

    private List<Payment> payments = new ArrayList<>();

    public Payment save(Payment Payment) {
        int i = 0;
        for (Payment savedPayment : payments) {
            if (savedPayment.getId().equals(Payment.getId())) {
                payments.remove(i);
                payments.add(i, Payment);
                return Payment;
            }
            i += 1;
        }

        payments.add(Payment);
        return Payment;
    }
    public Payment getPayment(String id) {
        for (Payment savedPayment : payments) {
            if (savedPayment.getId().equals(id)) {
                return savedPayment;
            }
        }
        return null;
    }

    public List<Payment> getAllPayments() {
        List<Payment> result = new ArrayList<>();
        for (Payment savedPayment : payments) {
            result.add(savedPayment);
        }

        return result;
    }
}

