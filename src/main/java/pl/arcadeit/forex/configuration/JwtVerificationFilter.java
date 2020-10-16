package pl.arcadeit.forex.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pl.arcadeit.forex.configuration.SecurityConstants.*;

public class JwtVerificationFilter extends BasicAuthenticationFilter {

    private final UserDetailsService userDetailsService;

    public JwtVerificationFilter(final AuthenticationManager authenticationManager, final UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken token = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(final HttpServletRequest request) {
        final String token = request.getHeader(HEADER_STRING);
        final String user = (token == null) ? null : getUsernameFromToken(token);
        return (user == null) ? null : getUsernamePasswordAuthenticationToken(user);
    }

    private String getUsernameFromToken(final String token) {
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(final String user) {
        return new UsernamePasswordAuthenticationToken(user, null, userDetailsService.loadUserByUsername(user).getAuthorities());
    }
}
