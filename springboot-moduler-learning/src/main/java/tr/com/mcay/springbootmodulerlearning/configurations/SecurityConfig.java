package tr.com.mcay.springbootmodulerlearning.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF korumasını devre dışı bırakıyoruz
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/public/**").permitAll()  // /public altındaki yollar serbest
                        .anyRequest().authenticated()  // Diğer tüm yollar kimlik doğrulama gerektirir
                )
                .httpBasic(withDefaults());  // Basic Authentication kullanıyoruz

        return http.build();
    }
}

