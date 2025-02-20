package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product create(Product product);
    List<Product> findAll();
    void delete(String product);
    Product findById(String productId);
    void edit(String productId, Product submittedProduct);

}
