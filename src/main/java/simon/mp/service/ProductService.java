package simon.mp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import simon.mp.dataclass.AddProductReq;
import simon.mp.entity.Image;
import simon.mp.entity.Product;
import simon.mp.entity.User;
import simon.mp.repo.CategoryRepository;
import simon.mp.repo.ImageRepository;
import simon.mp.repo.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public Page<Product> getAllProducts(Pageable page) {
        return productRepository.findAllByOrderByCreatedDesc(page);
    }

    public Product addProduct(AddProductReq req){
        Product product = new Product();
        product.setName(req.name);
        product.setOrigial_price(Double.parseDouble(req.origial_price));
        product.setPrice(Double.parseDouble(req.price));
        product.setPublished(req.published);
        product.setStock(req.stock);
        product.setDetail(req.detail);
        product.setCategory(categoryRepository.findById(req.category_id).orElse(null));
        productRepository.save(product);
        req.images.forEach((image_id) -> {
            Image image = imageRepository.findById(image_id).orElse(null);
            if (image != null){
                image.setProduct(product);
                imageRepository.save(image);
            }
        });
        return product;
    }
}
