package com.jsonJPA.jsonWorking.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RULES")
public class Rules {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@Column(name = "RULEBOX")
	private String ruleBox;

	@Column(name = "VALUE")
	private String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuleBox() {
		return ruleBox;
	}

	public void setRuleBox(String ruleBox) {
		this.ruleBox = ruleBox;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Rules [id=" + id + ", ruleBox=" + ruleBox + ", value=" + value + "]";
	}

}
