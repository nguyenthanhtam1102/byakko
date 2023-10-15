package com.byakko.service.authentication.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.NullSecurityContextRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
                .exceptionHandling()
                .accessDeniedHandler(getCustomAccessDeniedHandler())
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/users", "/users/{id}").hasRole(PermissionConstants.GET_USER)
                .antMatchers(HttpMethod.POST, "/users").hasRole(PermissionConstants.ADD_USER)
                .antMatchers(HttpMethod.PUT, "/users/{id}").hasRole(PermissionConstants.UPDATE_USER)
                .antMatchers(HttpMethod.DELETE, "/users/{id}").hasRole(PermissionConstants.DELETE_USER)

                .antMatchers(HttpMethod.PUT, "/users/{id}/role").hasRole(PermissionConstants.SET_ROLE_TO_USER)
                .antMatchers(HttpMethod.DELETE, "/users/{id}/role/{role_id}").hasRole(PermissionConstants.REMOVE_ROLE_FROM_USER)

                .antMatchers(HttpMethod.GET, "/roles", "/roles/{id}").hasRole(PermissionConstants.GET_ROLE)
                .antMatchers(HttpMethod.POST, "/roles").hasRole(PermissionConstants.ADD_ROLE)
                .antMatchers(HttpMethod.PUT, "/roles/{id}").hasRole(PermissionConstants.UPDATE_ROLE)
                .antMatchers(HttpMethod.DELETE, "/roles/{id}").hasRole(PermissionConstants.DELETE_ROLE)

                .antMatchers(HttpMethod.POST, "/roles/{id}/permissions").hasRole(PermissionConstants.ADD_PERMISSION_TO_ROLE)
                .antMatchers(HttpMethod.PUT, "/roles/{id}/permissions").hasRole(PermissionConstants.SET_PERMISSION_TO_ROLE)
                .antMatchers(HttpMethod.DELETE, "/roles/{id}/permissions/{permission_id}").hasRole(PermissionConstants.REMOVE_PERMISSION_FROM_ROLE)

                .anyRequest().authenticated();

        http.addFilterBefore(getJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
