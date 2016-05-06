package com.wolfogre.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wolfogre.Information;
import com.wolfogre.domain.*;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/27.
 */
public class ManagerAction extends ActionSupport{
	private Configuration configuration;
	private ServiceRegistry serviceRegistry;
	private SessionFactory sessionFactory;
	private Session session;

	public ManagerAction(){
		configuration = new Configuration().configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
	}

	@Override
	protected void finalize() throws Throwable {
		session.close();
		sessionFactory.close();
		super.finalize();
	}

	public String index() throws Exception{
		return SUCCESS;
	}
	public String term() throws Exception{
		ActionContext actionContext = ActionContext.getContext();

		Term nowTerm = Information.getTerm();
		if(nowTerm == null) {
			actionContext.put("error","当前学期未定义");
			return SUCCESS;
		}

		actionContext.put("term", nowTerm);
		return SUCCESS;
	}

	public String updateTerm() throws Exception{
		ActionContext actionContext = ActionContext.getContext();

		Term nowTerm = Information.getTerm();
		if(nowTerm == null) {
			actionContext.put("error","当前学期未定义");
			return ERROR;
		}

		Transaction transaction = session.beginTransaction();
		Term term = (Term)session.get(Term.class, nowTerm.getD_term());

		try{
			if(actionContext.getParameters().get("sel_begin") != null && actionContext.getParameters().get("sel_end") != null)
			{
				java.util.Date begin = Information.getDateFormat().parse(((String[])actionContext.getParameters().get("sel_begin"))[0]);
				java.util.Date end = Information.getDateFormat().parse(((String[])actionContext.getParameters().get("sel_end"))[0]);
				term.getD_sel_begin().setTime(begin);
				term.getD_sel_end().setTime(end);
				transaction.commit();
				return SUCCESS;
			}
			if(actionContext.getParameters().get("reg_begin") != null && actionContext.getParameters().get("reg_end") != null)
			{
				java.util.Date begin = Information.getDateFormat().parse(((String[])actionContext.getParameters().get("reg_begin"))[0]);
				java.util.Date end = Information.getDateFormat().parse(((String[])actionContext.getParameters().get("reg_end"))[0]);
				term.getD_reg_begin().setTime(begin);
				term.getD_reg_end().setTime(end);
				transaction.commit();
				return SUCCESS;
			}
			if(actionContext.getParameters().get("inq_begin") != null && actionContext.getParameters().get("inq_end") != null)
			{
				java.util.Date begin = Information.getDateFormat().parse(((String[])actionContext.getParameters().get("inq_begin"))[0]);
				java.util.Date end = Information.getDateFormat().parse(((String[])actionContext.getParameters().get("inq_end"))[0]);
				term.getD_inq_begin().setTime(begin);
				term.getD_inq_end().setTime(end);
				transaction.commit();
				return SUCCESS;
			}
		} catch (ParseException pe){
			actionContext.put("error","日期时间格式有误");
			transaction.rollback();
			return ERROR;
		}

		actionContext.put("error","请确认参数");
		return ERROR;
	}

	public String student() throws Exception {
		List<Student> studentList = session.createSQLQuery("SELECT * FROM S").addEntity(Student.class).list();
		ActionContext actionContext = ActionContext.getContext();
		actionContext.put("studentList",studentList);
		return SUCCESS;
	}

	public String updateStudent() throws Exception{
		ActionContext actionContext = ActionContext.getContext();

		Transaction transaction = session.beginTransaction();
		try{
			if(actionContext.getParameters().get("delete_data") != null)
			{
				String[] parameter = ((String[])actionContext.getParameters().get("cb_delete"));
				if(parameter.length == 0){
					actionContext.put("error","请选择目标");
					return ERROR;
				}
				for(String id:parameter){
					session.delete(session.get(Student.class, id));
				}
				transaction.commit();
				return SUCCESS;
			}

			if(actionContext.getParameters().get("new_data") != null)
			{
				Student newStudent = new Student();
				newStudent.setS_id(((String[])actionContext.getParameters().get("s_id"))[0]);
				newStudent.setS_name(((String[])actionContext.getParameters().get("s_name"))[0]);
				newStudent.setS_pwd(((String[])actionContext.getParameters().get("s_pwd"))[0]);
				session.save(newStudent);
				transaction.commit();
				return SUCCESS;
			}
		} catch (Exception ex){
			actionContext.put("error",ex.getMessage());
			return ERROR;
		}

		actionContext.put("error","请确认参数");
		return ERROR;
	}

