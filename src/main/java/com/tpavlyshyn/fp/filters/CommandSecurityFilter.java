package com.tpavlyshyn.fp.filters;


import com.tpavlyshyn.fp.entity.user.Role;
import com.tpavlyshyn.fp.entity.user.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

public class CommandSecurityFilter implements Filter {

    private final List<String> urls;

    private final Set<String> accessibleCommands;

    private final Set<String> commonCommands;

    private final Set<String> clientCommands;

    private final Set<String> adminCommands;



    public CommandSecurityFilter() {
        urls = new ArrayList<>();
        accessibleCommands = new HashSet<>();
        commonCommands = new HashSet<>();
        clientCommands = new HashSet<>();
        adminCommands = new HashSet<>();

        accessibleCommands.add("login");
        accessibleCommands.add("showCruises");
        accessibleCommands.add("registration");
        accessibleCommands.add("showCruiseInfo");
        accessibleCommands.add("logout");
        accessibleCommands.add("submitRegistration");
        accessibleCommands.add("forgotPassword");
        accessibleCommands.add("validateOtp");
        accessibleCommands.add("newPassword");
        accessibleCommands.add("changeLang");
        // common commands


        // client commands
        clientCommands.add("makeRequest");
        clientCommands.add("pay");
        clientCommands.add("showUsersRequests");
        clientCommands.add("uploadDocuments");
        clientCommands.add("pdf");
        clientCommands.add("calculateTotalPrice");
        clientCommands.add("editProfile");

        // admin commands
        adminCommands.add("addCruise");
        adminCommands.add("showAllRequests");
        adminCommands.add("showAllCruises");
        adminCommands.add("showAllUsers");
        adminCommands.add("submitRequest");
        adminCommands.add("showAllPorts");
        adminCommands.add("addPortToCruise");
        adminCommands.add("findAllLiners");
        adminCommands.add("removePortFromCruise");
        adminCommands.add("putCruiseIntoSession");
        adminCommands.add("deleteCruise");
        adminCommands.add("showAllUsersByCruise");
        adminCommands.add("updateCruise");
        adminCommands.add("showUsersRequests");

    }

    public void init(FilterConfig fConfig) throws ServletException {

        String avoidURLs = fConfig.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(avoidURLs, ",");

        while (token.hasMoreTokens()) {
            urls.add(token.nextToken());
        }
    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String command = req.getParameter("command");

        if (accessibleCommands.contains(command)) {
            chain.doFilter(req, res); // request for accessible url
        } else {

            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("user") == null) {

                res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                if (commonCommands.contains(command)) {
                    chain.doFilter(req, res); // Logged-in user found, so
                    // just
                } else {
                    User user = (User) session.getAttribute("user");
                    if ("client".equals(Role.getRole(user).getName())
                            && clientCommands.contains(command)) {

                        chain.doFilter(req, res); // Logged-in user found, so
                        // just continue request.
                    } else if ("admin".equals(Role.getRole(user).getName())
                            && adminCommands.contains(command)) {


                        chain.doFilter(req, res); // Logged-in user found, so
                        // just continue request.
                    } else {
                        res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                }
            }
        }
    }
}
