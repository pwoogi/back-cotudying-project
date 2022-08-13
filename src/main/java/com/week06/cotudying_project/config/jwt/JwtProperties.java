package com.week06.cotudying_project.config.jwt;

public interface JwtProperties {
    // 서버만 알고있는 시크릿키
    String SECRET = "phw1029";
    int EXPIRATION_TIME = 60000 * 60 * 24;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";

}
