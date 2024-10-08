package tr.com.mcay.springbootmodulerlearning.product.service;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tr.com.mcay.springbootmodulerlearning.product.dto.ProductDTO;
import tr.com.mcay.springbootmodulerlearning.product.exceptions.ProductNotFoundException;
import tr.com.mcay.springbootmodulerlearning.product.model.Product;
import tr.com.mcay.springbootmodulerlearning.product.repository.ProductRepository;
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        logger.info("Fetching all products");
        return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    public ProductDTO createProduct(ProductDTO  productDTO) {
        logger.info("Creating new product: {}", productDTO.getName());
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }
    public ProductDTO getProductById(Long id) {
        logger.debug("Fetching product with ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        return convertToDTO(product);
    }
    @Cacheable ("products")
    public ProductDTO getProductByIdCache(Long id){
        logger.debug("getProductByIdCache() Fetching product with ID: {}", id);
        // Simülasyon: bir gecikme ekleyelim
        try {
            Thread.sleep(2000);  // Veri çekme işleminin uzun sürdüğünü simüle eder
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        return convertToDTO(product);
    }
    @Cacheable (value="productCache",key="#id")
    public ProductDTO getProductByIdRedisCache(Long id){
        logger.debug("getProductByIdRedisCache() Fetching product with ID: {}", id);
        // Simülasyon: bir gecikme ekleyelim
        try {
            Thread.sleep(2000);  // Veri çekme işleminin uzun sürdüğünü simüle eder
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        return convertToDTO(product);
    }
    @CachePut(value = "productCache", key = "#id")
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        logger.debug("Updating product with ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        Product updatedProduct = productRepository.save(product);
        logger.info("Product updated: {}", id);
        return convertToDTO(updatedProduct);
    }
    public String deleteProduct(Long id) {
        logger.warn("Deleting product with ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));

        productRepository.delete(product);
        logger.info("Product deleted: {}", id);
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
  /*  @CacheEvict(value = "productCache", allEntries = true)
    public void clearAllProductsCache() {
        // Tüm ürün cache'ini temizler
    }*/
    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return product;
    }


}
