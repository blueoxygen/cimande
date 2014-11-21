/**
 * 
 */
package org.blueoxygen.modules.papaje.skill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.core.DefaultJpaPersistence;

/**
 * @author dianw
 * 
 */
@Entity
@Table(name = "papaje_skill")
public class Skill extends DefaultJpaPersistence {
	private String name;
	private String description;
	private SkillCategory category;

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
	@JoinColumn(name = "skill_category_id")
	public SkillCategory getCategory() {
		return category;
	}

	public void setCategory(SkillCategory category) {
		this.category = category;
	}
}
