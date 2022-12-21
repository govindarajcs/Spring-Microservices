package com.learning.example.rbac.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the service database table.
 * 
 */
@Entity
@NamedQuery(name="Service.findAll", query="SELECT s FROM Endpoint s")
@Table(name = "service")
public class Endpoint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="http_method")
	private String httpMethod;

	@Column(name="suffix_path")
	private String suffixPath;

	//bi-directional many-to-one association to Permission
	@OneToMany(mappedBy="service")
	private List<Permission> permissions;

	public Endpoint() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHttpMethod() {
		return this.httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getSuffixPath() {
		return this.suffixPath;
	}

	public void setSuffixPath(String suffixPath) {
		this.suffixPath = suffixPath;
	}

	public List<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public Permission addPermission(Permission permission) {
		getPermissions().add(permission);
		permission.setService(this);

		return permission;
	}

	public Permission removePermission(Permission permission) {
		getPermissions().remove(permission);
		permission.setService(null);

		return permission;
	}
}