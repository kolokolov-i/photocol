package superbro.photocol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests()
//                    .antMatchers("/", "/index", "/images/*", "/styles/*", "/scripts/*").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                    .and()
                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/index", "/login").permitAll()
//                .and()
//                .formLogin().loginPage("/login").loginProcessingUrl("/login").permitAll()
//                .and().logout().permitAll()
//                .logoutSuccessUrl("/")
                ;
    }

    @Autowired
    public void configure(
            AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("cat").password("mew").roles("USER").and()
                .withUser("dog").password("gaw").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/scripts/**", "/styles/**", "/images/**");
    }
}
