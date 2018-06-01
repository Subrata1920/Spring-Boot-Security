package com.example.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

/**
 * 
 * @author subrata
 *
 */
@Entity
@Table(name="mst_role")
public class Role implements Serializable{

	private static final long serialVersionUID = -5837347135467909989L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id_pk")
	private Integer roleId;

	@Column(name = "role_code",length = 60)
	private String roleCode;

	@Column(name = "role_name",length = 60)
	private String roleName;

	@Column(name = "description")
	private String description;

	@Column(name = "created_date")
	@CreationTimestamp
	private Timestamp createdDate;

	@Column(name = "last_updated_date")
	@CreationTimestamp
	private Timestamp lastUpdatedDate;
	
	@OneToMany(mappedBy = "role", fetch=FetchType.LAZY)
	private List<RoleFeaturePermissionScheme> roleFeaturePermissionSchemes;

	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

	public List<RoleFeaturePermissionScheme> getRoleFeaturePermissionSchemes() {
		return roleFeaturePermissionSchemes;
	}

	public void setRoleFeaturePermissionSchemes(
			List<RoleFeaturePermissionScheme> roleFeaturePermissionSchemes) {
		this.roleFeaturePermissionSchemes = roleFeaturePermissionSchemes;
	}

}
