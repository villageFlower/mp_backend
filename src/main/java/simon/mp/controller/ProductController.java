package simon.mp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import simon.mp.APIList;
import simon.mp.entity.Image;
import simon.mp.entity.Product;
import simon.mp.service.ProductService;

import java.util.List;

@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    ProductService productService;


    @GetMapping(APIList.GET_ALL_PRODUCTS)
    public List<Product> GetAllProducts() {
        return productService.getAllProducts();
    }

}
