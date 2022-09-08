package com.tpavlyshyn.fp.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ReportPdf {
    public static void main(String[] args) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
            document.open();
            document.add(new Paragraph("A Hello World PDF document."));
            document.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
