package org.blueoxygen.cimande.core.mail;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.security.SessionCredentials;
import org.meruvian.yama.core.user.JpaUser;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DefaultMailManager implements MailManager {
	
	@Inject
	private MailSender mailSender;
	@Inject
    private SimpleMailMessage templateMessage;

	@Override
	@Async
	public void mail(final SimpleMailMessage simpleMailMessage) {
		try{
			System.out.println("Sending e-Mail");
			simpleMailMessage.setFrom(SessionCredentials.getCurrentSite().getNotifyEmail());
            mailSender.send(simpleMailMessage);
        } catch (MailException ex) {
            // simply log it and go on...
			System.out.println("Failed");
            System.err.println(ex.getMessage());
        }
		System.out.println("done");
	}

	@Override
	public void sendMail(JpaUser user, String sender) {
		SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
		if(StringUtils.isNotBlank(sender))
			msg.setFrom(sender);
        msg.setTo(user.getEmail());
        msg.setSubject("Test Email");
        msg.setText(
            "Dear " + user.getName().getFirst()
                + user.getName().getLast()
                + ", thank you for placing order. Your order number is ");
		mail(msg);
	}

}
