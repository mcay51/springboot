package tr.com.mcay.springbootmodulerlearning.security.controller;
import org.springframework.web.bind.annotation.*;
import tr.com.mcay.springbootmodulerlearning.security.jwt.service.JwtUtil;
import tr.com.mcay.springbootmodulerlearning.security.model.AuthRequest;
import tr.com.mcay.springbootmodulerlearning.users.model.User;
import tr.com.mcay.springbootmodulerlearning.users.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    private JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        // Kullanıcı doğrulama işlemi burada yapılmalı
        String token = userService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        return token;
    }
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return userService.register(user);
    }
}
