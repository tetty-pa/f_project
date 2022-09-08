package com.tpavlyshyn.fp.tags;


import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;


import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class CurrencyTag extends SimpleTagSupport {
    private String locale;
    private int priceInDollars;

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setPriceInDollars(int priceInDollars) {
        this.priceInDollars = priceInDollars;
    }

    public CurrencyTag() {
    }

    @Override
    public void doTag() throws  IOException {
        JspWriter jspWriter = getJspContext().getOut();

        if (locale.equals("en")) {
            jspWriter.write(priceInDollars + "$");
        } else if (locale.equals("ua")) {
            float priceInUAH = new BigDecimal(40 * priceInDollars)
                    .setScale(2, RoundingMode.UP).floatValue();
            jspWriter.write(priceInUAH + "₴");
        }
    }
}
