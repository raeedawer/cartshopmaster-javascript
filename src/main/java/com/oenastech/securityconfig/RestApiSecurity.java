package com.oenastech.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class RestApiSecurity {


    private DataSource dataSource;
    @Autowired
    public RestApiSecurity(DataSource dataSource) {
        this.dataSource=dataSource;
    }
    @Bean
    public UserDetailsService userDetails(){
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select email,password,active from client where email = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select email,role from roles where email = ?");
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity  chain) throws Exception {

        chain.authorizeHttpRequests( auth -> auth
                        .requestMatchers("/styles/**","/register")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .defaultSuccessUrl("/home")
                        .permitAll()
                ).logout(log -> log
                        .logoutUrl("/logout")
                        .permitAll())
                .authenticationProvider(provider())
                .exceptionHandling(exception -> exception.accessDeniedPage("/accessDenied")
                );
        return chain.build();


    }


    public AuthenticationProvider provider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetails());


        return provider;

    }


}
