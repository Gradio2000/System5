package com.example.system5.config;


import com.example.system5.util.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;

    public SecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http    .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/registration", "/adduser", "/registrationnext").anonymous()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()

                .and().httpBasic()
//                .and().exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))

                .and().exceptionHandling()
                    .accessDeniedHandler(new AccessDeniedExceptionHandler())

                .and().formLogin()
                    .loginPage("/login").permitAll()
                    .loginProcessingUrl("/perform_login")
                    .defaultSuccessUrl("/loginTrue", true)
                    .failureUrl("/login?error=true")
                    .usernameParameter("login")
                    .passwordParameter("password")

                .and().logout()
                    .logoutSuccessUrl("/login")
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")

                .and().sessionManagement().maximumSessions(1);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}