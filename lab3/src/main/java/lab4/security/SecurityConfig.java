package lab4.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // доступ к библиотечной статистике
                        .requestMatchers(HttpMethod.GET, "/api/library/stats").hasAnyRole("USER", "ADMIN")

                        // loan — доступ только к определённым методам для USER
                        .requestMatchers(HttpMethod.POST, "/api/loan").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/loan/top-books", "/api/loan/debtors").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/loan/**").hasRole("ADMIN")

                        // остальные GET-запросы
                        .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("USER", "ADMIN")
                        // остальные запросы — только для ADMIN
                        .requestMatchers("/api/**").hasRole("ADMIN")

                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").authenticated()
                        .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

