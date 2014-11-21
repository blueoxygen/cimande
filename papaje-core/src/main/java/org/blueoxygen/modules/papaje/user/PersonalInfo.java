/**
 * 
 */
package org.blueoxygen.modules.papaje.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.meruvian.yama.core.DefaultJpaPersistence;
import org.meruvian.yama.core.Updateable;
import org.meruvian.yama.core.user.JpaUser;

/**
 * @author dianw
 * 
 */
@Entity
@Table(name = "papaje_personal_info")
public class PersonalInfo extends DefaultJpaPersistence implements Updateable<PersonalInfo> {
	public enum Gender {
		M, F, OTHER
	}
	
	private JpaUser user;
	private Date birthdate;
	private String birthplace;
	private Gender gender = Gender.M;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	public JpaUser getUser() {
		return user;
	}

	public void setUser(JpaUser user) {
		this.user = user;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public void update(PersonalInfo info) {
		if (this.user != null && info.getUser() != null)
			this.user.update(info.getUser());
		this.birthdate = info.getBirthdate();
		this.birthplace = info.getBirthplace();
		this.gender = info.getGender();
	}
}
