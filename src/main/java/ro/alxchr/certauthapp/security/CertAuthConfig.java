package ro.alxchr.certauthapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Controller
public class CertAuthConfig {

    private final UserDetailsService userDetailsService;

    public CertAuthConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
                .x509(x509 -> x509
                        .x509PrincipalExtractor(new IssuerSerialPrincipalExtractor())
                        .userDetailsService(userDetailsService)
                );
        return http.build();
    }


}
