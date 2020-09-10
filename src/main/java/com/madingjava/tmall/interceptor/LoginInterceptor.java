package com.madingjava.tmall.interceptor;

import com.madingjava.tmall.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor{
    /**
        1. 准备字符串数组 requireAuthPages，存放那些需要登录才能访问的路径
        2. 获取uri
        3. 去掉前缀/tmall_springboot
        4. 判断是否是以 requireAuthPages 里的开头的
            4.1 如果是就判断是否登陆，未登陆就跳转到 login 页面
            4.2 如果不是就放行
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String contextPath = httpServletRequest.getServletContext().getContextPath();
        String []requireAuthPages = new String[]{
                "buy",
                "alipay",
                "payed",
                "cart",
                "bought",
                "confirmPay",
                "orderConfirmed",
                "forebuyone",
                "forebuy",
                "foreaddCart",
                "forecart",
                "forechangeOrderItem",
                "foredeleteOrderItem",
                "forecreateOrder",
                "forepayed",
                "forebought",
                "foreconfirmPay",
                "foreorderConfirmed",
                "foredeleteOrder",
                "forereview",
                "foredoreview"

        };
        String uri = httpServletRequest.getRequestURI();
        uri = StringUtils.remove(uri , contextPath+"/");
        String page = uri;

        if(beginWith(page , requireAuthPages)){
            User user = (User)session.getAttribute("user");
            if(user == null){
                //说明未登录状态
                httpServletResponse.sendRedirect("login");
                return false;
            }
        }

        return true;

    }

    private boolean beginWith(String page , String [] requiredAuthPages){
        boolean result = false;
        for(String requireAuthpage : requiredAuthPages){
            if(StringUtils.startsWith(page , requireAuthpage)){
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
