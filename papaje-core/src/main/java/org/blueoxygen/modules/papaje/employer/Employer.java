package org.blueoxygen.modules.papaje.employer;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.blueoxygen.cimande.core.CimandeJpaPersistence;
import org.blueoxygen.modules.papaje.company.Company;
import org.blueoxygen.modules.papaje.user.PersonalInfo;

@Entity
@Table(name = "papaje_employer")
public class Employer extends CimandeJpaPersistence {
	private PersonalInfo personalInfo;
	private Company company;

	@ManyToOne
	@JoinColumn(name = "personal_info_id")
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}
	
	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	@ManyToOne
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
