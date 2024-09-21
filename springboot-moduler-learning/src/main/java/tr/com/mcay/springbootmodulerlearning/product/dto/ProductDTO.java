package tr.com.mcay.springbootmodulerlearning.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {

    private Long id;
    @NotBlank(message = "Product name cannot be empty")
    private String name;
    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than zero")
    private double price;

}
