package com.example.backend.service.user;

import com.example.backend.data.dao.RefreshDao;
import com.example.backend.security.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final JwtUtil jwtUtil;
    private final RefreshDao refreshDao;

    @Autowired
    public UserService(JwtUtil jwtUtil, RefreshDao refreshDao) {
        this.jwtUtil = jwtUtil;
        this.refreshDao = refreshDao;
    }

    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        // Refresh 토큰 검색
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refresh")) {
                    refresh = cookie.getValue();
                    break;
                }
            }
        }

        // Refresh 토큰이 없을 경우
        if (refresh == null) {
            return new ResponseEntity<>("Refresh token not found", HttpStatus.BAD_REQUEST);
        }

        // Refresh 토큰 만료 확인
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>("Refresh token expired", HttpStatus.BAD_REQUEST);
        }

        // Refresh 토큰의 카테고리 확인 (토큰 발급 시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {
            return new ResponseEntity<>("Invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshDao.existsByRefresh(refresh);
        if (!isExist) {
            //response body
            return new ResponseEntity<>("invalid refresh token2", HttpStatus.BAD_REQUEST);
        }

        // 새로운 Access 토큰 생성
        String userId = jwtUtil.getUserId(refresh);
        Long id = jwtUtil.getId(refresh);
        String newAccess = jwtUtil.createJwt("access", userId, id, 3600L);
        String newRefresh = jwtUtil.createJwt("refresh", userId, id, 86400L);

        // 응답에 새로운 Access 토큰 추가
        response.setHeader("access", newAccess);
        response.addCookie(createCookie("refresh", newRefresh));

        refreshDao.deleteByRefresh(refresh);

        refreshDao.save(userId, newRefresh);


        return new ResponseEntity<>(HttpStatus.OK);
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