	public String teacher() throws Exception {
		List<Teacher> teacherList = session.createSQLQuery("SELECT * FROM T").addEntity(Teacher.class).list();
		ActionContext actionContext = ActionContext.getContext();
		actionContext.put("teacherList",teacherList);
		return SUCCESS;
	}

	public String updateTeacher() throws Exception{
		ActionContext actionContext = ActionContext.getContext();

		Transaction transaction = session.beginTransaction();
		try{
			if(actionContext.getParameters().get("delete_data") != null)
			{
				String[] parameter = ((String[])actionContext.getParameters().get("cb_delete"));
				if(parameter.length == 0){
					actionContext.put("error","请选择目标");
					return ERROR;
				}
				for(String id:parameter){
					session.delete(session.get(Teacher.class, id));
				}
				transaction.commit();
				return SUCCESS;
			}

			if(actionContext.getParameters().get("new_data") != null)
			{
				Teacher newTeacher = new Teacher();
				newTeacher.setT_id(((String[])actionContext.getParameters().get("t_id"))[0]);
				newTeacher.setT_name(((String[])actionContext.getParameters().get("t_name"))[0]);
				newTeacher.setT_pwd(((String[])actionContext.getParameters().get("t_pwd"))[0]);
				session.save(newTeacher);
				transaction.commit();
				return SUCCESS;
			}
		} catch (Exception ex){
			actionContext.put("error",ex.getMessage());
			return ERROR;
		}

		actionContext.put("error","请确认参数");
		return ERROR;
	}

	public String manager() throws Exception {
		List<Manager> managerList = session.createSQLQuery("SELECT * FROM M").addEntity(Manager.class).list();
		ActionContext actionContext = ActionContext.getContext();
		actionContext.put("managerList",managerList);
		return SUCCESS;
	}

	public String updateManager() throws Exception{
		ActionContext actionContext = ActionContext.getContext();

		Transaction transaction = session.beginTransaction();
		try{
			if(actionContext.getParameters().get("delete_data") != null)
			{
				String[] parameter = ((String[])actionContext.getParameters().get("cb_delete"));
				if(parameter.length == 0){
					actionContext.put("error","请选择目标");
					return ERROR;
				}
				for(String id:parameter){
					session.delete(session.get(Manager.class, id));
				}
				transaction.commit();
				return SUCCESS;
			}

			if(actionContext.getParameters().get("new_data") != null)
			{
				Manager newManager = new Manager();
				newManager.setM_id(((String[])actionContext.getParameters().get("m_id"))[0]);
				newManager.setM_name(((String[])actionContext.getParameters().get("m_name"))[0]);
				newManager.setM_pwd(((String[])actionContext.getParameters().get("m_pwd"))[0]);
				session.save(newManager);
				transaction.commit();
				return SUCCESS;
			}
		} catch (Exception ex){
			actionContext.put("error",ex.getMessage());
			return ERROR;
		}

		actionContext.put("error","请确认参数");
		return ERROR;
	}


	public String course() throws Exception {
		List<Course> courseList = session.createSQLQuery("SELECT * FROM C").addEntity(Course.class).list();
		ActionContext actionContext = ActionContext.getContext();
		actionContext.put("courseList",courseList);
		return SUCCESS;
	}

	public String updateCourse() throws Exception{
		ActionContext actionContext = ActionContext.getContext();

		Transaction transaction = session.beginTransaction();
		try{
			if(actionContext.getParameters().get("delete_data") != null)
			{
				String[] parameter = ((String[])actionContext.getParameters().get("cb_delete"));
				if(parameter.length == 0){
					actionContext.put("error","请选择目标");
					return ERROR;
				}
				for(String id:parameter){
					session.delete(session.get(Course.class, id));
				}
				transaction.commit();
				return SUCCESS;
			}

			if(actionContext.getParameters().get("new_data") != null)
			{
				Course newCourse = new Course();
				newCourse.setC_id(((String[])actionContext.getParameters().get("c_id"))[0]);
				newCourse.setC_name(((String[])actionContext.getParameters().get("c_name"))[0]);
				newCourse.setC_credit(Integer.parseInt(((String[])actionContext.getParameters().get("c_credit"))[0]));
				session.save(newCourse);
				transaction.commit();
				return SUCCESS;
			}
		} catch (Exception ex){
			actionContext.put("error",ex.getMessage());
			return ERROR;
		}

		actionContext.put("error","请确认参数");
		return ERROR;
	}

