package com.tpavlyshyn.fp.commands;

import com.tpavlyshyn.fp.commands.admin.*;
import com.tpavlyshyn.fp.commands.client.*;
import com.tpavlyshyn.fp.commands.common.*;
import com.tpavlyshyn.fp.commands.client.ShowCruisesCommand;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Map<String, Command> commands = new TreeMap<>();
/*
    private static CommandContainer instance = null;
*/


    /*    static {
            commands.put("registration", new RegistrationCommand());
            commands.put("showCruises", new ShowCruisesCommand());
            commands.put("login", new LoginCommand());
            commands.put("addCruise", new AddCruiseCommand());
            commands.put("makeRequest", new MakeRequestCommand());
            commands.put("showCruiseInfo", new ShowCruiseInfoCommand());
            commands.put("showUsersRequests", new ShowUsersRequestsCommand());
            commands.put("showAllRequests", new ShowAllRequestsCommand());
            commands.put("showAllUsers", new ShowAllUsersCommand());
            commands.put("showAllCruises", new ShowAllCruisesCommand());
            commands.put("submitRequest", new SubmitRequestCommand());
            commands.put("uploadDocuments", new UploadDocumentsCommand());
            commands.put("calculateTotalPrice", new CalculateTotalPriceCommand());
            commands.put("pay", new PayCommand());
            commands.put("updateCruise", new UpdateCruiseCommand());
            commands.put("logout", new LogoutCommand());
            commands.put("editProfile", new EditProfileCommand());
            commands.put("deleteCruise", new DeleteCruiseCommand());

            commands.put("submitRegistration", new SubmitRegistrationCommand());

            commands.put("forgotPassword", new ForgotPasswordCommand());
            commands.put("validateOtp", new ValidateOtpCommand());
            commands.put("newPassword", new NewPasswordCommand());


        }*/
    public void addCommand(String name, Command command) {
        commands.put(name, command);
    }

    public Command getCommand(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }

  /*  public static CommandContainer getInstance() {
        if (instance == null) {
            instance = new CommandContainer();
        }
        return instance;
    }*/
}
