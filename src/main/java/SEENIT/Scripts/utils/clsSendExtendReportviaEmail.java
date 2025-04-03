package SEENIT.Scripts.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.io.File;

public class clsSendExtendReportviaEmail {

	public static void sendEmailWithAttachment(String toEmailId, String mailSubject, String mailBody, String extenReportPath) 
	{
        // Sender's email credentials
		final String fromEmail = "humbqa@gmail.com"; // Sender's email
        final String password = "awqw hcof nyci oyuj";          // Sender's email password

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");  // SMTP Host
        props.put("mail.smtp.port", "587");            // TLS Port
        props.put("mail.smtp.auth", "true");           // Enable Authentication
        props.put("mail.smtp.starttls.enable", "true"); // Enable StartTLS

        // Create a Session object to connect to the mail server
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Create a MimeMessage
            MimeMessage message = new MimeMessage(session);

            // Set email fields
            message.setFrom(new InternetAddress(fromEmail));
            
            
           // New Code for multiple person
            
            InternetAddress[] recipientAddresses = InternetAddress.parse(toEmailId);
            message.addRecipients(Message.RecipientType.TO, recipientAddresses);
            //old code for single Mail ID
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmailId));
            message.setSubject(mailSubject);
         
            // Email body
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText(mailBody);

            // New Code For HTML Body Part
            MimeBodyPart htmlBodyPart = new MimeBodyPart();
            htmlBodyPart.setContent(mailBody, "text/html");
            
            // Attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(extenReportPath));

            // Combine body and attachment
            Multipart multipart = new MimeMultipart();
           //old Code 
           // multipart.addBodyPart(messageBodyPart);
           // New Code
            multipart.addBodyPart(htmlBodyPart);
            multipart.addBodyPart(attachmentPart);

            // Set content to the message
            message.setContent(multipart);

            // Send email
            Transport.send(message);

            System.out.println("Email sent successfully!");
            ReportHelper.generateLog("Email sent successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
