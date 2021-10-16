package com.miss.api.auth.security;

public class SecurityConstants {
    public static final String AUTH_LOGIN_URL = "/auth/login";
    public static final String FORGET_PWD_LOGOUT_URL = "/auth/forget_pwd";
    public static final String USER_SAVE_URL = "/users";
    public static final String IMAGE_URL = "/photos/**";
    public static final String ARTICLE_URL = "/articles/**";
    public static final String RECIT_URL = "/recits/**";
    public static final String PARTENAIRE_URL = "/partenaires/**";
    public static final String CONTACT_URL = "/contacts/**";

    // Signing key for HS512 algorithm
    // You can use the page https://mkjwk.org/ to generate all kinds of keys
    public static final String JWT_SECRET = "KLQBCS%LBKLKQSBLCLSQB";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
