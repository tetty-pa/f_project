package com.tpavlyshyn.fp.commands.common;

import com.tpavlyshyn.fp.EmailMessageHelper;
import com.tpavlyshyn.fp.commands.Command;
import com.tpavlyshyn.fp.commands.Path;
import com.tpavlyshyn.fp.commands.action.Dispatcher;
import com.tpavlyshyn.fp.commands.action.Forward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ForgotPasswordCommand implements Command {
    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {
       /* String email = request.getParameter("email");
        RequestDispatcher dispatcher = null;
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
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("message", "OTP is sent to your email id");
        mySession.setAttribute("otp", otpvalue);
        mySession.setAttribute("email", email);
*/
        EmailMessageHelper.sendMessage(request);
        request.setAttribute("command", "validateOtp");
        return new Forward(Path.PAGE__ENTER_OTP);

    }
}