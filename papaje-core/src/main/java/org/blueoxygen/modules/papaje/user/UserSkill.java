/**
 * 
 */
package org.blueoxygen.modules.papaje.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.blueoxygen.modules.papaje.skill.Skill;
import org.meruvian.yama.core.DefaultJpaPersistence;

/**
 * @author dianw
 * 
 */
@Entity
@Table(name = "papaje_user_skill")
public class UserSkill extends DefaultJpaPersistence {
	private String name;
	private String description;
	private PersonalInfo personalInfo;
	private Skill skill;
	private boolean showInPublic = false;

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 500)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name = "personal_info_id")
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	@ManyToOne
	@JoinColumn(name = "skill_id")
	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
	@Column(name = "is_show_in_public")
	public boolean isShowInPublic() {
		return showInPublic;
	}

	public void setShowInPublic(boolean showInPublic) {
		this.showInPublic = showInPublic;
	}

}
