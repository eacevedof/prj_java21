package com.eduardoaf.balance.boot_config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    // https://medium.com/@yohanesdwikiwitman/spring-boot-3-template-part-4-configuring-spring-security-72572a60d18b
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());

        http.sessionManagement(sessionManager  -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(
            exceptionHandling -> exceptionHandling.authenticationEntryPoint(
                (request, response, exception) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage())
            )
        );
        http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
                .requestMatchers(
                        "/api/v1/income/create",
                        "/api/v1/**",
                    "/api/authentication/**"
                ).permitAll()
                .anyRequest().authenticated()
        );
        /*
        if (yamlConfig.getPublicApiList().size() > 0) {
            http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
                    .requestMatchers(String.join(", ", yamlConfig.getPublicApiList())).permitAll()
                    .anyRequest().authenticated()
            );
            //LOGGER.info("Added public API: '{}' ", String.join("', '", yamlConfig.getPublicApiList()));
        }
        //http.addFilterBefore(new SecurityFilter(), UsernamePasswordAuthenticationFilter.class); // custom protocol Authorization

         */
        return http.build();
    }
}