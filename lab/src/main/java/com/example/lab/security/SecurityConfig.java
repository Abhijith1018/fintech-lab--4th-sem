package com.example.lab.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(customizer -> {
                    customizer.requestMatchers("/v1/users/login").permitAll();
                    customizer.requestMatchers("/v1/admin/login").permitAll();
                    customizer.anyRequest().authenticated();
                }
        );
        // http.authorizeRequests(request -> request.anyRequest().authenticated());
        // http.formLogin(Customizer.withDefaults());
        http.formLogin(customizer -> {
            customizer.loginPage("/v1/users/login").permitAll();
        });
        http.httpBasic(Customizer.withDefaults());

        // http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

   /*  @Bean
    public UserDetailsService userDetailsService()
    {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                                .username("Hikaru")
                                .password("H321")
                                .roles("ADMIN")
                                .build();

        return new InMemoryUserDetailsManager(user1);
    } */

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    // springboot requires an object of Authentication Provider, which we are preparing above
    // SInce we are using db we use the Dao one
    // also for the Authentication provider we set a service for userDetails
}


