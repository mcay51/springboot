package tr.com.mcay.springbootmodulerlearning.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/health")
public class HealthCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isDatabaseHealthy = checkDatabaseHealth();


        if (isDatabaseHealthy) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Application is healthy!");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Database connection is down!");
        }
    }

    private boolean checkDatabaseHealth() {
        String url = "jdbc:postgresql://localhost:5432/microcommerce";  // Veritabanı URL'nizi buraya ekleyin
        String username = "postgres";  // Veritabanı kullanıcı adınız
        String password = "postgres";  // Veritabanı şifreniz

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Db bağlantısı kontrol ediliyor");
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            // Bağlantı sırasında bir hata oluştuysa, false döndürüyoruz.
            e.printStackTrace();
            return false;
        }
    }
}
