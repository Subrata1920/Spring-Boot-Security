package com.example.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Role implements Serializable{

	private static final long serialVersionUID = -5837347135467909989L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "role_code")
	private String roleCode;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "description")
	private String description;

	@Column(name = "created_date")
	@CreationTimestamp
	private Timestamp createdDate;

	@Column(name = "last_updated_date")
	@UpdateTimestamp
	private Timestamp lastUpdatedDate;
	
	@ManyToOne
	@JoinColumn(name="user_id_fk")
	private CollectUser user;

	public Role() {
		super();
	}

	public Integer getRoleId() {
		return roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Role(int roleId) {
		this.roleId=roleId;
	}

	public CollectUser getUser() {
		return user;
	}

	public void setUser(CollectUser user) {
		this.user = user;
	}
}
