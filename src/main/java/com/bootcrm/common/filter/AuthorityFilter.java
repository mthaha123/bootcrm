package com.bootcrm.common.filter;


import com.bootcrm.model.SysUser;
import com.bootcrm.common.util.UserUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorityFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //得到用户个人相关的信息（登陆的用户，用户的语言）
        fillUserInfo(request);

        try{
            chain.doFilter(request,response);
        }finally {
            clearAllUserInfo();
        }

    }

    public void destroy() {

    }


    private void fillUserInfo(HttpServletRequest request){
        //获取用户信息
        SysUser user =getUserFromSession(request);
        if(user != null){
            UserUtil.setUser(user.getUserName());
        }

        // 语言信息
        String locale = getLocaleFromCookies(request);

        // 放入到threadlocal，同一个线程任何地方都可以拿出来
        if (locale != null) {
            UserUtil.setLocale(locale);
        }
    }

    private String getLocaleFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }
        for (Cookie cookie:cookies){
            if(UserUtil.KEY_LANG.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }

    private SysUser getUserFromSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return null;
        }
        // 从session中获取用户信息放到工具类中
        SysUser user = (SysUser)session.getAttribute(UserUtil.KEY_USER);
        return user;
    }

    private void clearAllUserInfo(){
        UserUtil.clearAllUserInfo();
    }
}
