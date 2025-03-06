package id.ac.ui.cs.advprog.eshop.model;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Builder
@Getter

public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    public Payment(String id, String method,  Map<String, String> paymentData)
    {
        this.id = id;

        if (PaymentMethod.contains(method)) {
            this.method = method;
        } else {
            throw new IllegalArgumentException();
        }

        this.status = "WAITING";
        this.paymentData = paymentData;

    }

    public Payment(String id, String method, String status, Map<String, String> paymentData)
    {
        this.id = id;

        if (PaymentMethod.contains(method)) {
            this.method = method;
        } else {
            throw new IllegalArgumentException();
        }
        if (PaymentStatus.contains(status)) {
            this.status = status;

        } else {
            throw new IllegalArgumentException();
        }
        this.paymentData = paymentData;

    }

    public void setStatus(String status) {

        if (PaymentStatus.contains(status)) {
            this.status = status;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
};
