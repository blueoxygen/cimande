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
import javax.persistence.UniqueConstraint;

import org.blueoxygen.modules.papaje.education.EducationUnit;
import org.blueoxygen.modules.papaje.education.EducationUnit.EducationUnitType;
import org.meruvian.yama.core.DefaultJpaPersistence;

/**
 * @author dianw
 * 
 */
@Entity
@Table(name = "papaje_education", uniqueConstraints = { @UniqueConstraint(columnNames = { "personal_info_id", "education_unit_id" }) })
public class UserEducation extends DefaultJpaPersistence {
	private String schoolName;
	private String majors;
	private EducationUnitType educationUnitType;
	private Date dateFrom;
	private Date dateTo;
	private boolean graduated = false;
	private String description;
	private PersonalInfo personalInfo;
	private EducationUnit educationUnit;
	private boolean showInPublic = false;

	@Column(name = "school_name")
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public String getMajors() {
		return majors;
	}
	
	public void setMajors(String majors) {
		this.majors = majors;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "education_unit_type")
	public EducationUnitType getEducationUnitType() {
		return educationUnitType;
	}
	
	public void setEducationUnitType(EducationUnitType educationUnitType) {
		this.educationUnitType = educationUnitType;
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

	public boolean isGraduated() {
		return graduated;
	}

	public void setGraduated(boolean graduated) {
		this.graduated = graduated;
	}

	@Column(length = 500)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name = "personal_info_id", nullable = false)
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	@ManyToOne
	@JoinColumn(name = "education_unit_id")
	public EducationUnit getEducationUnit() {
		return educationUnit;
	}

	public void setEducationUnit(EducationUnit educationUnit) {
		this.educationUnit = educationUnit;
	}

	@Column(name = "is_show_in_public")
	public boolean isShowInPublic() {
		return showInPublic;
	}

	public void setShowInPublic(boolean showInPublic) {
		this.showInPublic = showInPublic;
	}
	
}
