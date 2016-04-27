package com.wolfogre.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wolfogre.domain.Manager;
import com.wolfogre.domain.Student;
import com.wolfogre.domain.Teacher;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/27.
 */
public class LoginAction extends ActionSupport {
	private String id;
	private String password;
	private String loginType;

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

	public String login() throws Exception{
		ServletActionContext.getRequest().getSession().invalidate();
		ActionContext actionContext = ActionContext.getContext();
		actionContext.getParameters().clear();//TODO:不确定这有没有卵用
		return SUCCESS;
	}

	public String checkLogin() throws Exception{
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

		//三个应该只有一个有效，其他为null，这样写是为了能尽早关闭session
		Student student = (Student)session.get(Student.class,getId());
		Teacher teacher = (Teacher)session.get(Teacher.class,getId());
		Manager manager = (Manager)session.get(Manager.class,getId());

		session.close();
		sessionFactory.close();

		if(getLoginType().equals("student")){
			if(student == null || !student.getS_pwd().equals(getPassword())) {
				actionContext.put("error", "学号或密码错误");
				return ERROR;
			} else{
				actionContext.getSession().put("master", student);
				return "STUDENT";
			}
		}

		if(getLoginType().equals("teacher")){
			if(teacher == null || !teacher.getT_pwd().equals(getPassword())) {
				actionContext.put("error", "工号或密码错误");
				return ERROR;
			} else{
				actionContext.getSession().put("master", teacher);
				return "TEACHER";
			}
		}

		if(getLoginType().equals("manager")){
			if(manager == null || !manager.getM_pwd().equals(getPassword())) {
				actionContext.put("error", "工号或密码错误");
				return ERROR;
			} else{
				actionContext.getSession().put("master", manager);
				return "MANAGER";
			}
		}

		actionContext.put("error", "登录类型不合法！");
		return ERROR;
	}
}
