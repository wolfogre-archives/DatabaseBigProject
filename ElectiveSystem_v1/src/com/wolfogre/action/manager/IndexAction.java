package com.wolfogre.action.manager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wolfogre.DbDao;

import java.sql.ResultSet;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/20.
 */
public class IndexAction extends ActionSupport {
	@Override
	public String execute() throws Exception {
		DbDao dbDao = new DbDao("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/elective_system_v1","root", "DBlocal");
		ResultSet resultSet = dbDao.query("select * from D where d_begin <= NOW() and d_end >= NOW()");

		if(resultSet.next()){
			ActionContext actionContext = ActionContext.getContext();
			String term = resultSet.getString("d_term");
			String splits[] = term.split("-");
			if(splits[1].equals("C"))
				term = "春季学期";
			if(splits[1].equals("X"))
				term = "夏季学期";
			if(splits[1].equals("Q"))
				term = "秋学期";
			if(splits[1].equals("D"))
				term = "冬季学期";
			term = splits[0] + "年" + term;
			actionContext.put("term",term);
		}

		dbDao.closeConnection();

		return SUCCESS;
	}
}
