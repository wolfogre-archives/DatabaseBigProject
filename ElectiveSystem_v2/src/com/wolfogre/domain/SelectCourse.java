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
	//TODO:不确定这样能不能搞联合主键，继续测试

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
