package com.varun.spring.security.SpringSecurityVarun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration {

    //in memory authentication 
    @Bean
    public InMemoryUserDetailsManager userDetailsService()
    {
        UserDetails user  = User.withUsername("blah")
                                .password("blah")
                                .roles("USER")
                                .build();
        return new InMemoryUserDetailsManager(user);
    }

    
    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
    
    
}
