package com.vehiclecontroler.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.vehiclecontroler.exception.MailException;

//import com.itextpdf.text.pdf.codec.Base64.InputStream;
/*
 @author  Rajnish
 it contail the username and password from the mail.properties file 
 and send the mail to the tecivnghost what we pass the mail id at the tiem of the sending mail

*/
public class SendMailUtil {

	private static String userName;
	private static String password;
	private static String sendingHost;
	private static String receivingHost;
	private String from;
	private String to;
	private String subject;
	private String text;
	private int sendingPort;

	

	public void  sendGmail( String to, String subject, String text) throws MailException
	{
		
		InputStream inputStream = SendMailUtil.class.getClassLoader().getResourceAsStream("gmail.properties");
		
		System.out.println(inputStream);
		Properties props = new Properties();
		try {
			props.load(inputStream);
			userName = props.getProperty("emailID");
			password = props.getProperty("password");
			sendingHost = props.getProperty("mail.smtp.host");
			receivingHost = props.getProperty("receivingHost");

		} catch (IOException e) {
			e.printStackTrace();
		}
		

		// This will send mail from -->sender@gmail.com to -->receiver@gmail.com

		
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
		 sendingPort = 587;
		// For a Gmail account--sending mails-- host and port shold be as
		// follows

		

		       props.put("mail.smtp.host", sendingHost);
	
               props.put("mail.smtp.password", password);

		       props.put("mail.smtp.auth", true);
		
               props.put("mail.stmp.user" , "userName");

	        //To use TLS
	        props.put("mail.smtp.auth", "true"); 
	        props.put("mail.smtp.starttls.enable", "true");
	       
	        //To use SSL
	        props.put("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.port", "465");

		//Createing a session for checking authentication with username and password
		Session session = Session.getDefaultInstance(props, 
			    new javax.mail.Authenticator(){
			        protected PasswordAuthentication getPasswordAuthentication() {
			            return new PasswordAuthentication(
			                userName, password);// Specify the Username and the PassWord
			        }
			});
		

		Message simpleMessage = new MimeMessage(session);

		// MIME stands for Multipurpose Internet Mail Extensions

		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;

		try {

			fromAddress = new InternetAddress(userName);
			toAddress = new InternetAddress(to);

		} catch (AddressException e) {

			e.printStackTrace();

		}

		try {

			simpleMessage.setFrom(fromAddress);

			simpleMessage.setRecipient(RecipientType.TO, toAddress);

			simpleMessage.setSubject(this.subject);

			simpleMessage.setText(this.text);

			Transport transport = session.getTransport("smtps");
			
		
			transport.connect(sendingHost, userName, password);
			
			Transport.send(simpleMessage);
			System.out.println("Mail send to "+this.to);

			

			//transport.close();

		} catch (MessagingException e) {
			System.out.println("mail not send");

			e.printStackTrace();
				throw new MailException("UNABLE TO SEND MAIL");
		}
		

	}
	public static void main(String[] args) throws MailException {
		SendMailUtil mailUtil = new SendMailUtil();
		mailUtil.sendGmail("smiley9677@gmail.com", "abc", "TEST");
	}
}
