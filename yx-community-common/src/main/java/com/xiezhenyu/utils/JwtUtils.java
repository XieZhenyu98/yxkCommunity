package com.xiezhenyu.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiezhenyu.response.CommonResult;

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

    /**
     * 验证token是否合法
     * @param token
     * @return
     */
    public static boolean verifyToken(String token) {
        try{
            //验证令牌
            JwtUtils.verify(token);
            //放行请求
            return true;
        }catch (SignatureVerificationException e){//签名不一致
            return false;
        }catch (TokenExpiredException e){//过期
            return false;
        }catch (AlgorithmMismatchException e){//算法不匹配
            return false;
        }catch (InvalidClaimException e){//失效的payload异常
            return false;
        }catch (Exception e){//无效签名
            return false;
        }
    }
}