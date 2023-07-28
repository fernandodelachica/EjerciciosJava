package com.bosonit.formacion.block7CrudValidationRebuild.security.filters;

import com.bosonit.formacion.block7CrudValidationRebuild.person.application.PersonUserDetailsService;
import com.bosonit.formacion.block7CrudValidationRebuild.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PersonUserDetailsService personUserDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        //Extraemos el token del header de la petición
        String tokenHeader = request.getHeader("Authorization");

        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){
            //Coge el valor del token a partir del Bearer
            String token = tokenHeader.substring(7);

            //Valida el token
            if(jwtUtils.isTokenValid(token)){
                //Recuperamos del token el Usuario
                String username = jwtUtils.getUserNameFromToken(token);
                UserDetails userDetails = personUserDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

                //Seteamos la autenticación con los roles
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        //Si tokenHeader es nulo, deniega el acceso
        filterChain.doFilter(request, response);
    }
}
