package com.varun.spring.security.SpringSecurityVarun;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    
    @Autowired
    private DataSource dataSource;
    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
     {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .withDefaultSchema()
            .withUser(
                User.withUsername("user")
                    .password("pass")
                    .roles("USER")
                    )
            .withUser(
                User.withUsername("admin")
                    .password("pass")
                    .roles("ADMIN")
            );
     }

     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
     {
        http.authorizeRequests()
        .antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/user").hasAnyRole("USER","ADMIN")
        .antMatchers("/").permitAll()
        .and().formLogin();

        return http.build();
     }

     @Bean
     public PasswordEncoder getPasswordEncoder()
        {
            return NoOpPasswordEncoder.getInstance();
        }

    /* 
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
            .build();
    }
    
    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        UserDetails user1 = User.withDefaultPasswordEncoder()
            .username("user")
            .password("pass1")
            .roles("USER")
            .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("pass2")
            .roles("ADMIN")
            .build();    
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.createUser(user1);
        users.createUser(user2);
        return users;
    }
   */

    //in memory authentication 
    /* 
    @Bean
    public InMemoryUserDetailsManager userDetailsService()
    {
        UserDetails user1  = User.withUsername("blah")
                                .password("blah")
                                .roles("ADMIN")
                                .build();

        UserDetails user2  = User.withUsername("foo")
                                .password("foo")
                                .roles("USER")
                                .build();                       
        return new InMemoryUserDetailsManager(user1,user2);
    }

    */
    
    /* 
    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
    */

    //working of authorization using HttpSecurity
    /* 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/admin").hasRole("ROLE_ADMIN")
        .antMatchers("/user").hasAnyRole("ROLE_USER","ROLE_ADMIN")
        .antMatchers("/").permitAll()
        .and().formLogin();

        return http.build();
     }
     */
    
    
}
