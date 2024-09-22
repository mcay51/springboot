package tr.com.mcay.springbootmodulerlearning.users.model;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Column(name = "role")
    private String role;

    private Boolean isLocked; // Hesap kilitli mi?
    private Boolean active; // Hesap aktif mi?

    private LocalDate expiryDate; // Hesap süresi
    private LocalDate passwordExpiryDate; // Parola süresi

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return expiryDate == null || !expiryDate.isBefore(LocalDate.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return passwordExpiryDate == null || !passwordExpiryDate.isBefore(LocalDate.now());
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
