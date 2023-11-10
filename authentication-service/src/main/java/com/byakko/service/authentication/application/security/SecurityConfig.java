package com.byakko.service.authentication.application.security;

import com.byakko.common.domain.valueobject.SystemRole;
import com.byakko.common.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public JwtUtils getJwtUtils() { return new JwtUtils(); }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable()
                .securityContext()
                .securityContextRepository(new NullSecurityContextRepository()) // Cấu hình không lưu trữ context
                .and()
                .authorizeRequests()
                .antMatchers("/customers/signup").permitAll()
                .antMatchers("/customers/signin").permitAll()
                .antMatchers("/customers/signout").hasAuthority(SystemRole.CUSTOMER.getName())
                .antMatchers("/customers/{id}/resendVerifyMail").hasAuthority(SystemRole.CUSTOMER.getName())
                .antMatchers("/customers/verifyemailaddress").permitAll()
                .antMatchers("/customers/{id}/changepassword").hasAnyAuthority(SystemRole.CUSTOMER.getName())
                .antMatchers("/customers/sendResetPasswordMail").permitAll()
                .antMatchers("/customers/resetPassword").permitAll()
                .antMatchers("/customers/{id}").hasAnyAuthority(SystemRole.CUSTOMER.getName())

                .antMatchers("/roles/**").hasAnyAuthority(SystemRole.SYSTEM_ADMIN.getName(), SystemRole.SHOP_OWNER.getName())

                .antMatchers("/sa/signin").permitAll()
                .antMatchers("/adminPage/getMenuItemById").hasAnyAuthority(SystemRole.SYSTEM_ADMIN.getName(), SystemRole.SHOP_OWNER.getName())
                .antMatchers("/adminPage/getAllPage").hasAnyAuthority(SystemRole.SYSTEM_ADMIN.getName(), SystemRole.SHOP_OWNER.getName())
                .antMatchers("/adminPage/getMenuById").hasAnyAuthority(SystemRole.SYSTEM_ADMIN.getName(), SystemRole.SHOP_OWNER.getName())
                .antMatchers("/adminPage/getAllPermissions").hasAnyAuthority(SystemRole.SYSTEM_ADMIN.getName(), SystemRole.SHOP_OWNER.getName())
                .antMatchers("/adminPage/getAllMenu").hasAnyAuthority(SystemRole.SYSTEM_ADMIN.getName(), SystemRole.SHOP_OWNER.getName())
                .antMatchers("/adminPage/createMenu").hasAnyAuthority(SystemRole.SYSTEM_ADMIN.getName(), SystemRole.SHOP_OWNER.getName())
                .antMatchers("/adminPage/createMenuItem").hasAnyAuthority(SystemRole.SYSTEM_ADMIN.getName(), SystemRole.SHOP_OWNER.getName())
                .antMatchers("/adminPage/createPermissionGroups").hasAnyAuthority(SystemRole.SYSTEM_ADMIN.getName(), SystemRole.SHOP_OWNER.getName())
                .antMatchers("/adminPage/createMenuToPermissionGroup").hasAnyAuthority(SystemRole.SYSTEM_ADMIN.getName(), SystemRole.SHOP_OWNER.getName())
                .antMatchers("/adminPage/deleteMenuById").hasAnyAuthority(SystemRole.SYSTEM_ADMIN.getName(), SystemRole.SHOP_OWNER.getName())
                .antMatchers("/adminPage/deleteMenuItemById").hasAnyAuthority(SystemRole.SYSTEM_ADMIN.getName(), SystemRole.SHOP_OWNER.getName())
                .antMatchers("/adminPage/updateMenu").hasAnyAuthority(SystemRole.SYSTEM_ADMIN.getName(), SystemRole.SHOP_OWNER.getName())

                .antMatchers("/shopowners/signin").permitAll()
                .antMatchers("/shopowners/signup").permitAll()
                .antMatchers("/shopowners/sendResetPasswordMail").permitAll()
                .antMatchers("/shopowners/resetPassword").permitAll()
                .antMatchers("/shopowners/{id}/resendVerifyMail").permitAll()
                .antMatchers("/shopowners/verifyemailaddress").permitAll()
                .antMatchers("/shopowners/{id}").hasAuthority(SystemRole.SHOP_OWNER.getName())

                .antMatchers("/employees/**").hasAnyAuthority(SystemRole.SYSTEM_ADMIN.getName(), SystemRole.SHOP_OWNER.getName())
//                .antMatchers("/employees/{userId}/changepassword").hasAnyAuthority(SystemRole.)
//                .antMatchers("/sendResetPasswordMail").hasAnyAuthority(SystemRole)
//                .antMatchers("/resetPassword").hasAnyAuthority()
                .antMatchers("/employees/signin").permitAll()

                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Áp dụng cho tất cả các URL

        return source;
    }

}
