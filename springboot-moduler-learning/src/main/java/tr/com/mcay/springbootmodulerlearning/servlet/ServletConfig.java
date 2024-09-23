package tr.com.mcay.springbootmodulerlearning.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<HealthCheckServlet> healthCheckServlet() {
        return new ServletRegistrationBean<>(new HealthCheckServlet(), "/actuator/health");
    }
}
