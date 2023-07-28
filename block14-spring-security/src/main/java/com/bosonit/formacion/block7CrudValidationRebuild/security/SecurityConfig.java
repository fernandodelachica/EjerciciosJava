package com.bosonit.formacion.block7CrudValidationRebuild.security;

import com.bosonit.formacion.block7CrudValidationRebuild.security.filters.JwtAuthenticationFilter;
import com.bosonit.formacion.block7CrudValidationRebuild.security.filters.JwtAuthorizationFilter;
import com.bosonit.formacion.block7CrudValidationRebuild.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtAuthorizationFilter authorizationFilter;

    //Configuración donde añadimos roles, filtros, etc.
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception{

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
                .csrf(config -> config.disable()) //Deshabilita el Cross-Site Request Forgery
                .authorizeHttpRequests( auth -> {
                    //Configuración de permisos según roles
                    auth.requestMatchers(POST, "/person", "/instructor", "/student", "/subject").hasRole("ADMIN");
                    auth.requestMatchers(PUT, "/person", "/instructor", "/student", "/subject").hasRole("ADMIN");
                    auth.requestMatchers(GET, "/person", "/instructor", "/student", "/subject").hasAnyRole("ADMIN", "USER");
                    auth.anyRequest().authenticated();
                })
                .sessionManagement( session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS); //La app no mantiene la información de sesión entre solicitudes
                })
                .addFilter(jwtAuthenticationFilter)     //Filtro de Authentication
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)   //Filtro de Authorization
                .build();
    }

    //Encripta de contraseñas
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and().build();
    }

}
