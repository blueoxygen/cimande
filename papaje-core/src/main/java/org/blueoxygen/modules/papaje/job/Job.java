package org.blueoxygen.modules.papaje.job;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.blueoxygen.cimande.core.CimandeJpaPersistence;
import org.blueoxygen.modules.papaje.category.Category;
import org.blueoxygen.modules.papaje.company.Company;
import org.meruvian.yama.core.commons.JpaAddress;

@Entity
@Table(name = "papaje_job")
public class Job extends CimandeJpaPersistence {
	private String name;
	private String description;
	private String requirement;
	private String personalSpec;
	private String notes;
	private Company company;
	private Category category;
	private String type;
	private int experienceLevel;
	private String function;
	private JpaAddress address;
	private String code;
	private String sallary;
	private int applied;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getRequirement() {
		return requirement;
	}
	
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	
	@Column(name = "personal_spec")
	public String getPersonalSpec() {
		return personalSpec;
	}
	
	public void setPersonalSpec(String personalSpec) {
		this.personalSpec = personalSpec;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@ManyToOne
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "experience_level")
	public int getExperienceLevel() {
		return experienceLevel;
	}
	
	public void setExperienceLevel(int experienceLevel) {
		this.experienceLevel = experienceLevel;
	}
	
	public String getFunction() {
		return function;
	}
	
	public void setFunction(String function) {
		this.function = function;
	}

	@Embedded
	public JpaAddress getAddress() {
		return address;
	}
	
	public void setAddress(JpaAddress address) {
		this.address = address;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getSallary() {
		return sallary;
	}
	
	public void setSallary(String sallary) {
		this.sallary = sallary;
	}
	
	public int getApplied() {
		return applied;
	}
	
	public void setApplied(int applied) {
		this.applied = applied;
	}
}
