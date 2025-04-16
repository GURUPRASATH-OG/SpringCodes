package com.SpringBootJwt.security;

import com.SpringBootJwt.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
private final JwtUtils jwtUtils;
private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, CustomUserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String token = request.getHeader("Authorization");
        if(token !=null && token.startsWith("Bearer"))
        {
            token = token.substring(7);
            String username = jwtUtils.extractUsername(token);
            if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null)
            {
                UserDetails userDetail =userDetailsService.loadUserByUsername(username);
                if(jwtUtils.isValidToken(token))
                {
                    UsernamePasswordAuthenticationToken AuthToken = new UsernamePasswordAuthenticationToken(userDetail,null, userDetail.getAuthorities());
                    AuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(AuthToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
