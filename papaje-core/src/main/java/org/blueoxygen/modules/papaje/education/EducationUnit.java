/**
 * 
 */
package org.blueoxygen.modules.papaje.education;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.blueoxygen.cimande.core.CimandeJpaPersistence;

/**
 * @author dianw
 * 
 */
@Entity
@Table(name = "papaje_education_unit")
public class EducationUnit extends CimandeJpaPersistence {
	public enum EducationUnitType {
		UNIVERSITY, HIGH_SCHOOL, OTHER
	}
	
	private String name;
	private String description;
	private EducationUnitType educationType = EducationUnitType.UNIVERSITY;

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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "education_unit_type", nullable = false)
	public EducationUnitType getEducationUnitType() {
		return educationType;
	}
	
	public void setEducationUnitType(EducationUnitType educationType) {
		this.educationType = educationType;
	}
}
