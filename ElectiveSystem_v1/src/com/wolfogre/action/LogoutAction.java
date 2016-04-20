package com.wolfogre.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/18.
 */
public class LogoutAction extends ActionSupport{
	@Override
	public String execute() throws Exception {
		ServletActionContext.getRequest().getSession().invalidate();
		return SUCCESS;
	}
}
