package com.wolfogre.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Jason Song(wolfogre.com) on 2016/5/4.
 */
@Entity
@Table(name = "SO")
public class SelectCourse {
	@Id
	private String s_id;
	@Id
	private Integer o_id;
	//TODO:不确定这样能不能搞联合主键，继续测试
}
