package com.example.demo.security.common;


import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

// 리퀘스트로부터 아이디 패스워드 외의 파라미터를 핸들링하는 클래스를 상속받은 커스텀 클래스
// WebAuthenticationDetails : 인증 과정 중 전달된 데이터를 저장(Authentication 의 details 속성에 저장) 하는 클래스
public class FormWebAuthenticationDetails extends WebAuthenticationDetails {

    private String secretKey;

    public FormWebAuthenticationDetails(HttpServletRequest request){
        super(request);
        secretKey = request.getParameter("secret_key");
    }

    public String getSecretKey(){
        return secretKey;
    }
}
