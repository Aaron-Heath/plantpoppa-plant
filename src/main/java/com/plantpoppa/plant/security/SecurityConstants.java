package com.plantpoppa.plant.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class SecurityConstants {
    public static final long ONE_DAY = 86400000;
    public static final long FIFTEEN_DAYS = 1296000000;
    private static final String JWT_SECRET = System.getenv("JWT_SECRET");
    public static final SecretKey JWT_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
    }
