package com.example.backend.security.jwt;
import com.example.backend.data.dao.RefreshDao;
import com.example.backend.data.dto.user.JwtUserDto;
import com.example.backend.data.dto.user.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshDao refreshDao;

    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, RefreshDao refreshDao ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshDao = refreshDao;
        // 변경된 로그인 경로 설정
        AntPathRequestMatcher loginRequestMatcher = new AntPathRequestMatcher("/api/v1/user/login", "POST");
        setRequiresAuthenticationRequestMatcher(loginRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        LoginDto loginDto = new LoginDto();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            loginDto = objectMapper.readValue(messageBody, LoginDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String userId = loginDto.getUserId();
        String password = loginDto.getPassword();


        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, password, null);

        return authenticationManager.authenticate(authToken);
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
        String userId = jwtUserDto.getUsername();
        Long id = jwtUserDto.getId();



        // JWT 토큰 생성
        String accessToken = jwtUtil.createJwt("access", userId,id, 3600L);
        String refreshToken = jwtUtil.createJwt("refresh", userId, id, 86400L);

        response.addCookie(createCookie("refresh", refreshToken));
        response.setHeader("access", accessToken);
        // 응답 헤더에 JWT 토큰 추가
        // JSON 데이터 생성
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("id", id);
        responseBody.put("userId", userId);

        //redis에 refreshtoken 저장
        refreshDao.save(userId, refreshToken);

        try {
            // 응답 본문에 JSON 데이터 추가

            ObjectMapper objectMapper = new ObjectMapper();
            String responseBodyJson = objectMapper.writeValueAsString(responseBody);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseBodyJson);
        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();
        }

    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

        response.setStatus(401);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

}
