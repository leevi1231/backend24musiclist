package com.example.musiclist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.musiclist.web.UserDetailService;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration
public class WebSecConfig {

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                authorizeRequest -> authorizeRequest.requestMatchers(antMatcher("/css/**")).permitAll()
                        .anyRequest().authenticated())
                .headers(headers -> headers.frameOptions(frameoptions -> frameoptions.disable()))
                .formLogin(formlogin -> formlogin.defaultSuccessUrl("/songlist", true).permitAll())
                .logout(logout -> logout.permitAll());
        http.cors().disable().csrf().disable().httpBasic();
        return http.build();
    }

    @Autowired
    private UserDetailService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}