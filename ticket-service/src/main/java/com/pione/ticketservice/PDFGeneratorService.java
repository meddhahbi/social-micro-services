package com.pione.ticketservice;
import java.io.File;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response, FullTicketResponse ticket) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph title = new Paragraph("This is your ticket", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph ticketInfo = new Paragraph("Ticket ID: " + ticket.getTicket().getId(), fontParagraph);
        Paragraph eventName = new Paragraph("Event Name: " + ticket.getEvent().getEventName(), fontParagraph);
        Paragraph eventDate = new Paragraph("Event Date Starts at: " + ticket.getEvent().getStartDate(), fontParagraph);
        Paragraph  price = new Paragraph("Prix: " + ticket.getTicket().getPrice()+" DNT ", fontParagraph);
        Paragraph nombre=new Paragraph("Tickets booked"+ticket.getTicket().getParticipants().size(),fontParagraph);
        document.add(title);
        document.add(ticketInfo);
        document.add(eventName);
        document.add(eventDate);
        document.add(price);
        document.add(nombre);
        String qrCodeText = "Your QR code text here"; // Replace with your QR code content
        int qrCodeWidth = 200;
        int qrCodeHeight = 200;
        String qrCodeImagePath = generateQRCode(qrCodeText, qrCodeWidth, qrCodeHeight);

        if (qrCodeImagePath != null) {
            // Load and add the QR code image to the PDF
            Image qrCodeImage = Image.getInstance(qrCodeImagePath);
            qrCodeImage.scaleAbsolute(qrCodeWidth, qrCodeHeight);
            document.add(qrCodeImage);

            // Delete the QR code image file after adding it to the PDF
            File qrCodeImageFile = new File(qrCodeImagePath);
            qrCodeImageFile.delete();
        }





        document.close();
    }
    private String generateQRCode(String text, int width, int height) {
        // Call your QR code generator class here
        try {
            QRCodeGenerator.generateQRCode(text, width, height, "png", "qrcode.png");
            return "qrcode.png";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