	public String openCourse() throws Exception {
		List<OpenCourse> openCourseList = session.createSQLQuery("SELECT * FROM O").addEntity(OpenCourse.class).list();
		ActionContext actionContext = ActionContext.getContext();
		actionContext.put("openCourseList",openCourseList);
		return SUCCESS;
	}

	public String updateOpenCourse() throws Exception{
		ActionContext actionContext = ActionContext.getContext();

		Transaction transaction = session.beginTransaction();
		try{
			if(actionContext.getParameters().get("delete_data") != null)
			{
				String[] parameter = ((String[])actionContext.getParameters().get("cb_delete"));
				if(parameter.length == 0){
					actionContext.put("error","请选择目标");
					return ERROR;
				}
				for(String id:parameter){
					session.delete(session.get(OpenCourse.class, id));
				}
				transaction.commit();
				return SUCCESS;
			}

			if(actionContext.getParameters().get("new_data") != null)
			{
				OpenCourse newOpenCourse = new OpenCourse();
				newOpenCourse.setO_id(0);//这句无意义，id会自动分配，但不写还不行
				newOpenCourse.setC_id(((String[])actionContext.getParameters().get("c_id"))[0]);
				newOpenCourse.setT_id(((String[])actionContext.getParameters().get("t_id"))[0]);
				newOpenCourse.setD_term(((String[])actionContext.getParameters().get("d_term"))[0]);
				newOpenCourse.setO_room(((String[])actionContext.getParameters().get("o_room"))[0]);
				newOpenCourse.setO_time(((String[])actionContext.getParameters().get("o_time"))[0]);
				newOpenCourse.setO_cap(Integer.parseInt(((String[])actionContext.getParameters().get("o_cap"))[0]));
				session.save(newOpenCourse);
				transaction.commit();
				return SUCCESS;
			}
		} catch (Exception ex){
			actionContext.put("error",ex.getMessage());
			return ERROR;
		}

		actionContext.put("error","请确认参数");
		return ERROR;
	}

	public String selectCourse() throws Exception {
		List<SelectCourse> selectCourseList = session.createSQLQuery("SELECT * FROM SO").addEntity(SelectCourse.class).list();
		ActionContext actionContext = ActionContext.getContext();
		actionContext.put("selectCourseList",selectCourseList);
		return SUCCESS;
	}

	public String updateSelectCourse() throws Exception{
		ActionContext actionContext = ActionContext.getContext();

		Transaction transaction = session.beginTransaction();
		try{
			if(actionContext.getParameters().get("delete_data") != null)
			{
				String[] parameter = ((String[])actionContext.getParameters().get("cb_delete"));
				if(parameter.length == 0){
					actionContext.put("error","请选择目标");
					return ERROR;
				}
				for(String id:parameter){
					session.delete(session.get(SelectCourse.class, id));
				}
				transaction.commit();
				return SUCCESS;
			}

			if(actionContext.getParameters().get("new_data") != null)
			{
				SelectCourse newSelectCourse = new SelectCourse();
				newSelectCourse.setO_id(0);//这句无意义，id会自动分配，但不写还不行
				newSelectCourse.setO_id(Integer.parseInt(((String[])actionContext.getParameters().get("o_id"))[0]));
				newSelectCourse.setS_id(((String[])actionContext.getParameters().get("s_id"))[0]);
				session.save(newSelectCourse);
				transaction.commit();
				return SUCCESS;
			}
		} catch (Exception ex){
			actionContext.put("error",ex.getMessage());
			return ERROR;
		}

		actionContext.put("error","请确认参数");
		return ERROR;
	}
}
