/**
 * 
 */
package org.blueoxygen.modules.papaje.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.blueoxygen.modules.papaje.company.Company;
import org.blueoxygen.modules.papaje.company.Company.CompanyType;
import org.meruvian.yama.core.DefaultJpaPersistence;

/**
 * @author dianw
 * 
 */
@Entity
@Table(name = "papaje_user_company")
public class UserCompany extends DefaultJpaPersistence {
	private String posistion;
	private Date dateFrom;
	private Date dateTo;
	private String story;
	private String companyName;
	private CompanyType companyType = CompanyType.COMPANY;
	private Company company;
	private PersonalInfo personalInfo;
	private boolean showInPublic = false;

	public String getPosition() {
		return posistion;
	}

	public void setPosition(String posistion) {
		this.posistion = posistion;
	}

	@Column(name = "date_from")
	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	@Column(name = "date_to")
	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	@Column(length = 500)
	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	@Column(name = "company_name")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "company_type")
	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	@ManyToOne
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne
	@JoinColumn(name = "personal_info_id")
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public String getPosistion() {
		return posistion;
	}

	public void setPosistion(String posistion) {
		this.posistion = posistion;
	}

	@Column(name = "is_show_in_public")
	public boolean isShowInPublic() {
		return showInPublic;
	}

	public void setShowInPublic(boolean showInPublic) {
		this.showInPublic = showInPublic;
	}
}
