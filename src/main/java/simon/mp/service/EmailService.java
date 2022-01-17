package simon.mp.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public String sendEmail(String receiver, String subject, String body) throws IOException {
        Email from = new Email("invoice@mp-order.simonyc.tech");

        Email to = new Email(receiver);
        Content content = new Content("text/html", body);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info(String.valueOf(response.getStatusCode()));
            return response.getBody();
        } catch (IOException ex) {
            throw ex;
        }
    }
}
