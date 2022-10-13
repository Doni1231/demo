package prod.java.service;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import prod.java.payload.UserDto;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class MailSenderService {
    private final JavaMailSender sender;

    private final Configuration config;

    public MailSenderService(JavaMailSender sender, Configuration config) {
        this.sender = sender;
        this.config = config;
    }

    public void sendEmailForVerification(UUID code, UserDto userDto, Boolean changing) throws MessagingException, IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        model.put("email", userDto.getEmail());
        model.put("changing", changing.toString());
        model.put("code", code);
        model.put("fullName", userDto.getFirstName() + " " + userDto.getLastName());
        sendEmailWithHtml(userDto.getEmail(), model, "email-template.ftl");
    }

    public void sendEmailWithHtml(String email, Map<String, Object> model, String htmlFile) throws MessagingException, IOException, TemplateException {
        MimeMessage message = sender.createMimeMessage();
        // set mediaType
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        // add attachment
//			helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
        Template t = config.getTemplate(htmlFile);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        helper.setTo(email);
        helper.setText(html, true);
        helper.setSubject("\uD83D\uDCDB noreply");
        sender.send(message);
    }


}
