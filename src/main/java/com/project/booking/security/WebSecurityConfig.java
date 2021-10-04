package com.project.booking.security;

import com.project.booking.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        
        return authProvider;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{

           http.authorizeRequests()
                   .antMatchers("/","/**/*.js", "/**/*.css", "/**/*.png", "/**/*.PNG").permitAll()
                   .antMatchers("/valid/**").permitAll()
                   .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                   .antMatchers("/reservation/**").hasAnyAuthority("ROLE_USER")
                   .antMatchers("/anonymous*").anonymous()
                   .and()
                   .formLogin()
                   .loginPage("/register")
                   .loginProcessingUrl("/perform_login")
                   .defaultSuccessUrl("/successlogin", true)
                   .and()
                   .logout()
                   .logoutUrl("/logout")
                   .deleteCookies("JSESSIONID")
                   ;
    }
    
}
