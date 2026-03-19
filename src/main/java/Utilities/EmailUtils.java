package Utilities;

import java.io.File;
import java.util.List;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtils {

	 public static void sendTestReport(
	            String reportPath,
	            int passCount,
	            int failCount,
	            int skipCount,
	            List<String> failedTests) {

	        final String senderEmail    = "ajinkyaqa591@gmail.com";
	        final String appPassword    = "swdr dgvg nghr qvgd";
	        final String recipientEmail = "ajinkyaqa591@gmail.com";

	        // SMTP Properties
	        Properties prop = new Properties();
	        prop.put("mail.smtp.auth",            "true");
	        prop.put("mail.smtp.host",            "smtp.gmail.com");
	        prop.put("mail.smtp.starttls.enable", "true");
	        prop.put("mail.smtp.port",            "587");
	        prop.put("mail.smtp.ssl.trust",       "smtp.gmail.com");

	        // Create Session
	        Session session = Session.getInstance(prop, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(senderEmail, appPassword);
	            }
	        });

	        try {
	            // ✅ Check report exists
	            File reportFile = new File(reportPath);
	            if (!reportFile.exists()) {
	                System.out.println("❌ Report not found: " + reportPath);
	                return;
	            }

	            // ✅ Set subject based on pass/fail
	            String subject = failCount > 0
	                ? "❌ Luxepolis Test Report — " + failCount + " Test(s) FAILED"
	                : "✅ Luxepolis Test Report — All Tests PASSED";

	            // ✅ Build failure details for email body
	            StringBuilder failDetails = new StringBuilder();
	            if (failCount > 0) {
	                failDetails.append("Failed Test Cases:\n");
	                failDetails.append("─────────────────────────\n");
	                for (String failed : failedTests) {
	                    failDetails.append(failed).append("\n");
	                }
	                failDetails.append("─────────────────────────\n\n");
	            }

	            // ✅ Email body with summary
	            String emailBody =
	                "Hi Team,\n\n"
	                + "Please find the Luxepolis Test Execution Report below:\n\n"
	                + "Test Summary:\n"
	                + "─────────────────────────\n"
	                + "✅ Passed  : " + passCount + "\n"
	                + "❌ Failed  : " + failCount + "\n"
	                + "⚠️ Skipped : " + skipCount + "\n"
	                + "─────────────────────────\n\n"
	                + failDetails.toString()
	                + "Please find the detailed report attached.\n\n"
	                + "Regards,\n"
	                + "QA Team";

	            // Create Message
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(senderEmail));
	            message.setRecipients(
	                Message.RecipientType.TO,
	                InternetAddress.parse(recipientEmail)
	            );
	            message.setSubject(subject);

	            // Email Body Part
	            MimeBodyPart textPart = new MimeBodyPart();
	            textPart.setText(emailBody);

	            // Attachment Part
	            MimeBodyPart attachmentPart = new MimeBodyPart();
	            attachmentPart.attachFile(reportFile);

	            // Combine
	            MimeMultipart multipart = new MimeMultipart();
	            multipart.addBodyPart(textPart);
	            multipart.addBodyPart(attachmentPart);
	            message.setContent(multipart);

	            // Send
	            Transport.send(message);
	            System.out.println("✅ Email Sent: " + subject);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	
	} 
		 
		 
		 
		 
		 
