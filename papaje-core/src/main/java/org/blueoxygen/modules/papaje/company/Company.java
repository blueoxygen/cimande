/**
 * 
 */
package org.blueoxygen.modules.papaje.company;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.meruvian.yama.core.DefaultJpaPersistence;
import org.meruvian.yama.core.commons.JpaAddress;

/**
 * @author dianw
 * 
 */
@Entity
@Table(name = "papaje_company")
public class Company extends DefaultJpaPersistence {
	public enum CompanyType {
		COMPANY, ORGANIZATION
	}

	private String name;
	private String description;
	private String website;
	private String email;
	private String phone1;
	private String phone2;
	private JpaAddress address = new JpaAddress();

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false, length = 500)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = false)
	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	@Embedded
	public JpaAddress getAddress() {
		return address;
	}

	public void setAddress(JpaAddress address) {
		this.address = address;
	}

}
