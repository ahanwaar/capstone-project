package com.worldnavigator.webapp.security;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;

import com.worldnavigator.webapp.jwt.JwtConfiguration;
import com.worldnavigator.webapp.jwt.JwtConfigurer;
import com.worldnavigator.webapp.services.UserService;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(JwtConfiguration.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtConfiguration jwtConfiguration;
    private final UserService userService;

    @Autowired
    public SecurityConfiguration(JwtConfiguration jwtConfiguration, UserService userService) {
        this.jwtConfiguration = jwtConfiguration;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .apply(new JwtConfigurer(jwtConfiguration, secretKey(), userDetailsService()));

        // Authentication endpoints security
        http
            .csrf().disable()
            .authorizeRequests()
            .mvcMatchers("/auth/login")
                .permitAll();

        // Accounts endpoints security
        http
            .authorizeRequests()
                .mvcMatchers(DELETE, "/users/@{username}")
                    .access("#username == principal?.username or hasRole('ROLE_ADMIN')")
                .mvcMatchers(GET, "/users/me")
                    .authenticated()
                .mvcMatchers("/users/**")
                    .permitAll();

        // Game authentication
        http
            .authorizeRequests()
                .mvcMatchers("/games/**")
                    .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }

    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfiguration.getSecret()));
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
