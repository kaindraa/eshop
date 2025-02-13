package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public  Product findById(UUID productId){
        return productData.stream().filter(p->p.getProductId().equals(productId)).findFirst().orElse(null);
    }

    public Product save(Product product) {
        Product existingProduct = findById(product.getProductId());
        if(existingProduct!=null){
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductQuantity(product.getProductQuantity());
            return existingProduct;
        }
        return null;
    }


    public Iterator<Product> findAll(){
        return productData.iterator();

    }

}
