package pl.arcadeit.forex.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.arcadeit.forex.exception.UserException;
import pl.arcadeit.forex.model.LoginForm;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static pl.arcadeit.forex.configuration.SecurityConstants.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(final AuthenticationManager authenticationManager, final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request,
                                                final HttpServletResponse response) throws AuthenticationException {
        try {
            final LoginForm credentials = new ObjectMapper().readValue(request.getInputStream(), LoginForm.class);
            return authenticationManager.authenticate(getUserToken(credentials));
        } catch (IOException e) {
            throw new UserException("Username or password is invalid.");
        }
    }

    private UsernamePasswordAuthenticationToken getUserToken(final LoginForm login) {
        return new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword(), userDetailsService.loadUserByUsername(login.getEmail()).getAuthorities());
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request,
                                            final HttpServletResponse response,
                                            final FilterChain chain,
                                            final Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((UserDetails) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
