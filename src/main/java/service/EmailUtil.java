package service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {
	final String CONFIG = "config";
	CommonService service = new CommonServiceImpl();

	public String readContentFromTemplate(String filePath) {
		StringBuilder contents = new StringBuilder();
		if (filePath.substring(0, 4).toLowerCase().equals("http")) {
			try {
				URL url = new URL(filePath);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(url.openStream(), "UTF-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {
					contents.append(line);
				}
				reader.close();
				return contents.toString();
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(
						filePath));
				String line = null;
				while ((line = reader.readLine()) != null) {
					contents.append(line);
				}
				reader.close();
				return contents.toString();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

   public String editEmailContentBasedOnCustomer(String filePath,
			Map<String, String> input) {
		String content = readContentFromTemplate(filePath);
		Set<Entry<String, String>> entries = input.entrySet();
		for (Map.Entry<String, String> entry : entries) {
			try {
				content = content.replace(entry.getKey().trim(), entry
						.getValue().trim());
			} catch (Exception e) {
				content = content.replace(entry.getKey().trim(), "Not found");
			}
		}
		return content;
	}

	public void sendHTMLEmail(String content, String subject, String recipient, String senderName, String sender) {
		MimeMultipart multiPart = new MimeMultipart();
		BodyPart bodyPart = new MimeBodyPart();
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", service.getValueByKey("HOST"));
			props.put("mail.smtp.auth", "true");
			props.put("mail.transport.protocol", service.getValueByKey("PROTOCOL"));
			props.put("mail.smtp.socketFactory.class", service.getValueByKey("SOCKET"));
			props.put("mail.smtp.port", service.getValueByKey("PORT"));
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.user", service.getValueByKey("EMAIL_USER"));
			props.put("mail.smtp.password", service.getValueByKey("EMAIL_PASS"));
			bodyPart.setContent(content, "text/html");
			multiPart.addBodyPart(bodyPart);
			Session session = Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender, senderName));
			message.setSubject(subject);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setContent(multiPart);
			Transport transport = session.getTransport(service.getValueByKey("PROTOCOL"));
			transport.connect(service.getValueByKey("HOST"), service.getValueByKey("EMAIL_USER"),
					service.getValueByKey("EMAIL_PASS"));
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
