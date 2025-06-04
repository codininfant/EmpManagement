package com.pms.filter;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.pms.pojo.Result;
import java.io.IOException;
import java.util.Map;

import com.pms.utils.JwtUtils;
@WebFilter(urlPatterns = {"/*"})
public class empfilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(((HttpServletRequest)servletRequest).getRequestURI().toString().contains("login"))
        {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String jwtToken = ((HttpServletRequest)servletRequest).getHeader("token");
        if(jwtToken == null)
        {
            Result error = Result.error("NOT_LOGIN");
            String notLoginJson = JSONObject.toJSONString(error);
            ((HttpServletResponse)servletResponse).getWriter().write(notLoginJson);
            return;
        }

        Map<String, Object> res;

        try {
            res = JwtUtils.parseJWT(jwtToken);
        }catch (Exception e)
        {
            Result error = Result.error("NOT_LOGIN");
            String notLoginJson = JSONObject.toJSONString(error);
            ((HttpServletResponse)servletResponse).getWriter().write(notLoginJson);
            return;
        }
        String role = (String) res.get("identification");
        Integer id = (Integer) res.get("id");
        if(role.equals( "0"))//manager
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if(role.equals("1"))//employee
        {
            String[] uriParts = ((HttpServletRequest)servletRequest).getRequestURI().split("/");
            String urlIdStr = uriParts[uriParts.length - 1]; // 获取最后一个路径片段

            if(id.toString().equals(urlIdStr))
                filterChain.doFilter(servletRequest, servletResponse);
            else
            {
                Result error = Result.error("NOT_PERMISSION");
                String notPermissionJson = JSONObject.toJSONString(error);
                ((HttpServletResponse)servletResponse).getWriter().write(notPermissionJson);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
