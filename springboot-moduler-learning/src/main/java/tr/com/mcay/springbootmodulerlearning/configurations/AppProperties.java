package tr.com.mcay.springbootmodulerlearning.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppProperties {

    private String name;
    private String version;
    private String description;


}
