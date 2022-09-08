package com.tpavlyshyn.fp.listener;

import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.CommandContainer;
import com.tpavlyshyn.fp.commands.client.PdfCommand;
import com.tpavlyshyn.fp.commands.admin.*;
import com.tpavlyshyn.fp.commands.client.*;
import com.tpavlyshyn.fp.commands.common.*;
import com.tpavlyshyn.fp.dao.*;
import com.tpavlyshyn.fp.dao.impl.*;
import com.tpavlyshyn.fp.services.CruiseService;
import com.tpavlyshyn.fp.services.LinerService;
import com.tpavlyshyn.fp.services.RequestService;
import com.tpavlyshyn.fp.services.UserService;
import com.tpavlyshyn.fp.services.impl.CruiseServiceImpl;
import com.tpavlyshyn.fp.services.impl.LinerServiceImpl;
import com.tpavlyshyn.fp.services.impl.RequestServiceImpl;
import com.tpavlyshyn.fp.services.impl.UserServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ContextListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        log.debug("Servlet context initialization starts");
        ServletContext context = event.getServletContext();
        initDatasource(context);
        initLog4J(context);
        initCommands(context);
        log.debug("Servlet context initialization finished");

    }

    private void initDatasource(ServletContext context) throws IllegalStateException {
        String dataSourceName = context.getInitParameter("dataSource");
        log.trace("dataSourceName: {" + dataSourceName + "}");
        Context jndiContext;
        try {
            jndiContext = (Context) new InitialContext().lookup("java:/comp/env");
            log.trace("jndiContext: {" + jndiContext + "}");
            DataSource dataSource = (DataSource) jndiContext.lookup(dataSourceName);
            log.trace("dataSource: {" + dataSource + "}");
            context.setAttribute("dataSource", dataSource);
            log.trace("context.setAttribute 'dataSource': {" + dataSource.getClass().getName() + "}");
        } catch (NamingException e) {
            throw new IllegalStateException("Cannot initialize dataSource", e);
        }
    }


    private void initCommands(ServletContext context) {
        DataSource dataSource = (DataSource) context.getAttribute("dataSource");
        //create dao
        UserDao userDao = new UserDaoImpl(dataSource);
        RequestDao requestDao = new RequestDaoImpl(dataSource);
        CruiseDao cruiseDao = new CruiseDaoImpl(dataSource);
        PortDao portDao = new PortDaoImpl(dataSource);
        LinerDao linerDao = new LinerDaoImpl(dataSource);

        //create services
        UserService userService = new UserServiceImpl(userDao);
        RequestService requestService = new RequestServiceImpl(requestDao, cruiseDao);
        CruiseService cruiseService = new CruiseServiceImpl(cruiseDao, portDao, requestDao);
        LinerService linerService = new LinerServiceImpl(linerDao);

        CommandContainer commands = new CommandContainer();
        Command command = new RegistrationCommand(userService);
        commands.addCommand("registration", command);

        command = new ShowCruisesCommand(cruiseService);
        commands.addCommand("showCruises", command);
        command = new LoginCommand(userService);
        commands.addCommand("login", command);
        command = new PutCruiseIntoSessionCommand();
        commands.addCommand("putCruiseIntoSession", command);
        command = new MakeRequestCommand(requestService);
        commands.addCommand("makeRequest", command);
        command = new ShowCruiseInfoCommand(cruiseService);
        commands.addCommand("showCruiseInfo", command);
        command = new ShowUsersRequestsCommand(requestService);
        commands.addCommand("showUsersRequests", command);
        command = new ShowAllRequestsCommand(requestService);
        commands.addCommand("showAllRequests", command);
        command = new ShowAllUsersCommand(userService);
        commands.addCommand("showAllUsers", command);
        command = new ShowAllCruisesCommand(cruiseService);
        commands.addCommand("showAllCruises", command);
        command = new SubmitRequestCommand(requestService);
        commands.addCommand("submitRequest", command);
        command = new UploadDocumentsCommand(userService);
        commands.addCommand("uploadDocuments", command);
        command = new CalculateTotalPriceCommand(requestService);
        commands.addCommand("calculateTotalPrice", command);
        command = new PayCommand(requestService);
        commands.addCommand("pay", command);

        command = new LogoutCommand();
        commands.addCommand("logout", command);
        command = new EditProfileCommand(userService);
        commands.addCommand("editProfile", command);
        command = new DeleteCruiseCommand(cruiseService);
        commands.addCommand("deleteCruise", command);

        command = new SubmitRegistrationCommand(userService);
        commands.addCommand("submitRegistration", command);

        command = new ForgotPasswordCommand();
        commands.addCommand("forgotPassword", command);
        command = new ValidateOtpCommand();
        commands.addCommand("validateOtp", command);
        command = new NewPasswordCommand(userService);
        commands.addCommand("newPassword", command);
        command = new ChangeLangCommand();
        commands.addCommand("changeLang", command);
        command = new ShowUsersByCruiseIdCommand(userService);
        commands.addCommand("showAllUsersByCruise", command);
        command = new PdfCommand(requestService);
        commands.addCommand("pdf", command);

        command = new ShowAllPortsCommand(cruiseService);
        commands.addCommand("showAllPorts", command);

        command = new AddPortToCruiseCommand();
        commands.addCommand("addPortToCruise", command);

        command = new AddCruiseCommand(cruiseService);
        commands.addCommand("addCruise", command);

        command = new FindAllLinersCommand(linerService);
        commands.addCommand("findAllLiners", command);

        command = new RemovePortFromCruiseCommand();
        commands.addCommand("removePortFromCruise", command);

        context.setAttribute("commandContainer", commands);

        log.trace("context.setAttribute 'commandContainer': {" + commands + "}");
    }


    private void initLog4J(ServletContext servletContext) {
        log.debug("Log4J initialization started");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath(
                    "WEB-INF/log4j.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        log.debug("Log4J initialization finished");
    }

}
