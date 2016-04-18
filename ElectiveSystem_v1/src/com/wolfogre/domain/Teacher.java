package com.wolfogre.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/18.
 */
@Entity
@Table(name = "T")
public class Teacher {
	@Id
	private String t_id;
	private String t_name;
	private String t_pwd;

	public String getT_id() {
		return t_id;
	}

	public void setT_id(String t_id) {
		this.t_id = t_id;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String getT_pwd() {
		return t_pwd;
	}

	public void setT_pwd(String t_pwd) {
		this.t_pwd = t_pwd;
	}
}
