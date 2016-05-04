package com.wolfogre.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Jason Song(wolfogre.com) on 2016/5/4.
 */
@Entity
@Table(name = "C")
public class Course {
	@Id
	private String c_id;
	private String c_name;
	private Integer c_credit;

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public Integer getC_credit() {
		return c_credit;
	}

	public void setC_credit(Integer c_credit) {
		this.c_credit = c_credit;
	}
}
