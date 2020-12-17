package com.xiezhenyu.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @author Tim
 */
public class JwtUtils {
    private static final String SING = "!Q@W#E$SFD2^&%SA@#%$R";
    /**
     * 生成token header.payload.sing
     */
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        //默认1天过期
        instance.add(Calendar.DATE, 1);

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });

        //指定过期时间
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));

        return token;
    }
    /**
     * 验证token,验证token的合法性
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
    /**
     * 获取token信息方法
     */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return verify;
    }
}