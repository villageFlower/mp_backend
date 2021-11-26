package simon.mp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simon.mp.entity.Product;
import simon.mp.entity.User;
import simon.mp.repo.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
