package SEENIT.Scripts.utils;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.search.FlagTerm;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.config.Architecture;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Store;

import javax.mail.search.FlagTerm;
//import com.testing.framework.EmailUtils;

public class clsEmailUtils extends MasterClass{

	private static final String HOST = "imap.gmail.com";
    private static final String PORT = "993";

    public static String getOtpFromGmail(String email, String password) throws Exception 
    {
        // Set mail properties
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imap.host", HOST);
        props.put("mail.imap.port", PORT);

        // Connect to email
        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore("imaps");
        store.connect(HOST, email, password);

        // Open inbox
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

        // Search for unread emails
        Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
        	String otp = null;
        Thread.sleep(10000);
        //messages=null;
        
        /*
        if(messages!= null && messages.length>0)
        {
        	ReportHelper.onTestPass("OTP received to the user and going to login in the system.");
        	testResults.put("OTP mail received", "Pass");
        }
        else
        {	currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
        	ReportHelper.generateLog("OTP is not received to the user and can not login in the system.");
        	ReportHelper.onTestFailure("OTP not received ");
        	testResults.put("OTP mail received", "Fail");
        	hitReport("OTP mail not Received Login Failed ", "Very High",currentReportPath,currentConnectionStatus);
        	
        }
                
        // Iterate through unread messages
        for (Message message : messages) {
            String subject = message.getSubject();
            if (subject.contains("Login Verification code")) { // Adjust to match your email subject
                Object content = message.getContent();
                if (content instanceof String) {
                    otp = extractOtp((String) content);
                } else if (content instanceof Multipart) {
                    Multipart multipart = (Multipart) content;
                    for (int i = 0; i < multipart.getCount(); i++) {
                        BodyPart bodyPart = multipart.getBodyPart(i);
                        if (bodyPart.isMimeType("text/plain")) {
                            otp = extractOtp(bodyPart.getContent().toString());
                            break;
                        }
                    }
                }
                message.setFlag(Flags.Flag.SEEN, true); // Mark as read
                break;
            }
        }

        inbox.close(false);
        store.close();
        return otp;
        */
        
        
        Arrays.sort(messages, Comparator.comparing(m -> {
            try {
                return m.getReceivedDate();
            } catch (MessagingException e) {
                return new Date(0);
            }
        }, Comparator.reverseOrder()));

       // String otp = null;
        Date latestMailDate = null;

        if (messages.length > 0) {
            Message latestMessage = messages[0]; // Pick the latest unread mail
            latestMailDate = latestMessage.getReceivedDate();
            String subject = latestMessage.getSubject();

            if (subject.contains("Login Verification code")) { // Adjust for your email subject
                Object content = latestMessage.getContent();

                if (content instanceof String) {
                    otp = extractOtp((String) content);
                } else if (content instanceof Multipart) {
                    Multipart multipart = (Multipart) content;
                    for (int i = 0; i < multipart.getCount(); i++) {
                        BodyPart bodyPart = multipart.getBodyPart(i);
                        if (bodyPart.isMimeType("text/plain")) {
                            otp = extractOtp(bodyPart.getContent().toString());
                            break;
                        }
                    }
                }
                latestMessage.setFlag(Flags.Flag.SEEN, true); // Mark as read
            }
        }

        inbox.close(false);
        store.close();

        // Format and print the email received date
        if (latestMailDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("Latest OTP Email Received At: " + sdf.format(latestMailDate));
        } else {
            System.out.println("No new OTP email found.");
        }

        return otp;
    }

    // Extract OTP from email content
    private static String extractOtp(String content) {
      //  return content.replaceAll("[^0-9]", "").substring(0, 6); // Adjust based on your OTP format
    	
    	String[] str = content.split("OTP : ");
    	//String[] str = content.split("TP : ");
    	String[] otp = str[1].split("</p><p><br></p><h5>Thanks");
    	return otp[0];
				
    }	
}
