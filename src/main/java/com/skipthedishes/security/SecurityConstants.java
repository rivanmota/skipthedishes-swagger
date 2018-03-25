package com.skipthedishes.security;

public class SecurityConstants {
	
	public static final String SECRET = "@S3cr3tK3yToG3nJWTs#";
    public static final long EXPIRATION_TIME = 43_200_000; // 12 hours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user/sign-up";
    public static final String LOGIN_URL = "/api/v1/Customer/auth";
}
