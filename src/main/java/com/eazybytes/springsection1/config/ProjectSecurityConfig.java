package com.eazybytes.springsection1.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount","/myLoans","/myCards","/myLoans","/myPaisa").authenticated()
                .requestMatchers("/myNotices","/myContact").permitAll());

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
       UserDetails user = User.withUsername("abhay").password("{noop}EasyBytes12345").authorities("read").build();
       UserDetails admin= User.withUsername("admin").password("{bcrypt}$2a$12$BxzKrRJOesiIRhlWeHpcduxFl.6uGp83DFAa3b0fsNLtVr5VRTP5m").authorities("admin").build();

       return new InMemoryUserDetailsManager(user,admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker()
    {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

}