package Email;


import java.util.ArrayList;
import java.util.List;


import Utilities.EmailUtils;
import Utilities.ExtentReport;






public class Email_Sender {

	
 public static void main(String[] args) {
	
	/* final String senderEmail="ajinkyaqa591@gmail.com";
	 final String appPassword="swdr dgvg nghr qvgd";
	 final String recepientEmail="ajinkyaqa591@gmail.com";
	 
	 
	 //SMTP Server Properties
	 Properties prop=new Properties();
	 prop.put("mail.smtp.au	th", "true");
	 prop.put("mail.smtp.host", "smtp.gmail.com");
	 prop.put("mail.smtp.starttls.enable","true");
	 prop.put("mail.smtp.port","587");
			 
	 //Create Session with Authentication
	 Session session=Session.getInstance(prop, new Authenticator() {
		 protected PasswordAuthentication getPasswordAuthentication() {
			 
			 return new PasswordAuthentication(senderEmail, appPassword);
		 }
		 
	});
	 session.setDebug(true);
	 
	 
	 //Create Email Message
	 try
	 {
		
		 
		 Message message=new MimeMessage(session);
		 message.setFrom(new InternetAddress(senderEmail));
		 message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recepientEmail));
		 message.setSubject("Luxepolis Test Report");
		// message.setText("Hi Team \n \n Luxepolis Test cases Status Update \n \n Regards, \n QA Team");
		 
		 
		 //Email Body part
		 MimeBodyPart textpart=new MimeBodyPart();
		 textpart.setText("Hi Team \n \n Luxepolis Test cases Status Update \n \n Regards, \n QA Team");
		 
		 //Attachment part
		 MimeBodyPart attachmentpart=new MimeBodyPart();
		 String Filepath="C:\\Users\\ajinkya.more\\eclipse-workspaceAjinkya\\Ajinkya7-3\\luxepolis-test-framework"
		 		+ "\\src\\test\\Luxepolis_TestCase_Report\\Luxepolis.html";
		 System.out.println("Attach part is"+Filepath);
		 attachmentpart.attachFile(new File(Filepath));
		 
		 //Combine body and attachment parts
		 MimeMultipart multipart=new MimeMultipart();
		 multipart.addBodyPart(textpart);
		 multipart.addBodyPart(attachmentpart);
		 message.setContent(multipart);
		 
		 
		 //Send Email
		 jakarta.mail.Transport.send(message);
		 System.out.println("Email Send Successfully");
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 
		 
	 }*/
	 
	
 
	// ✅ Dynamic report path
     String reportPath = ExtentReport.ReportPath;
     System.out.println("Sending report: " + reportPath);

     // ✅ Add test counts manually if running standalone
     int passCount = 0;
     int failCount = 0;
     int skipCount = 0;
     List<String> failedTests = new ArrayList<>();

     // ✅ Call with all 5 parameters
     EmailUtils.sendTestReport(
         reportPath,
         passCount,
         failCount,
         skipCount,
         failedTests
     );
 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
