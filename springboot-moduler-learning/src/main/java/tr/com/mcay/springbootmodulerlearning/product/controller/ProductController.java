package tr.com.mcay.springbootmodulerlearning.product.controller;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tr.com.mcay.springbootmodulerlearning.product.dto.ProductDTO;
import tr.com.mcay.springbootmodulerlearning.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    @GetMapping("/cache/{id}")
    public ProductDTO getProductByIdCache(@PathVariable Long id) {
        return productService.getProductByIdCache(id);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
}

