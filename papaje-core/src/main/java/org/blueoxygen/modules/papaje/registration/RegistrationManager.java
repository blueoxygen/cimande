package org.blueoxygen.modules.papaje.registration;

import org.meruvian.yama.core.user.User;

public interface RegistrationManager {
	User register(User user, String roleName);
}
