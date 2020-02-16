package openkino.com.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import openkino.com.configuration.JwtConfig;
import openkino.com.models.KinoUser;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

public class BasicAuthenticationVUFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger logger = Logger.getLogger(BasicAuthenticationVUFilter.class);
    private final JwtConfig jwtConfig;
    private AuthenticationManager authManager;

    public BasicAuthenticationVUFilter(AuthenticationManager authManager, JwtConfig jwtConfig) {
        this.authManager = authManager;
        this.jwtConfig = jwtConfig;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            KinoUser kinoUser = new ObjectMapper().readValue(request.getInputStream(), KinoUser.class);

            // 2. Create auth object (contains credentials) which will be used by auth manager
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    kinoUser.getMail(), kinoUser.getPassword(), Collections.emptyList());
            return authManager.authenticate(authToken);
        } catch (IOException e) {
            SecurityContextHolder.clearContext();
            logger.warn(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        if (auth != null){
            KinoUser kinoUser = (KinoUser) auth.getPrincipal();
            if (!kinoUser.getAction()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Пользователь '"+kinoUser.getMail()+"' заблокирован!");
        }
        Long now = System.currentTimeMillis();
        String token = Jwts.builder()
                .setSubject(auth.getName())
                // Convert to list of strings.
                .claim("authorities", auth.getAuthorities())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();

        // Add token to header
        response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
    }
}
