package com.tpavlyshyn.fp.commands;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import com.tpavlyshyn.fp.entity.request.Request;
import com.tpavlyshyn.fp.exceptions.ServiceException;
import com.tpavlyshyn.fp.services.CruiseService;
import com.tpavlyshyn.fp.services.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.swing.text.StyleConstants;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;


public class PdfCommand implements Command {
    private final RequestService requestService;

    public PdfCommand(RequestService requestService) {
        this.requestService = requestService;
    }



    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/pdf");

        response.setHeader(
                "Content-disposition",
                "inline; filename='Downloaded.pdf'");

        Document document = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.setPageSize(PageSize.LETTER);
            document.setMargins(36, 72, 108, 180);
            document.setMarginMirroring(false);
            document.open();

            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 26, Font.UNDERLINE, BaseColor.BLACK);
            Paragraph title = new Paragraph("CompUs Company Report\n\n", font);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            Font f1 = new Font(Font.FontFamily.UNDEFINED, 13, Font.NORMAL, BaseColor.BLACK);
            int requestId = Integer.parseInt(request.getParameter("requestId"));
            String cruiseName = request.getParameter("cruiseName");
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            Date endDate = Date.valueOf(request.getParameter("endDate"));
            int amount = Integer.parseInt(request.getParameter("amount"));

            document.add(new Paragraph("Cruise: " + cruiseName, f1));
            document.add(new Paragraph("\nOrder ID: " + requestId, f1));
            document.add(new Paragraph("\nDates: " + startDate + " - " + endDate, f1));
            document.add(new Paragraph("\nNumber of tickets: " + amount + "\n\n\n", f1));

            document.addAuthor("CompUs company");
            document.addCreationDate();
            document.addCreator("CompUs company");
            document.addTitle("Cruise order report");

            int totalPrice = requestService.calculateTotalPrice(requestId);

            document.add(new LineSeparator(2f, 100, BaseColor.DARK_GRAY, Element.ALIGN_CENTER, 1f));
/*            document.add(new Paragraph("Sum to pay: "+ totalPrice));*/
            Paragraph price = new Paragraph("Sum to pay: "+ totalPrice);
            price.setAlignment(Element.ALIGN_RIGHT);
            document.add(price);
            Paragraph status = new Paragraph("\n PAID\n\n",new Font(Font.FontFamily.UNDEFINED, 13, Font.NORMAL, BaseColor.RED));
            status.setAlignment(Element.ALIGN_RIGHT);
            document.add(status);
            document.add(new LineSeparator(2f, 100, BaseColor.DARK_GRAY, Element.ALIGN_CENTER, 1f));
            writer.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new Forward(Path.PAGE__INDEX);
    }
}
