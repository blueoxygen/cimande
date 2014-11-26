package org.blueoxygen.cimande.modulefunction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.blueoxygen.cimande.core.CimandeJpaPersistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cimande_module_function")
public class ModuleFunction extends CimandeJpaPersistence {
	private String name;
	private String description;
	private int viewActive = 0;
	private String tableReferences;
	private List<ModuleFunction> moduleFunctions = new ArrayList<ModuleFunction>();
	private String moduleUrl;
	private ModuleFunction master;
	private String treePath;
	private int level = 0;
	
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

	@Column(name = "viewall_flag", length = 1)
	public int getViewActive() {
		return viewActive;
	}
	
	public void setViewActive(int viewActive) {
		this.viewActive = viewActive;
	}

	@Column(name = "ref_desc")
	public String getTableReferences() {
		return tableReferences;
	}
	
	public void setTableReferences(String tableReferences) {
		this.tableReferences = tableReferences;
	}

	@JsonIgnore
	@org.codehaus.jackson.annotate.JsonBackReference
	@OneToMany(mappedBy = "master", fetch = FetchType.LAZY)
	public List<ModuleFunction> getModuleFunctions() {
		return moduleFunctions;
	}
	
	public void setModuleFunctions(List<ModuleFunction> moduleFunctions) {
		this.moduleFunctions = moduleFunctions;
	}

	@Column(name = "module_url")
	public String getModuleUrl() {
		return moduleUrl;
	}
	
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	@JsonBackReference
	@org.codehaus.jackson.annotate.JsonBackReference
	@ManyToOne
	@JoinColumn(name = "master")
	public ModuleFunction getMaster() {
		return master;
	}
	
	public void setMaster(ModuleFunction master) {
		this.master = master;
	}

	@Column(name = "tree_path", length = 500)
	public String getTreePath() {
		return treePath;
	}
	
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
