package org.blueoxygen.modules.papaje.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.core.DefaultJpaPersistence;

@Entity
@Table(name = "papaje_user_family")
public class UserFamily extends DefaultJpaPersistence {
	public enum Relationships {
		FATHER, MOTHER, BROTHER, SISTER
	}
	
	private PersonalInfo personalInfo;
	private PersonalInfo familyInfo;
	private Relationships relationships = Relationships.FATHER;
	private boolean showInPublic = false;

	@ManyToOne
	@JoinColumn(name = "personal_info_id", nullable = false)
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}
	
	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	@ManyToOne
	@JoinColumn(name = "family_info_id", nullable = false)
	public PersonalInfo getFamilyInfo() {
		return familyInfo;
	}
	
	public void setFamilyInfo(PersonalInfo familyInfo) {
		this.familyInfo = familyInfo;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public Relationships getRelationships() {
		return relationships;
	}
	
	public void setRelationships(Relationships relationships) {
		this.relationships = relationships;
	}

	@Column(name = "is_show_in_public")
	public boolean isShowInPublic() {
		return showInPublic;
	}

	public void setShowInPublic(boolean showInPublic) {
		this.showInPublic = showInPublic;
	}
}
