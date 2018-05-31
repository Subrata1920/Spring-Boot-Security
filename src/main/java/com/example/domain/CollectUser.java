package com.example.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "collect_user", uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
public class CollectUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6142358483948073924L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "user_name")
	private String userName;

	private String password;

	@Column(name = "user_enable", columnDefinition = "boolean DEFAULT true")
	private boolean enabled=true;

	@Column(name = "credential_expired", columnDefinition = "boolean DEFAULT false")
	private boolean credentialexpired=false;

	@Column(name = "account_expired", columnDefinition = "boolean DEFAULT false")
	private boolean accountExpired=false;

	@Column(name = "account_locked", columnDefinition = "boolean DEFAULT false")
	private boolean accountLocked=false;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Role> role;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isCredentialexpired() {
		return credentialexpired;
	}

	public void setCredentialexpired(boolean credentialexpired) {
		this.credentialexpired = credentialexpired;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}
}
