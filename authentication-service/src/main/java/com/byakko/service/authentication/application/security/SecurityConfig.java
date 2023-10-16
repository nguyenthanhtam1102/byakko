package com.byakko.service.authentication.application.security;

import com.byakko.common.domain.valueobject.SystemPermission;
import com.byakko.common.domain.valueobject.SystemRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.NullSecurityContextRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter getJwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().csrf().disable()
                .securityContext()
                .securityContextRepository(new NullSecurityContextRepository()) // Cấu hình không lưu trữ context
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/users", "/users/{id}").hasRole(SystemRole.CUSTOMER.getName())
                .antMatchers().hasRole(SystemRole.SYSTEM_ADMIN.getName())
                .antMatchers().hasRole(SystemPermission.CREATE_PRODUCT.getName())
                .anyRequest().authenticated();

        http.addFilterBefore(getJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
