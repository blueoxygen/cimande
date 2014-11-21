package org.blueoxygen.modules.papaje.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.core.DefaultJpaPersistence;

@Entity
@Table(name = "papaje_user_reference")
public class UserReference extends DefaultJpaPersistence {
	private String name;
	private String position;
	private String relationships;
	private PersonalInfo personalInfo;
	private boolean showInPublic = false;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getRelationships() {
		return relationships;
	}
	
	public void setRelationships(String relationships) {
		this.relationships = relationships;
	}

	@ManyToOne
	@JoinColumn(name = "personal_info_id", nullable = false)
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}
	
	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	@Column(name = "is_show_in_public")
	public boolean isShowInPublic() {
		return showInPublic;
	}
	
	public void setShowInPublic(boolean showInPublic) {
		this.showInPublic = showInPublic;
	}
	
}
