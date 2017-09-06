package com.jinpaihushi.jphs.fiter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SafetyFilter extends HandlerInterceptorAdapter {
    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("过滤器.......");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        logger.info("ip地址：" + ip);
        /*if(request.getRequestURI().equals("/upload/index.jhtml")){
        response.setHeader("Access-Control-Allow-Methods","POST");
        response.setHeader("Access-Control-Max-Age"," 1000");
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    /*
     * public void doFilterInternal(ServletRequest req, ServletResponse res,
     * FilterChain chain) throws IOException, ServletException {
     * HttpServletRequest request = (HttpServletRequest) req; // 获取当前请求的url
     * String url = request.getRequestURI(); System.out.println(url); //
     * 获取用户的可用的url List<String> list = (List<String>)
     * request.getSession().getAttribute("session_url"); if (list.contains(url))
     * { chain.doFilter(req, res); } else { super.doFilter(request, res, chain);
     * } }
     */

}