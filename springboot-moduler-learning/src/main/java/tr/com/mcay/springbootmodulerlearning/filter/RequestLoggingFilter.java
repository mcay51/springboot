package tr.com.mcay.springbootmodulerlearning.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class RequestLoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("------------Filter Start ---------------");
        System.out.println("Incoming request: " + request.getRemoteAddr());
        System.out.println("-------------Filter End -----------------");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

