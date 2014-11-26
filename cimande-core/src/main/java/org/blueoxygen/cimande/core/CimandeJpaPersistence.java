package org.blueoxygen.cimande.core;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.meruvian.yama.core.DefaultPersistence;

@MappedSuperclass
public class CimandeJpaPersistence implements DefaultPersistence {
	protected String id;
	protected CimandeJpaLogInformation logInformation = new CimandeJpaLogInformation();
	
	@Id()
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Embedded
	public CimandeJpaLogInformation getLogInformation() {
		return logInformation;
	}

	public void setLogInformation(CimandeJpaLogInformation logInformation) {
		this.logInformation = logInformation;
	}
}
