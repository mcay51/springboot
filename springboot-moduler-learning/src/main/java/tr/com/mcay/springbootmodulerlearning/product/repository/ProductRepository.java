package tr.com.mcay.springbootmodulerlearning.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.mcay.springbootmodulerlearning.product.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
