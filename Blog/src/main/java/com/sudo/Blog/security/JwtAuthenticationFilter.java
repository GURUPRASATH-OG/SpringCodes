package com.sudo.Blog.security;

import com.sudo.Blog.services.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private final AuthenticationService authenticationService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        try {
            String token = extractToken(request);
            if (token != null) {
                UserDetails userDetails = authenticationService.validateToken(token);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                if (userDetails instanceof BlogUserDetails) {
                    request.setAttribute("userId", ((BlogUserDetails) userDetails).getId());
                }
            }
        }catch (Exception ex)
        {
            //Do not throw exception, just don t authenticate user.
            logger.warn("Received invalid auth token");
        }
        filterChain.doFilter(request,response);
    }
    private String  extractToken(HttpServletRequest request)
    {
        String bearToken= request.getHeader("Authorization");
        if(bearToken!=null&&bearToken.startsWith("Bearer "))
        {
            return bearToken.substring(7);
        }
        return null;
    }
}
