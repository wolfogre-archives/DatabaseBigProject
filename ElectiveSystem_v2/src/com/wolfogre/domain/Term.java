package com.wolfogre.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/28.
 */
@Entity
@Table(name = "D")
public class Term {
	@Id
	String d_term;
	Date d_begin;
	Date d_end;
	Calendar d_sel_begin;
	Calendar d_sel_end;
	Calendar d_reg_begin;
	Calendar d_reg_end;
	Calendar d_inq_begin;
	Calendar d_inq_end;

	public String getD_term() {
		return d_term;
	}

	public void setD_term(String d_term) {
		this.d_term = d_term;
	}

	public Date getD_begin() {
		return d_begin;
	}

	public void setD_begin(Date d_begin) {
		this.d_begin = d_begin;
	}

	public Date getD_end() {
		return d_end;
	}

	public void setD_end(Date d_end) {
		this.d_end = d_end;
	}

	public Calendar getD_sel_begin() {
		return d_sel_begin;
	}

	public void setD_sel_begin(Calendar d_sel_begin) {
		this.d_sel_begin = d_sel_begin;
	}

	public Calendar getD_sel_end() {
		return d_sel_end;
	}

	public void setD_sel_end(Calendar d_sel_end) {
		this.d_sel_end = d_sel_end;
	}

	public Calendar getD_reg_begin() {
		return d_reg_begin;
	}

	public void setD_reg_begin(Calendar d_reg_begin) {
		this.d_reg_begin = d_reg_begin;
	}

	public Calendar getD_reg_end() {
		return d_reg_end;
	}

	public void setD_reg_end(Calendar d_reg_end) {
		this.d_reg_end = d_reg_end;
	}

	public Calendar getD_inq_begin() {
		return d_inq_begin;
	}

	public void setD_inq_begin(Calendar d_inq_begin) {
		this.d_inq_begin = d_inq_begin;
	}

	public Calendar getD_inq_end() {
		return d_inq_end;
	}

	public void setD_inq_end(Calendar d_inq_end) {
		this.d_inq_end = d_inq_end;
	}
}
