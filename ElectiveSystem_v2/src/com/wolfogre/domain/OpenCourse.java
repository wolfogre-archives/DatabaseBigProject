package com.wolfogre.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Jason Song(wolfogre.com) on 2016/5/4.
 */
@Entity
@Table(name = "O")
public class OpenCourse {
	@Id
	private Integer o_id;
	private String c_id;
	private String t_id;
	private String d_term;
	private String o_room;
	private String o_time;
	private Integer o_cap;

	public Integer getO_id() {
		return o_id;
	}

	public void setO_id(Integer o_id) {
		this.o_id = o_id;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getT_id() {
		return t_id;
	}

	public void setT_id(String t_id) {
		this.t_id = t_id;
	}

	public String getD_term() {
		return d_term;
	}

	public void setD_term(String d_term) {
		this.d_term = d_term;
	}

	public String getO_room() {
		return o_room;
	}

	public void setO_room(String o_room) {
		this.o_room = o_room;
	}

	public String getO_time() {
		return o_time;
	}

	public void setO_time(String o_time) {
		this.o_time = o_time;
	}

	public Integer getO_cap() {
		return o_cap;
	}

	public void setO_cap(Integer o_cap) {
		this.o_cap = o_cap;
	}
}
