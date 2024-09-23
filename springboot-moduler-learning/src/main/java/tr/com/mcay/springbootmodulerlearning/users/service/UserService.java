package tr.com.mcay.springbootmodulerlearning.users.service;



import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.mcay.springbootmodulerlearning.security.jwt.service.JwtUtil;
import tr.com.mcay.springbootmodulerlearning.users.repository.UserRepository;
import tr.com.mcay.springbootmodulerlearning.users.model.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository; // Kullanıcı veritabanı erişimi
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(JwtUtil jwtUtil, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(String username, String password) {
        // Kullanıcıyı doğrula ve JWT token oluştur
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()) {
            User user=userOptional.get();
            if (user != null &&   passwordEncoder.matches(password, user.getPassword())) {
                return jwtUtil.generateToken(username);
            } else {
                throw new RuntimeException("Geçersiz kullanıcı adı veya şifre");
            }
        }else{
            throw new  UsernameNotFoundException("Kullanıcı DB de bulunamadı.");
        }
    }
    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Şifreyi hash'le
        userRepository.save(user); // Kullanıcıyı kaydet
        return "Kullanıcı başarıyla kaydedildi!";
    }

    public Optional<User> getUserById(Long id) {
       return userRepository.findById(id);
    }
}

