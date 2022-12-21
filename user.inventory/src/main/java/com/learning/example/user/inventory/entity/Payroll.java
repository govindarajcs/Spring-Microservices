package com.learning.example.user.inventory.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the payroll database table.
 * 
 */
@Entity
@NamedQuery(name="Payroll.findAll", query="SELECT p FROM Payroll p")
public class Payroll implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer basic;

	private Integer gross;
	
	@Id
	private Integer id;

	private byte nps;

	private Integer variable;

	//bi-directional one-to-one association to UserDetail
	@OneToOne(cascade={CascadeType.REMOVE})
	@JoinColumn(name="user_id")
	private UserDetail userDetail;

	public Payroll() {
	}

	public Integer getBasic() {
		return this.basic;
	}

	public void setBasic(Integer basic) {
		this.basic = basic;
	}

	public Integer getGross() {
		return this.gross;
	}

	public void setGross(Integer gross) {
		this.gross = gross;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte getNps() {
		return this.nps;
	}

	public void setNps(byte nps) {
		this.nps = nps;
	}

	public Integer getVariable() {
		return this.variable;
	}

	public void setVariable(Integer variable) {
		this.variable = variable;
	}

	public UserDetail getUserDetail() {
		return this.userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

}