package openkino.com.configuration;

import openkino.com.filter.BasicAuthenticationVUFilter;
import openkino.com.filter.EncodingFilter;
import openkino.com.service.KinoUserService;
import openkino.com.tokens.FilterToken;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private KinoUserService kinoUserService;
    private ApplicationContext context;

    public SecurityConfig(ApplicationContext context, KinoUserService kinoUserService) {
        this.kinoUserService = kinoUserService;
        this.context = context;
    }

    public ApplicationContext getContext() {
        return context;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(kinoUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final BasicAuthenticationVUFilter authenticationVUFilter = new BasicAuthenticationVUFilter(authenticationManagerBean(), jwtConfig());
        http
                .csrf().disable()
                .addFilterBefore(new FilterToken(jwtConfig(), kinoUserService), UsernamePasswordAuthenticationFilter.class)
                .addFilter(authenticationVUFilter)
                .addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class)
                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "Film/image/**", "Film/save", "hall/**", "session/**", "reser/discount", "reser/setList/*").access("hasRole('ROLE_ADMIN')")
//                .antMatchers(HttpMethod.DELETE, "Film/**", "KinoUser/**", "buy/*", "hall/**", "session/**", "reser/deleteList/*").access("hasRole('ROLE_ADMIN')")
//                .antMatchers(HttpMethod.PUT, "Film/**", "KinoUser/block", "KinoUser/active", "hall/upd**", "session/**").access("hasRole('ROLE_ADMIN')")
//                .antMatchers(HttpMethod.PUT, "reser/**").permitAll()
//                .antMatchers("buy/save").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
//                .antMatchers(HttpMethod.GET, "KinoUser/*", "KinoUser/all").access("hasRole('ROLE_ADMIN')")
//                .antMatchers(HttpMethod.GET, "Film/**", "KinoUser/?**", "buy/**", "card/**",
//                        "hall/**", "session/**", "reser/**").permitAll()
//                .antMatchers("Film/rating/**", "KinoUser/save", "KinoUser/update", "buy", "card**", "hall/changeStatus**", "reser/delete/*", "reser/sav**").permitAll()
                .antMatchers("/**").permitAll()

                .anyRequest().permitAll()

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
        ;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
