package com.example.demo.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

// AuthenticationProvider 를 상속받아 커스텀 인증처리를 구현
// AuthenticationProvider : 실제 인증처리를 진행하는 스피링 시큐리티 클래스
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    // 실제 인증 함수
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // authentication 는 사용자가 입력한 데이터를 가진 객체
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();

        // username 을 기반으로 계정 정보 조회 - ID 검증
        AccountContext accountContext = (AccountContext)customUserDetailService.loadUserByUsername(username);

        // 패스워드가 일치하지 않을 경우 BadCredentialsException 발생 - PASSWORD 검증
        if(!passwordEncoder.matches(password, accountContext.getAccount().getPassword())){
            throw new BadCredentialsException("BadCredentialsException");
        }

        // 인증에 성공!

        // 인증 객체(토큰)를 만들어서 반환
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());
        return authenticationToken;
    }

    // 파라미터로 전달되는 클래스의 타입과 토큰의 타입이 일치할 때 인증을 진행할 수 있도록 구현(?)
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
