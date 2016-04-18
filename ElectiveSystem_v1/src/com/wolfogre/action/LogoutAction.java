package com.wolfogre.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/18.
 */
public class LogoutAction implements Action, ServletRequestAware, ServletResponseAware{

	private HttpServletRequest httpServletRequest;
	private HttpServletResponse httpServletResponse;

	@Override
	public String execute() throws Exception {
		httpServletRequest.getSession().invalidate();
		httpServletResponse.sendRedirect("/index.jsp");
		return null;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}
}
