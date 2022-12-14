package tools;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

  public static void enviar() {

    final String fromEmail = "no.reply.cesur@gmail.com";
    final String password = "dxvwontalaqvcalp";
    final String toEmail = "lytos_87@hotmail.com"; // can be any email id

    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    props.setProperty("mail.smtp.starttls.enable", "true");
    props.setProperty("mail.smtp.port", "587");
    props.setProperty("mail.smtp.user", fromEmail);
    props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
    props.setProperty("mail.smtp.auth", "true");

    Session session = Session.getInstance(props);

    try {

      MimeMessage msg = new MimeMessage(session);

      // set message headers
      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
      msg.addHeader("format", "flowed");
      msg.addHeader("Content-Transfer-Encoding", "8bit");

      msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));
      msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

      msg.setSubject("Este es el asunto JIMENEZ", "UTF-8");
      msg.setText("A jimenez le gusta mucho las..................", "ISO-8859-1", "html");
      msg.setSentDate(new Date());
     
      Transport mTransport = session.getTransport("smtp");
      mTransport.connect(fromEmail, password);
      mTransport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
      mTransport.close();

      System.out.println("EMail Sent Successfully!!");

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}