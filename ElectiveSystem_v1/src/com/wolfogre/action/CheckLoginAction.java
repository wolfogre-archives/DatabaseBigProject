package com.wolfogre.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.wolfogre.domain.Manager;
import com.wolfogre.domain.Student;
import com.wolfogre.domain.Teacher;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/18.
 */
public class CheckLoginAction implements Action, ServletResponseAware {

	private String id;
	private String password;
	private String loginType;
	private HttpServletResponse httpServletResponse;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//狗日的对参数大小写敏感，参数必须是loginType，logintype不行
	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	@Override
	public String execute() throws Exception {
		String loginResult = "";
		ActionContext actionContext = ActionContext.getContext();
		if(getId() == null)
		{
			actionContext.put("error", "学号/工号不能为空！");
			return ERROR;
		}
		if(getPassword() == null)
		{
			actionContext.put("error", "密码不能为空！");
			return ERROR;
		}
		if(getLoginType() == null)
		{
			actionContext.put("error", "登录类型不能为空！");
			return ERROR;
		}

		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		//TODO:没有手动关闭Session，不确定需不需要

		if(getLoginType().equals("student")){
			Student student = (Student)session.get(Student.class,getId());
			if(student == null || !student.getS_pwd().equals(getPassword())) {
				actionContext.put("error", "学号或密码错误");
				return ERROR;
			} else{
				actionContext.getSession().put("master", student);
				httpServletResponse.sendRedirect("student/index.jsp");
				return null;
			}
		}

		if(getLoginType().equals("teacher")){
			Teacher teacher = (Teacher)session.get(Teacher.class,getId());
			if(teacher == null || !teacher.getT_pwd().equals(getPassword())) {
				actionContext.put("error", "工号或密码错误");
				return ERROR;
			} else{
				actionContext.getSession().put("master", teacher);
				httpServletResponse.sendRedirect("teacher/index.jsp");
				return null;
			}
		}

		if(getLoginType().equals("manager")){
			Manager manager = (Manager)session.get(Manager.class,getId());
			if(manager == null || !manager.getM_pwd().equals(getPassword())) {
				actionContext.put("error", "工号或密码错误");
				return ERROR;
			} else{
				actionContext.getSession().put("master", manager);
				httpServletResponse.sendRedirect("manager/index.jsp");
				return null;
			}
		}

		actionContext.put("error", "登录类型不合法！");
		return ERROR;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}
}
