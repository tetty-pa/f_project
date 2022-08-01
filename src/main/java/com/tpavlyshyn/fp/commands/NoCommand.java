package com.tpavlyshyn.fp.commands;

import com.tpavlyshyn.fp.commands.action.Dispatcher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


public class NoCommand implements Command {
	private final static Logger log = Logger.getLogger(NoCommand.class);


	@Override
	public Dispatcher execute(HttpServletRequest request,
							  HttpServletResponse response)  {
		log.error("No such command");
		String errorMessage = "No such command";
		request.setAttribute("errorMessage", errorMessage);
		return null;
	}

}