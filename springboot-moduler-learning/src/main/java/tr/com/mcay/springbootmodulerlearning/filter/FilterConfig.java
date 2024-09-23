package tr.com.mcay.springbootmodulerlearning.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns("/*");  // Tüm URL'ler için geçerli
        registrationBean.setOrder(1);  // Filtreler sıralı çalışır, bu ilk olacak şekilde ayarlanabilir

        return registrationBean;
    }
}

