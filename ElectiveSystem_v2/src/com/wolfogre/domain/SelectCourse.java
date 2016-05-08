package com.wolfogre.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Jason Song(wolfogre.com) on 2016/5/4.
 */
@Entity
@Table(name = "SO")
public class SelectCourse implements Serializable {
	@Id
	private String s_id;
	@Id
	private Integer o_id;

	private Double so_ps_score;

	private Double so_ks_score;

	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	public Integer getO_id() {
		return o_id;
	}

	public void setO_id(Integer o_id) {
		this.o_id = o_id;
	}

	public Double getSo_ps_score() {
		return so_ps_score;
	}

	public void setSo_ps_score(Double so_ps_score) {
		this.so_ps_score = so_ps_score;
	}

	public Double getSo_ks_score() {
		return so_ks_score;
	}

	public void setSo_ks_score(Double so_ks_score) {
		this.so_ks_score = so_ks_score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SelectCourse that = (SelectCourse) o;

		if (s_id != null ? !s_id.equals(that.s_id) : that.s_id != null) return false;
		return o_id != null ? o_id.equals(that.o_id) : that.o_id == null;

	}

	@Override
	public int hashCode() {
		int result = s_id != null ? s_id.hashCode() : 0;
		result = 31 * result + (o_id != null ? o_id.hashCode() : 0);
		return result;
	}
}
