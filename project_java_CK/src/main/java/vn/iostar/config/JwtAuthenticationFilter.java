package vn.iostar.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.iostar.service.User.AuthenticationServiceImpl;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationServiceImpl auth;
    public JwtAuthenticationFilter(AuthenticationServiceImpl auth) {
        this.auth = auth;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = (String) request.getSession().getAttribute("jwt_token"); // Lấy token từ session
        if (token != null && auth.validateToken(token)) {
            String username = auth.extractUsername(token);
            List<GrantedAuthority> authorities = getAuthorities(username); // Lấy quyền từ cơ sở dữ liệu hoặc JWT
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
    private List<GrantedAuthority> getAuthorities(String username) {
        return List.of(new SimpleGrantedAuthority("ROLE_USER")); // Thêm quyền cụ thể vào đây
    }
}
