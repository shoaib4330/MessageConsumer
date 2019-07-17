package com.kashegypt.gateway.notification.mail;

import com.kashegypt.gateway.notification.mail.jpa.MailTemplateRepository;
import com.kashegypt.gateway.notification.mail.mapper.MailMapper;
import com.kashegypt.gateway.notification.mail.dto.MailMessageRequest;
import com.kashegypt.gateway.notification.mail.jpa.model.MailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.URLDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailService {

    @Autowired
    private Session session;

    @Value(value = "${mail.inline.ke.logo.cid}")
    private String keLogoCID;

    @Value(value = "${mail.inline.ke.logo.path}")
    private String keLogoPath;

    public void email(MailMessageRequest message, String emailSubject, String mailContentBody) {
        Message email = new MimeMessage(session);

        InternetAddress from = createRecipient(message.getFrom());
        List<InternetAddress> toList = message.getTo().stream().map(to -> createRecipient(to.getEmail())).collect(Collectors.toList());

        try {
            email.setFrom(from);
            email.setRecipients(Message.RecipientType.TO, toList.toArray(new Address[toList.size()]));
            email.setSubject(emailSubject);
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mailContentBody, "text/html;charset=UTF-8");

            // add body/text/html
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            // add inline ke-logo
            multipart.addBodyPart(createImagePartFromFile(keLogoPath, keLogoCID));

            email.setContent(multipart);
            Transport.send(email);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add inline/embedded images from url
     * @param imgUrl
     * @param cid
     * @return MimeBodyPart (image body part)
     * @throws MessagingException
     */
    private MimeBodyPart createImagePartFromUrl(String imgUrl, String cid) throws MessagingException {
        MimeBodyPart imagePart = new MimeBodyPart();
        imagePart.setDataHandler(createDataHandler(imgUrl));
        imagePart.setHeader("Content-ID", cid);
        imagePart.setDisposition(MimeBodyPart.INLINE);
        return imagePart;
    }

    /**
     * Add inline/embedded images from local
     * @param imagePath
     * @param cid
     * @return MimeBodyPart (image body part)
     * @throws MessagingException
     */
    private MimeBodyPart createImagePartFromFile(String imagePath, String cid) throws MessagingException {
        MimeBodyPart imagePart = new MimeBodyPart();
        imagePart.setDataHandler(new DataHandler(this.getClass().getClassLoader().getResource(imagePath)));
        imagePart.setHeader("Content-ID", cid);
        imagePart.setDisposition(MimeBodyPart.INLINE);
        return imagePart;
    }

    private DataHandler createDataHandler(String imgUrl){
        URL url = null;
        try {
            url = new URL(imgUrl);
            URLDataSource ds = new URLDataSource(url);
            return new DataHandler(ds);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private InternetAddress createRecipient(String email) {
        try {
            return new InternetAddress(email);
        } catch (AddressException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem creating recipient");
        }
    }
}
