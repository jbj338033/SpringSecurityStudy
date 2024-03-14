package com.jbj338033.springsecuritystudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
                // For everyone
                .requestMatchers("/login", "/join").permitAll()

                // For logged in user
                .requestMatchers("/").hasRole("USER")

                // For admin
                .requestMatchers("/admin").hasRole("ADMIN")

                // Else
                .anyRequest().authenticated());

        http.sessionManagement((auth) -> auth.maximumSessions(1).maxSessionsPreventsLogin(true));

        http.sessionManagement((auth) -> auth.sessionFixation().changeSessionId());

//        http.csrf(AbstractHttpConfigurer::disable);

//        http.logout((auth) -> auth.logoutUrl("/logout").logoutSuccessUrl("/"));

//        http.formLogin((auth) -> auth.loginPage("/login").loginProcessingUrl("/login").permitAll());

        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();

        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

        return hierarchy;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
