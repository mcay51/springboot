package tr.com.mcay.springbootmodulerlearning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tr.com.mcay.springbootmodulerlearning.security.jwt.service.JwtUtil;
import tr.com.mcay.springbootmodulerlearning.users.model.User;
import tr.com.mcay.springbootmodulerlearning.users.repository.UserRepository;
import tr.com.mcay.springbootmodulerlearning.users.service.UserService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    /**
     * Argument Captors: Mock nesnelerinin metotlarına gönderilen argümanları yakalamak için kullanılır.
     */
    @Captor
    private ArgumentCaptor<User> userCaptor;
    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("admin");
        testUser.setPassword("password"); // Test için açık metin
        testUser.setRole("ROLE_USER");
        testUser.setActive(true);
        testUser.setIsLocked(false);
        testUser.setExpiryDate(LocalDate.now().plusDays(5));
        testUser.setPasswordExpiryDate(LocalDate.now().plusMonths(3));

    }

    @Test
    void testAuthenticate_Success() {
      /*  // Arrange
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches("password", testUser.getPassword())).thenReturn(true);
        when(jwtUtil.generateToken("admin")).thenReturn("dummyToken");

        // Act
        String token = userService.authenticate("admin", "password");

        // Assert
        assertEquals("dummyToken", token);
        verify(userRepository).findByUsername("admin");
        verify(passwordEncoder).matches("password", testUser.getPassword());
   */ }

    @Test
    void testAuthenticate_UserNotFound() {
     /*   // Arrange
        when(userRepository.findByUsername("admin")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.authenticate("admin", "password");
        });
        assertEquals("Kullanıcı DB de bulunamadı.", exception.getMessage());
   */ }

    @Test
    void testAuthenticate_InvalidPassword() {
     /*   // Arrange
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches("wrongPassword", testUser.getPassword())).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.authenticate("admin", "wrongPassword");
        });
        assertEquals("Geçersiz kullanıcı adı veya şifre", exception.getMessage());
   */ }

    @Test
    void testRegister_Success() {
      /*  // Arrange
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        String result = userService.register(testUser);

        // Assert
        assertEquals("Kullanıcı başarıyla kaydedildi!", result);
        verify(passwordEncoder).encode("password");
        verify(userRepository).save(testUser);
   */ }

    @Test
    public void testCaptorRegisterUser() {
       /* User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");

        userService.register(user);

        verify(userRepository).save(userCaptor.capture());
        assertEquals("testUser", userCaptor.getValue().getUsername());
   */ }

    /**
     * Spies: Gerçek nesneleri taklit etmenizi sağlar. Spies ile gerçek metotları çağırabilir ve sadece belirli metotları mocklayabilirsiniz.
     */
    @Test
    public void testUserServiceWithSpy() {
      /*  UserService spyUserService = spy(new UserService(jwtUtil, userRepository, passwordEncoder));

        doReturn("mockToken").when(spyUserService).authenticate(anyString(), anyString());

        String token = spyUserService.authenticate("user", "password");
        System.out.println("Token : "+token);
        assertEquals("mockToken", token);
   */ }
}
