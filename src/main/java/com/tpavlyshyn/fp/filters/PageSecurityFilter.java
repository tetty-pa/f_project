package com.tpavlyshyn.fp.filters;

import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.common.LoginCommand;
import com.tpavlyshyn.fp.entity.user.Role;
import com.tpavlyshyn.fp.entity.user.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageSecurityFilter implements Filter {

    private final static Logger log = Logger.getLogger(LoginCommand.class);

    private static final String ADMIN_PAGE_PATH_PATTERN = ".*/jsp/admin/.*.jsp*";
    private static final String CLIENT_PAGE_PATH_PATTERN = ".*/jsp/client/.*.jsp*";
    private static final String COMMON_PAGE_PATH_PATTERN = ".*/jsp/common/.*.jsp*";

    private String redirectPage;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        redirectPage = filterConfig.getInitParameter("MAIN");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String page = httpServletRequest.getServletPath();
        boolean isCommonPage = checkPath(page, COMMON_PAGE_PATH_PATTERN);
        if (isCommonPage|| page.equals(Path.PAGE__INDEX)) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            HttpSession session = httpServletRequest.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                log.warn(String.format("Unexpected action from guest, page=%s.", page));
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + redirectPage);
            } else {
                Role userRole = Role.getRole(user);
                boolean isAccessAllowed = checkRole(userRole, page);
                if (isAccessAllowed) chain.doFilter(request, response);

                else {
                    log.warn("Unexpected action from user " + user + "and page=" + page);
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + redirectPage);

                }

            }
        }
    }

    private boolean checkRole(Role userRole, String path) {
        if (userRole == Role.ADMIN) {
            return checkPath(path, ADMIN_PAGE_PATH_PATTERN);
        }
        return checkPath(path, CLIENT_PAGE_PATH_PATTERN);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }


    private boolean checkPath(String path, String pagePattern) {
        Pattern pattern = Pattern.compile(pagePattern);
        Matcher matcher = pattern.matcher(path);

        return matcher.matches();
    }
}
