package com.krishna.project.HouZing.security;

import com.krishna.project.HouZing.entity.type.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtAuthFilter jwtAuthFilter;

    //Security filter chain

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity, consider enabling it in production
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless sessions for REST APIs
                )
                .authorizeHttpRequests(auth -> auth

                                .requestMatchers("/auth/signup").hasRole(UserRole.ADMIN.name())

                                .requestMatchers("/auth/**").permitAll() // Allow unauthenticated access to auth endpoints

                                .requestMatchers("/residents/**").hasRole(UserRole.RESIDENT.name()) // Require authentication for residents endpoints

                                .requestMatchers("/admin/**").hasRole(UserRole.ADMIN.name())

                                .requestMatchers("/visitor/register", "/visitor/visitor-exit/**").hasRole(UserRole.GUARD.name())

                                .requestMatchers("/visitor/approve", "/visitor/my", "/visitor/preregister").hasRole(UserRole.RESIDENT.name())

                                .requestMatchers("/visitor/all").hasAnyRole(UserRole.ADMIN.name(), UserRole.GUARD.name())


                         // Require authentication for all other requests

                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((req,res,authEx) ->
                                handlerExceptionResolver.resolveException(req,res,null,authEx))
                        .accessDeniedHandler((req,res,accessEx) ->
                                handlerExceptionResolver.resolveException(req,res,null,accessEx)) )
                ;
        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
