package com.springBoot.lms.security;


import com.springBoot.lms.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(
                (authorize -> authorize
                        .requestMatchers("/api/user/signup").permitAll()
                        .requestMatchers("/api/user/get-token").authenticated()
                        .requestMatchers("/api/user/details").authenticated()
                        .requestMatchers("/api/learner/add").permitAll()
                        .requestMatchers("/api/learner/get").hasAuthority("LEARNER")
                        .requestMatchers("/api/author/add").permitAll()
                        .requestMatchers("/api/course/get-all").permitAll()
                        .requestMatchers("/api/course/add").hasAnyAuthority("AUTHOR","EXECUTIVE")
                        .requestMatchers("/api/video/add/{module_id}").hasAnyAuthority("AUTHOR","EXECUTIVE")
                        .anyRequest().authenticated())
        )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults()).build();
    }

    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager getAuthManager(AuthenticationConfiguration auth)
            throws Exception{
        return auth.getAuthenticationManager();
    }
}
