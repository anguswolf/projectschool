/*
package com.exercise.projectschool.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Configuration
@EnableWebSecurity
@Order(101)
public class BasicAuthWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${project-school.basic-auth.user}")
    private String basicUser;

    @Value("${project-school.basic-auth.password}")
    private String basicPassword;

    @Value("${project-school.basic-auth.role}")
    private String basicRoles;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/student/all/**", "/student/add/**").authenticated()   //Sostituire .authenticated()  con .hasRole("...") per accedere alla chiamata in base ai ruoli
                .anyRequest().permitAll()
                .and()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(basicUser)
                .password(basicPassword)    //{noop} sta per "no-op" nessuna operazione e indica che la password non è crittografata
                .roles(basicRoles);
    }

}


*/
