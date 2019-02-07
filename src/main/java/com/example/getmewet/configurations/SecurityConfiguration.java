package com.example.getmewet.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers( "/api/user/**", "api/plant/**", "api/status/**", "api/day/**").authenticated()
                .antMatchers("/api/register").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .logout();

        httpSecurity
                .authorizeRequests().antMatchers("/", "/register").permitAll()
                // TODO remove API and Register above!
                // TODO enable when not remaking db every time
                //.antMatchers("/reqister", "/api/register").hasAuthority("ADMIN")
                .anyRequest().authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/today")
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}