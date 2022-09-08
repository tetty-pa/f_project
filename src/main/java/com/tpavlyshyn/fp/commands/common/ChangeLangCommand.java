package com.tpavlyshyn.fp.commands.common;

import com.tpavlyshyn.fp.MessageManager;
import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Redirect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.jstl.core.Config;
import org.apache.log4j.Logger;

import java.util.Locale;
import java.util.Objects;

public class ChangeLangCommand implements Command {
    private static final Logger log = Logger.getLogger(ChangeLangCommand.class);

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {

        String url = request.getHeader("referer");
        String lang = request.getParameter("lang");
        request.getSession().setAttribute("locale", Objects.requireNonNullElse(lang, "en"));
        Config.set(request, Config.FMT_LOCALE, lang);
        log.info("Locale was set-->" + lang);
        MessageManager.changeLocale(new Locale(lang));

        return new Redirect(url);
    }
}
