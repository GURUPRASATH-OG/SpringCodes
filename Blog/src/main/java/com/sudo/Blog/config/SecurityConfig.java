package com.sudo.Blog.config;

import com.sudo.Blog.domain.entites.User;
import com.sudo.Blog.mappers.CategoryMapper;
import com.sudo.Blog.repository.UserRepository;
import com.sudo.Blog.security.BlogUserDetails;
import com.sudo.Blog.security.BlogUserDetailsService;
import com.sudo.Blog.security.JwtAuthenticationFilter;
import com.sudo.Blog.services.AuthenticationService;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService)
    {
        return new JwtAuthenticationFilter(authenticationService);
    }

    @Bean
    public BlogUserDetailsService userDetails(UserRepository userRepository)
    {
        BlogUserDetailsService blogUserDetailsService = new  BlogUserDetailsService(userRepository);
        String email = "user@test.com";
        userRepository.findByEmail(email).orElseGet(()->
        {
           User newUser = User.builder()
                   .name("Test")
                   .email(email)
                   .password(passwordEncoder().encode("password"))
                   .build();
           return userRepository.save(newUser);
        });
        return  blogUserDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception
    {
        http.authorizeHttpRequests(customizer-> customizer
                        .requestMatchers(HttpMethod.POST,"/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/posts/drafts").authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/v1/posts/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/v1/categories/**").permitAll()
                .requestMatchers(HttpMethod.GET,"api/v1/tags/**").permitAll()
                .anyRequest().authenticated())
                .headers(headers-> headers.frameOptions(custom->custom.disable()))
                .csrf(csrf-> csrf.disable())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); //by default uses the bcryptPasswordEncoder.
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }

}
