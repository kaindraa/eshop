package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    public Product create(Product product);
    public Product findById(String productId);
    public void edit(String productId, Product submittedProduct);
    public List<Product> findAll();
}
