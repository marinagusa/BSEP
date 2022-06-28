package com.example.bsep.security;

import com.example.bsep.security.auth.RestAuthenticationEntryPoint;
import com.example.bsep.security.auth.TokenAuthenticationFilter;
import com.example.bsep.service.CustomUserDetailsService;
import com.example.bsep.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // Definisemo uputstva AuthenticationManager-u:

                // 1. koji servis da koristi da izvuce podatke o korisniku koji zeli da se autentifikuje
                // prilikom autentifikacije, AuthenticationManager ce sam pozivati loadUserByUsername() metodu ovog servisa
                .userDetailsService(customUserDetailsService)

                // 2. kroz koji enkoder da provuce lozinku koju je dobio od klijenta u zahtevu
                // da bi adekvatan hash koji dobije kao rezultat hash algoritma uporedio sa onim koji se nalazi u bazi (posto se u bazi ne cuva plain lozinka)
                .passwordEncoder(passwordEncoder());
    }

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
            .authorizeRequests().antMatchers("/auth/**", "/csr/**").permitAll()
            .anyRequest().authenticated().and()
            .cors().and()
            .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, customUserDetailsService), BasicAuthenticationFilter.class);

        http.csrf().disable();

        http
            .requiresChannel(channel ->
                    channel.anyRequest().requiresSecure());

        http
            .headers()
            .xssProtection()
            .and()
            .contentSecurityPolicy("script-src 'self'");
    }
}
