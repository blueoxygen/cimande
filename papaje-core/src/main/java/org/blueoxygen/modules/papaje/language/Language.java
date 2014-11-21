package org.blueoxygen.modules.papaje.language;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.meruvian.yama.core.DefaultJpaPersistence;

@Entity
@Table(name = "papaje_language")
public class Language extends DefaultJpaPersistence {
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
