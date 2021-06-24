package pl.coderslab.charity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@ComponentScan(basePackages = {"pl.coderslab.charity"})
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userdetailsservice")
    private UserDetailsService userDetailsService;


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/donation").access("hasAuthority('ROLE_USER')")
                .antMatchers("/donation/*").access("hasAuthority('ROLE_USER')")
                .antMatchers("/").access("permitAll")
                .antMatchers("/**").access("permitAll")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email").passwordParameter("password")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                //.logout().logoutUrl("/logout")//to działa tylko z POST, więc trzeba by mini formularz z jedym przyciskiem zrobić
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//to musiałem dać zamiast logoutUrl, żeby działał request GET + CSRF
                .clearAuthentication(true).invalidateHttpSession(true)
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/403page")
        ;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }




}
