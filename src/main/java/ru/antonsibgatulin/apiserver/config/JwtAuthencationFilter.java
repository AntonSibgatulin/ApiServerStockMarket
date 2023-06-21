package ru.antonsibgatulin.apiserver.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.antonsibgatulin.apiserver.data.token.Token;
import ru.antonsibgatulin.apiserver.data.token.repository.TokenRepository;
import ru.antonsibgatulin.apiserver.data.user.User;
import ru.antonsibgatulin.apiserver.data.user.repository.UserRepository;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthencationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        User user = null;

        var token = request.getParameter("token");
        String email = null;
        if(token == null){
            filterChain.doFilter(request,response);
            return;
        }

        try{
            email = jwtService.extractEmail(token);
        }catch (Exception exception){
            filterChain.doFilter(request,response);
            return;

        }

        if(email != null){
            Token tokenEntity =  tokenRepository.getTokenByToken(token);
            if(token != null) {

                user = tokenEntity.getUser();
            }
        }


        if(user!=null && SecurityContextHolder.getContext().getAuthentication() == null){

            if(jwtService.isTokenValid(token,user)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
        return;


    }
}
