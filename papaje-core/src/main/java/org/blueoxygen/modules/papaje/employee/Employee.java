package org.blueoxygen.modules.papaje.employee;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.blueoxygen.cimande.core.CimandeJpaPersistence;
import org.blueoxygen.modules.papaje.user.PersonalInfo;

@Entity
@Table(name = "papaje_employee")
public class Employee extends CimandeJpaPersistence {
	private PersonalInfo personalInfo;
	private String personalPage;
	private String companyPage;
	private String blogPage;

	@ManyToOne
	@JoinColumn(name = "personal_info_id")
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}
	
	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}
	
	public String getPersonalPage() {
		return personalPage;
	}
	
	public void setPersonalPage(String personalPage) {
		this.personalPage = personalPage;
	}
	
	public String getCompanyPage() {
		return companyPage;
	}
	
	public void setCompanyPage(String companyPage) {
		this.companyPage = companyPage;
	}
	
	public String getBlogPage() {
		return blogPage;
	}
	
	public void setBlogPage(String blogPage) {
		this.blogPage = blogPage;
	}
}
