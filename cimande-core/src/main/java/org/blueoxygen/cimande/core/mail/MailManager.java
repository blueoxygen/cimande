package org.blueoxygen.cimande.core.mail;

import org.meruvian.yama.core.user.JpaUser;
import org.springframework.mail.SimpleMailMessage;

public interface MailManager {
	void mail(SimpleMailMessage simpleMailMessage);
	
	void sendMail(JpaUser user, String sender);
}
