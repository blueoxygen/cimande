package org.blueoxygen.modules.papaje.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.blueoxygen.modules.papaje.language.Language;
import org.meruvian.yama.core.DefaultJpaPersistence;

@Entity
@Table(name = "papaje_user_language")
public class UserLanguage extends DefaultJpaPersistence {
	private PersonalInfo personalInfo;
	private String slanguage;
	private Language language;
	private boolean showInPublic = false;

	@ManyToOne
	@JoinColumn(name = "personal_info_id", nullable = false)
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}
	
	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public String getSlanguage() {
		return slanguage;
	}

	public void setSlanguage(String slanguage) {
		this.slanguage = slanguage;
	}

	@ManyToOne
	@JoinColumn(name = "language_id", nullable = true)
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Column(name = "is_show_in_public")
	public boolean isShowInPublic() {
		return showInPublic;
	}

	public void setShowInPublic(boolean showInPublic) {
		this.showInPublic = showInPublic;
	}
}
