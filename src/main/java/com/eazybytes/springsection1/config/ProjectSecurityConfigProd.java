package com.eazybytes.springsection1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("prod")
public class ProjectSecurityConfigProd {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("i am in security config");
//        http.authorizeHttpRequests((requests) -> requests
//                .requestMatchers("/myAccount","/myLoans","/myCards","/myLoans","/myPaisa").authenticated()
//                .requestMatchers("/myNotices","/myContact","/checkJpa").permitAll());

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount","/myLoans","/myCards","/myLoans","/myPaisa","/myBalance").authenticated()
                .requestMatchers("/myNotices","/myContact","/checkJpa","/error","/registerUser").permitAll());

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService()
//    {
//        System.out.println("i am in user details service");
//       UserDetails user = User.withUsername("user").password("{noop}EasyBytes12345").authorities("read").build();
//       UserDetails admin= User.withUsername("abhay").password("{bcrypt}$2a$12$PrBq7ZU2jbGfdQJY9T9xxuZPEYWTFMyRDbB6SSvdTUog7T82xUOZy").authorities("admin").build();
//
//       return new InMemoryUserDetailsManager(user,admin);
//    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource)
//    {
//        return new JdbcUserDetailsManager(dataSource);
//    }

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
