package org.blueoxygen.cimande.roleprivilege;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.blueoxygen.cimande.core.CimandeJpaPersistence;
import org.blueoxygen.cimande.modulefunction.ModuleFunction;
import org.meruvian.yama.core.role.JpaRole;

@Entity
@Table(name = "cimande_role_privilege", uniqueConstraints = { @UniqueConstraint(columnNames = {"role_id", "module_function_id" }) })
public class RolePrivilege extends CimandeJpaPersistence {
	private JpaRole role = new JpaRole();
	private ModuleFunction moduleFunction = new ModuleFunction();

	@ManyToOne
	@JoinColumn(name = "role_id")
	public JpaRole getRole() {
		return role;
	}
	
	public void setRole(JpaRole role) {
		this.role = role;
	}

	@ManyToOne
	@JoinColumn(name = "module_function_id")
	public ModuleFunction getModuleFunction() {
		return moduleFunction;
	}

	public void setModuleFunction(ModuleFunction moduleFunction) {
		this.moduleFunction = moduleFunction;
	}
}
