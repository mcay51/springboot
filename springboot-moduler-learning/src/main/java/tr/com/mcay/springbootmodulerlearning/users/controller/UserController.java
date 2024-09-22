package tr.com.mcay.springbootmodulerlearning.users.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import tr.com.mcay.springbootmodulerlearning.users.model.User;
import org.springframework.web.bind.annotation.*;
import tr.com.mcay.springbootmodulerlearning.users.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public String getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return "Current User: " + userDetails.getUsername();
    }
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return userService.register(user);
    }
}
