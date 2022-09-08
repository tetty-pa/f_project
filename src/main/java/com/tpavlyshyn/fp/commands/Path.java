package com.tpavlyshyn.fp.commands;


public final class Path {

    // pages
    public static final String PAGE__LOGIN = "/jsp/common/login.jsp";
    public static final String PAGE__SHOW_CRUISES = "/jsp/common/showCruises.jsp";
    public static final String PAGE__REGISTRATION = "/jsp/common/registration.jsp";
    public static final String PAGE__SHOW_CRUISE_INFO = "/jsp/client/showCruiseInfo.jsp";
    public static final String PAGE__SHOW_USERS_REQUESTS = "/jsp/client/showUsersRequests.jsp";
    public static final String PAGE__SHOW_ALL_REQUESTS = "/jsp/admin/showAllRequests.jsp";
    public static final String PAGE__SHOW_ALL_USERS = "/jsp/admin/showAllUsers.jsp";
    public static final String PAGE__SHOW_ALL_CRUISES = "/jsp/admin/showAllCruises.jsp";
    public static final String PAGE__ADD_CRUISE = "/jsp/admin/addCruise.jsp";
    public static final String PAGE__PAY = "/jsp/client/pay.jsp";
    public static final String PAGE__SHOW_ALL_PORTS = "/jsp/admin/addPort.jsp";

    public static final String PAGE__INDEX = "/index.jsp";
    public static final String PAGE__ENTER_OTP = "/jsp/common/enterOtp.jsp";
    public static final String PAGE__FORGOT_PASSWORD = "/jsp/common/forgotPassword.jsp";
    public static final String PAGE__NEW_PASSWORD = "/jsp/common/newPassword.jsp";

    public static final String ERROR_PAGE = "/jsp/common/404.jsp";


    public static final String IMAGES_STORE = "C:\\Users\\Tetiana Pavlyshyn\\IdeaProjects\\f_project - Copy\\src\\main\\webapp\\img\\usersDocuments";
    public static final String COMMAND__SHOW_CRUISES = "/controller?command=showUsersRequests&userId=";
    public static final String COMMAND__SHOW_ALL_CRUISES = "/controller?command=showAllCruises";
    public static final String COMMAND__SHOW_ALL_PORTS = "/controller?command=showAllPorts&&currentPage=";


}