package com.project.config;

import com.project.admin.service.AdminService;
import com.project.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    @Order(1)
    public class AdminConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        public AdminService adminService;

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }


        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setUserDetailsService(this.adminService);
            daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
            return daoAuthenticationProvider;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher("/admin/**")
                    .authorizeRequests()
                    .anyRequest()
                    .hasRole("ADMIN")

                    .and()
                    .formLogin()
                    .loginPage("/admin-login")
                    .loginProcessingUrl("/admin/dologin")
                    .defaultSuccessUrl("/admin/home")

                    .and()
                    .logout()
                    .logoutUrl("/admin/logout")
                    .deleteCookies("JSESSIONID")

                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")

                    .and()
                    .csrf().disable();


        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(this.authenticationProvider());

        }
    }

    @Configuration
    @Order(2)
    public class StudentConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        public BCryptPasswordEncoder bCryptPasswordEncoder;

        @Autowired
        public StudentService studentService;

        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setUserDetailsService(this.studentService);
            daoAuthenticationProvider.setPasswordEncoder(this.bCryptPasswordEncoder);
            return daoAuthenticationProvider;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher("/student/**")
                    .authorizeRequests()
                    .anyRequest()
                    .hasRole("STUDENT")

                    .and()
                    .formLogin()
                    .loginPage("/student-login")
                    .loginProcessingUrl("/student/dologin")
                    .defaultSuccessUrl("/student/home")

                    .and()
                    .logout()
                    .logoutUrl("/student/logout")
                    .deleteCookies("JSESSIONID")

                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")

                    .and()
                    .csrf().disable();


        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(this.authenticationProvider());

        }
    }

}
