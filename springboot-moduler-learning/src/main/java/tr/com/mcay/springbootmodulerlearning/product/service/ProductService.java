package tr.com.mcay.springbootmodulerlearning.product.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import tr.com.mcay.springbootmodulerlearning.product.dto.ProductDTO;
import tr.com.mcay.springbootmodulerlearning.product.exceptions.ProductNotFoundException;
import tr.com.mcay.springbootmodulerlearning.product.model.Product;
import tr.com.mcay.springbootmodulerlearning.product.repository.ProductRepository;
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {

        return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    public ProductDTO createProduct(ProductDTO  productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        return convertToDTO(product);
    }
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        Product updatedProduct = productRepository.save(product);
        return convertToDTO(updatedProduct);
    }
    public String deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));

        productRepository.delete(product);
        return "Product deleted successfully";
    }
    // Dönüşüm metotları (Entity -> DTO ve DTO -> Entity)
    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return product;
    }
}
