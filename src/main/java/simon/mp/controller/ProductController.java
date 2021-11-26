package simon.mp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import simon.mp.APIList;
import simon.mp.entity.Image;
import simon.mp.entity.Product;
import simon.mp.service.ProductService;
import simon.mp.util.Constants;

import java.util.List;

@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    ProductService productService;


    @GetMapping(APIList.GET_ALL_PRODUCTS)
    public Page<Product> GetAllProducts(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = Constants.Page_Size) int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        return productService.getAllProducts(paging);
    }

}
