package com.company.milliyuniversity.configs.security;

import com.company.milliyuniversity.configs.AuthenticationEntryPoint;
import com.company.milliyuniversity.configs.jwt.JWTFilter;
import com.company.milliyuniversity.service.AuthUserService;
import com.company.milliyuniversity.utils.jwt.TokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.multipart.support.MultipartFilter;

import static com.company.milliyuniversity.configs.security.SecurityConstants.WHITE_LIST;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 06/02/23 Monday 11:41
 * milliy-university/IntelliJ IDEA
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfigurer {
    private final TokenService tokenService;
    private final AuthUserService authUserService;
    private final AuthenticationEntryPoint authEntryPoint;

    public SecurityConfigurer(@Qualifier("accessTokenService") TokenService tokenService,
                              AuthUserService authUserService,
                              com.company.milliyuniversity.configs.AuthenticationEntryPoint authEntryPoint) {
        this.tokenService = tokenService;
        this.authUserService = authUserService;
        this.authEntryPoint = authEntryPoint;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests(expressionInterceptUrlRegistry -> expressionInterceptUrlRegistry
                        .antMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JWTFilter(tokenService, authUserService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(authEntryPoint);
        return http.build();
    }

    @Bean
    public MultipartFilter multipartFilter() {

        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }
}
