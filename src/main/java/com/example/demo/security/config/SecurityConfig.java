package com.example.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    AuthenticationDetailsSource authenticationDetailsSource;
    // 커스텀 인증 클래스 등록
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider());
    }

    @Bean
    CustomAuthenticationProvider customAuthenticationProvider(){
        return new CustomAuthenticationProvider();
    }

//    인메모리 계정 생성
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 인코딩한 패스워드 스트링 생성
//        String password = passwordEncoder().encode("1111");
//        // 인메모리 유저 정보 생성
//        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
//        auth.inMemoryAuthentication().withUser("manager").password(password).roles("MANAGER", "USER");
//        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN", "MANAGER", "USER");
//    }

    // 평문인 비밀번호를 암호화하는 인코더
    @Bean
    public PasswordEncoder passwordEncoder() {
       return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // 접근 기본 설정
    @Override
    protected  void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/security").hasRole("geust")
                .antMatchers("/**/*").permitAll()
//                .antMatchers("/ticket/readAll").hasRole("USER")
//                .antMatchers("/ticket/readOne").hasRole("MANAGER")
//                .antMatchers("/test").hasRole("ADMIN")
                .anyRequest().authenticated()

        .and()
                .formLogin()
                .authenticationDetailsSource(authenticationDetailsSource)
//                .loginPage("/login")
//                .loginProcessingUrl("/login_proc")
//                .defaultSuccessUrl("/")
//                .permitAll()
                ;
    }

    // 접근 시 인증을 요구하지 않도록 webIgnore 설정
    // 정적 리소스 요청 시 인증 제외
    @Override
    public void configure(WebSecurity web){
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
