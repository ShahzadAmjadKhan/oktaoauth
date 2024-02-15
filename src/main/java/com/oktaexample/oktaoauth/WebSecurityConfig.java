package com.oktaexample.oktaoauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Value("${spring.security.ignored:/web/**,/*.ico,"
            + "/oauth/**,${server.servlet.context-path:}/oauth/**,"
            + "/actuator/**,${server.servlet.context-path:}/actuator/**,"
            + "/error,${server.servlet.context-path:}/error}")
    private String[] ignored;

    //private final Oauth2TokenService context;


    /**
     * Class constructor to inject dependencies.
     */
   /* public WebSecurityConfig(Oauth2TokenService context) {
        this.context = context;
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        configure(http);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return configureWebSecurity();
    }

    private WebSecurityCustomizer configureWebSecurity() {
        return this::configure;
    }


    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(ignored);
    }

    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                //.successHandler(context::oauth2Success)
               //.failureHandler(context::oauth2Failure)
                .and()
                .logout().permitAll();
    }
}
