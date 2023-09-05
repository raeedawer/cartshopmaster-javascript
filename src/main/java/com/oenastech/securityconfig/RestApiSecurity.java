package com.oenastech.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import javax.sql.DataSource;


/**
 * <h2>An online store project <h2/>
 * <p> That displays products with different providers,
 *with a shopping cart and an order page.<p/>
 *
 *
 *
 *
 * @author Raeed Awer
 *
 *@since 1.1
 */
@Configuration
public class RestApiSecurity {

    private final DataSource dataSource;
    @Autowired
    public RestApiSecurity(DataSource dataSource) {
        this.dataSource=dataSource;
    }
    @Bean
    public UserDetailsService userDetails(){
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager
                .setUsersByUsernameQuery("select email,password,active from client where email = ?");
        userDetailsManager
                .setAuthoritiesByUsernameQuery("SELECT client.email,role.name FROM client JOIN role where client.email = ?");
        return userDetailsManager;
    }
    @Bean
    public SecurityFilterChain filterChain( HttpSecurity  chain) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        chain.authorizeHttpRequests( auth -> auth
                        .requestMatchers("/styles/**","/register","/addClient","/error")
                        .permitAll()
                        .requestMatchers("/home").hasAnyRole("user","Admen")
                        .anyRequest()
                        .authenticated())
            .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .defaultSuccessUrl("/home")
                        .permitAll())
            .logout(log -> log
                        .logoutUrl("/logout")
                        .permitAll())
            .authenticationProvider(provider())
            .exceptionHandling(exception -> exception
                        .accessDeniedPage("/accessDenied"))
            .requestCache(cache -> cache
                        .requestCache(requestCache));

        return chain.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    public AuthenticationProvider provider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetails());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
