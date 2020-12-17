package com.xiezhenyu.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tim
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
        response.setHeader("Access-Control-Max-Age", 360000+"");
        CommonResult commonResult;
        //获取请求头中的令牌
        String token = request.getHeader("Authorization");
        try{
            //验证令牌
            JwtUtils.verify(token);
            //放行请求
            return true;
        }catch (SignatureVerificationException e){//签名不一致
            commonResult = CommonResult.errorCommonResult("签名不一致");
        }catch (TokenExpiredException e){//过期
            commonResult = CommonResult.errorCommonResult("令牌过期");
        }catch (AlgorithmMismatchException e){//算法不匹配
            commonResult = CommonResult.errorCommonResult("算法不匹配");
        }catch (InvalidClaimException e){//失效的payload异常
            commonResult = CommonResult.errorCommonResult("失效的payload异常");
        }catch (Exception e){
            commonResult = CommonResult.errorCommonResult("无效签名");
        }
        //将map转位json
        String json = new ObjectMapper().writeValueAsString(commonResult);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
