package com.jsonJPA.jsonWorking.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Auto")
public class Auto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PK_ID")
	private Integer pk_Id;

	@Column(name = "C_id")
	private String c_Id;

	@Column(name = "SME_NAME")
	private String sme_Name;

	@Column(name = "JSONAUTO")
	private String jsonAuto;

	public Auto() {
	}

	public Auto(Integer pk_Id, String c_Id, String sme_Name, String jsonAuto) {
		this.pk_Id = pk_Id;
		this.c_Id = c_Id;
		this.sme_Name = sme_Name;
		this.jsonAuto = jsonAuto;
	}

	public Integer getPk_Id() {
		return pk_Id;
	}

	public void setPk_Id(Integer pk_Id) {
		this.pk_Id = pk_Id;
	}

	public String getC_Id() {
		return c_Id;
	}

	public void setC_Id(String c_Id) {
		this.c_Id = c_Id;
	}

	public String getSme_Name() {
		return sme_Name;
	}

	public void setSme_Name(String sme_Name) {
		this.sme_Name = sme_Name;
	}

	public String getJsonAuto() {
		return jsonAuto;
	}

	public void setJsonAuto(String jsonAuto) {
		this.jsonAuto = jsonAuto;
	}

	@Override
	public String toString() {
		return "Auto [pk_Id=" + pk_Id + ", c_Id=" + c_Id + ", sme_Name=" + sme_Name + ", jsonAuto=" + jsonAuto + "]";
	}
	
	

}
