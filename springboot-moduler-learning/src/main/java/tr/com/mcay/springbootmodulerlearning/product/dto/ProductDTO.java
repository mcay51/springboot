package tr.com.mcay.springbootmodulerlearning.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private double price;

}
