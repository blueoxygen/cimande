package org.blueoxygen.modules.papaje.category;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.blueoxygen.cimande.core.CimandeJpaPersistence;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "papaje_category")
public class Category extends CimandeJpaPersistence {
	private String name;
	private String description;
	private Category master;
	
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
	
	@JsonBackReference
	@org.codehaus.jackson.annotate.JsonBackReference
	@ManyToOne
	@JoinColumn(name = "master")
	public Category getMaster() {
		return master;
	}

	public void setMaster(Category master) {
		this.master = master;
	}
}
