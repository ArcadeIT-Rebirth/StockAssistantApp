package pl.arcadeit.forex.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(@Qualifier("customUserDetailsService") final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2").hasRole("ADMIN")
                .antMatchers("/api/user/register", "/api/user/login", "/api/user/login-form", "/stock/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin().successForwardUrl("/h2").failureForwardUrl("/api/user")
                .and()
                .headers().frameOptions().disable();
    }

    @Override
    public UserDetailsService userDetailsServiceBean() {
        return userDetailsService;
    }
}
