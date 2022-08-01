package com.tpavlyshyn.fp;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Properties;
import java.util.Random;

public class EmailMessageHelper {


    public static boolean sendMessage(HttpServletRequest request){

        String email = request.getParameter("email");
        int otpvalue = 0;
        HttpSession mySession = request.getSession();

        // sending otp
        Random rand = new Random();
        otpvalue = rand.nextInt(1255650);

        // Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tetty.pavlyshyn.ph@gmail.com", "nfcsblagqyewgnco");

            }
        });
        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Hello");
            message.setText("your OTP is: " + otpvalue);
            Transport.send(message);
            request.setAttribute("message", "OTP is sent to your email id");
            mySession.setAttribute("otp", otpvalue);
            mySession.setAttribute("email", email);
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validate(HttpServletRequest request){
        int value=Integer.parseInt(request.getParameter("otp"));
        HttpSession session=request.getSession();
        int otp=(int)session.getAttribute("otp");

        if (value==otp)
        {
/*            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("status", "success");*/
            return true;
        }
        else
        {
            request.setAttribute("message","wrong otp");
            return false;
        }
    }
}
