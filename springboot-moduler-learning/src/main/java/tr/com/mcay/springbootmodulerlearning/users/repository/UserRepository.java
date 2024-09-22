package tr.com.mcay.springbootmodulerlearning.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.mcay.springbootmodulerlearning.users.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
