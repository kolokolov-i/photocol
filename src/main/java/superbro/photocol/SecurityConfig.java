package superbro.photocol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/index", "/images/*", "/styles/*", "/scripts/*").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/index")
                    .and()
                .csrf().disable();
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/index", "/images/*", "/styles/*", "/scripts/*").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .rememberMe();
    }

//    @Autowired
//    public void configureGlobal(
//            AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user@foo").password("password").roles("USER").and()
//                .withUser("admin@foo").password("password").roles("USER", "ADMIN");
//    }
}
