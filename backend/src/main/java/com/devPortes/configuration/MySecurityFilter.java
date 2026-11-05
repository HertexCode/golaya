package com.devPortes.configuration;

import com.devPortes.users.exceptions.UserNotFoundException;
import com.devPortes.users.model.Client;
import com.devPortes.users.model.IAuthenticated;
import com.devPortes.users.repository.UserJpaRepositoryAdapter;
import com.devPortes.users.security.CustomUserDetails;
import com.devPortes.users.security.TokenImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MySecurityFilter extends OncePerRequestFilter {
    private final TokenImpl tokenService;
    private final UserJpaRepositoryAdapter userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = recoverToken(request);

            if (token != null) {
                String subject = tokenService.getSubject(token);
                IAuthenticated user = userRepository.findByEmail(subject)
                        .orElseThrow(() -> new UserNotFoundException(subject));

                CustomUserDetails customUserDetails = new CustomUserDetails(user);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        customUserDetails, null, customUserDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        }catch (Exception e){
            e.printStackTrace(); throw e;
        }
    }

    public String recoverToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            return authorizationHeader.substring(7).trim();
        }
        return null;
    }

}
