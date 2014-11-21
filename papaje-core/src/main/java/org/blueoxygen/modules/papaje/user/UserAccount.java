/**
 * 
 */
package org.blueoxygen.modules.papaje.user;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.blueoxygen.modules.papaje.account.Account;
import org.meruvian.yama.core.DefaultJpaPersistence;

/**
 * @author dianw
 * 
 */
@Entity
@Table(name = "papaje_user_account", uniqueConstraints = { @UniqueConstraint(columnNames = {"personal_info_id", "account_id" }) })
public class UserAccount extends DefaultJpaPersistence {
	private PersonalInfo personalInfo;
	private Account account;

	@ManyToOne
	@JoinColumn(name = "personal_info_id", nullable = false)
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
