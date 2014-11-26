package org.blueoxygen.modules.papaje.language;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.blueoxygen.cimande.core.CimandeJpaPersistence;

@Entity
@Table(name = "papaje_language")
public class Language extends CimandeJpaPersistence {
	private String name;
	private String description;
	
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
	
}
