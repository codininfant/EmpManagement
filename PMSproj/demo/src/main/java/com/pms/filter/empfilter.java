package com.pms.filter;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.pms.pojo.Result;
import java.io.IOException;
import com.pms.utils.JwtUtils;
@WebFilter(urlPatterns = {"/*"})
public class empfilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(((HttpServletRequest)servletRequest).getRequestedSessionId().toString().contains("login"))
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
        try {
            JwtUtils.parseJWT(jwtToken);
        }catch (Exception e)
        {
            Result error = Result.error("NOT_LOGIN");
            String notLoginJson = JSONObject.toJSONString(error);
            ((HttpServletResponse)servletResponse).getWriter().write(notLoginJson);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
