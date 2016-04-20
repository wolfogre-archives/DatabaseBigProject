package com.wolfogre.action.manager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wolfogre.Information;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/20.
 */
public class IndexAction extends ActionSupport {
	@Override
	public String execute() throws Exception {
		ActionContext actionContext = ActionContext.getContext();
		actionContext.put("term", Information.getNowTerm());
		return SUCCESS;
	}
}
