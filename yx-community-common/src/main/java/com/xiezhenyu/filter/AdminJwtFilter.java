package com.xiezhenyu.filter;

import com.alibaba.fastjson.JSONObject;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.utils.JwtUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tim
 * @date 2021/5/26
 */
@WebFilter(urlPatterns = "/admin")
public class AdminJwtFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String token = request.getHeader("Authorization");
        System.out.println("AdminJwtFilter");
        if(!request.getServletPath().equals("/admin/login")&&!verify(token)) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(JSONObject.toJSONString(CommonResult.errorCommonResult("token验证不通过")));
            response.getWriter().flush();
        }else {
            chain.doFilter(req,res);
        }
    }

    boolean verify(String token){
        boolean res = JwtUtils.verifyToken(token);
        return res;
    }
}
