package com.yjhy.filter;

import com.yjhy.entity.SysAccount;
import com.yjhy.util.SessionUtil;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by husong on 2018/10/24.
 */
public class LoginFilter implements Filter {

    private String[] staticPaths;
    private String[] excludePaths;
    private String[] excludeSuffix;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        if (ObjectUtils.isEmpty(excludePaths)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //静态资源
        if (isStatic(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //不需要拦截
        if (isExcludePath(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpSession session = request.getSession();
        SysAccount admin = (SysAccount) session.getAttribute("admin");
        if (ObjectUtils.isEmpty(admin)) {
            response.sendRedirect("/login");
            return;
        }
        SessionUtil.setLoginAdmin(admin);
        filterChain.doFilter(servletRequest, servletResponse);
        return ;
    }

    private boolean isStatic(String uri) {
        boolean isStatic = false;
        if (!ObjectUtils.isEmpty(staticPaths)) {
            for (String path : staticPaths) {
                if (uri.startsWith(path)) {
                    isStatic = true;
                    break;
                }
            }
        }
        return isStatic;
    }

    private boolean hasSuffix(String path) {
        boolean hasSuffix = false;
        if (!ObjectUtils.isEmpty(excludeSuffix)) {
            for (String suffix : excludeSuffix) {
                if (path.lastIndexOf(suffix) != -1) {
                    hasSuffix = true;
                    break;
                }
            }
        }
        return hasSuffix;
    }

    private boolean isExcludePath(String uri) {
        boolean isExcludePath = false;
        if (!ObjectUtils.isEmpty(excludePaths)) {
            for (String path : excludePaths) {
                if (uri.indexOf(path) != -1) {
                    isExcludePath = true;
                    break;
                }
            }
        }
        return isExcludePath;
    }

    @Override
    public void destroy() {

    }

    public String[] getExcludePaths() {
        return excludePaths;
    }

    public void setExcludePaths(String[] excludePaths) {
        this.excludePaths = excludePaths;
    }

    public String[] getExcludeSuffix() {
        return excludeSuffix;
    }

    public void setExcludeSuffix(String[] excludeSuffix) {
        this.excludeSuffix = excludeSuffix;
    }

    public String[] getStaticPaths() {
        return staticPaths;
    }

    public void setStaticPaths(String[] staticPaths) {
        this.staticPaths = staticPaths;
    }
}
