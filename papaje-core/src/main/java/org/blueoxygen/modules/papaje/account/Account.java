/**
 * 
 */
package org.blueoxygen.modules.papaje.account;

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
@Table(name = "papaje_account")
public class Account extends CimandeJpaPersistence {
	public enum AccountType {
		PHONE, EMAIL, IM, WEBSITE, OTHER
	}

	private String accountName;
	private String carrier;
	private AccountType accountType = AccountType.OTHER;

	@Column(name = "account_name", unique = true, nullable = false)
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	@Enumerated(EnumType.STRING)
	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
}
