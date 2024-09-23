package tr.com.mcay.springbootmodulerlearning;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tr.com.mcay.springbootmodulerlearning.users.model.User;

import java.time.LocalDate;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegisterUser() throws Exception {
     /*  User testUser = new User();

        testUser.setUsername("testAdmin");
        testUser.setPassword("password"); // Test için açık metin
        testUser.setRole("ROLE_USER");
        testUser.setActive(true);
        testUser.setIsLocked(false);
        testUser.setExpiryDate(LocalDate.now().plusDays(5));
        testUser.setPasswordExpiryDate(LocalDate.now().plusMonths(3));

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(testUser)))
                .andExpect(status().isOk());*/
    }
}
