package com.tpavlyshyn.fp.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTag extends SimpleTagSupport {
    private Date date;
    private String locale;
    String DATE_UA = "dd.MM.yyyy";
    String DATE_EN = "MM.dd.yyyy";

    private LocalDate localDate;
    public void setDate(Date date) {
        this.date = date;
        localDate = date.toLocalDate();
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public void doTag() throws IOException {
        JspWriter jspWriter = getJspContext().getOut();

        if (locale.equals("ua")) {
            jspWriter.write(localDate.format(DateTimeFormatter.ofPattern(DATE_UA)));
        } else {
            jspWriter.write(localDate.format(DateTimeFormatter.ofPattern(DATE_EN)));
        }
    }


}
