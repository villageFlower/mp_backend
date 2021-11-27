package simon.mp.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import simon.mp.dataclass.AddProductReq;
import simon.mp.dataclass.UpdateProductReq;
import simon.mp.entity.Image;
import simon.mp.entity.Product;
import simon.mp.entity.QProduct;
import simon.mp.entity.User;
import simon.mp.repo.CategoryRepository;
import simon.mp.repo.ImageRepository;
import simon.mp.repo.ProductRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    CategoryRepository categoryRepository;

    Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private EntityManager entityManager;
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    public void afterPropertiesSet() throws Exception {
        jpaQueryFactory = new JPAQueryFactory(entityManager);

    }

    public static QProduct Qproduct = QProduct.product;

    public Page<Product> getAllProducts(Pageable page,Long category_id) {
        logger.info(String.valueOf(category_id));
        if (category_id>0){
            return productRepository.findAllByCategory(category_id,page);
        }
        return productRepository.findAllByOrderByCreatedDesc(page);
    }
    public Product getProductById(long id){
        return  productRepository.getById(id);
    }


    public ResponseEntity<String> deleteProductById(long id){
        try {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        }
        catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }
    }

    public Product addProduct(AddProductReq req){
        Product product = new Product();
        product.setName(req.name);
        product.setOriginal_price(req.original_price);
        product.setPrice(req.price);
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

    public ResponseEntity<Product> editProduct(UpdateProductReq req){

        Product product = productRepository.findById(req.id).orElse(null);
        if (product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Product());
        }
        product.setName(req.name);
        product.setOriginal_price(req.original_price);
        product.setPrice(req.price);
        product.setPublished(req.published);
        product.setStock(req.stock);
        product.setDetail(req.detail);
        product.setCategory(categoryRepository.findById(req.category_id).orElse(null));
        productRepository.save(product);

        product.getImages().forEach(image -> {
            image.setProduct(null);
            imageRepository.save(image);
        });
        req.images.forEach((image_id) -> {
            Image image = imageRepository.findById(image_id).orElse(null);
            if (image != null){
                image.setProduct(product);
                imageRepository.save(image);
            }
        });
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
