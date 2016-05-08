package com.wolfogre.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wolfogre.Information;
import com.wolfogre.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/27.
 */
public class StudentAction extends ActionSupport {
	private Configuration configuration;
	private ServiceRegistry serviceRegistry;
	private SessionFactory sessionFactory;
	private Session session;

	public StudentAction(){
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

	public String timetable()  throws Exception{
		ActionContext actionContext = ActionContext.getContext();
		Student student = (Student)actionContext.getSession().get( "master");
		List<SelectCourse> selectCourseList = session
				.createSQLQuery("SELECT * FROM SO WHERE s_id = '" + student.getS_id() + "' and o_id in (SELECT o_id FROM O WHERE d_term = '" + Information.getTerm().getD_term() +"')")
				.addEntity(SelectCourse.class).list();
		HashMap<Character, HashMap<String, Object>> selectData = new HashMap<>();
		char ch = 'A';
		for(SelectCourse selectCourse : selectCourseList){
			HashMap<String, Object> temp = new HashMap<>();
			OpenCourse openCourse = (OpenCourse)session.get(OpenCourse.class, selectCourse.getO_id());
			Course course = (Course)session.get(Course.class, openCourse.getC_id());
			Teacher teacher = (Teacher)session.get(Teacher.class, openCourse.getT_id());
			temp.put("课程号", course.getC_id());
			temp.put("课程名", course.getC_name());
			temp.put("教师号", teacher.getT_id());
			temp.put("教师名", teacher.getT_name());
			temp.put("学分", course.getC_credit());
			temp.put("上课时间", openCourse.getO_time());
			temp.put("上课地点", openCourse.getO_room());
			selectData.put(ch++, temp);
		}
		actionContext.put("selectData",selectData);
		return SUCCESS;
	}

	public String score() throws Exception{
		ActionContext actionContext = ActionContext.getContext();
		Student student = (Student)actionContext.getSession().get( "master");
		List<SelectCourse> selectCourseList = session
				.createSQLQuery("SELECT * FROM SO WHERE s_id = '" + student.getS_id() + "' and o_id in (SELECT o_id FROM O WHERE d_term = '" + Information.getTerm().getD_term() +"')")
				.addEntity(SelectCourse.class).list();
		HashMap<String, HashMap<String, Object>> scoreData = new HashMap<>();
		for(SelectCourse selectCourse : selectCourseList){
			HashMap<String, Object> temp = new HashMap<>();
			OpenCourse openCourse = (OpenCourse)session.get(OpenCourse.class, selectCourse.getO_id());
			Course course = (Course)session.get(Course.class, openCourse.getC_id());
			Teacher teacher = (Teacher)session.get(Teacher.class, openCourse.getT_id());
			temp.put("课程号", course.getC_id());
			temp.put("课程名", course.getC_name());
			temp.put("教师号", teacher.getT_id());
			temp.put("教师名", teacher.getT_name());
			if(selectCourse.getSo_ks_score() == null || selectCourse.getSo_ps_score() == null){
				temp.put("成绩", "尚未登分");
				temp.put("绩点", "尚未登分");
			} else {
				temp.put("成绩", selectCourse.getSo_ks_score() * 0.5 + selectCourse.getSo_ps_score() * 0.5);
				temp.put("绩点", Information.getGPA(selectCourse.getSo_ks_score() * 0.5 + selectCourse.getSo_ps_score() * 0.5));
			}
			temp.put("学分", course.getC_credit());
			scoreData.put(course.getC_id(), temp);
		}
		actionContext.put("scoreData",scoreData);
		return SUCCESS;
	}

	public String course() throws Exception{
		ActionContext actionContext = ActionContext.getContext();
		Student student = (Student)actionContext.getSession().get("master");

		List<OpenCourse> openCourseList = session.createSQLQuery("SELECT * FROM O WHERE NOT EXISTS ( SELECT * FROM SO WHERE SO.s_id = '" + student.getS_id() + "' AND SO.o_id = O.o_id AND  d_term = '" + Information.getTerm().getD_term() + "')").addEntity(OpenCourse.class).list();
		HashMap<Integer, HashMap<String, Object>> 可选课程 = new HashMap();
		for (OpenCourse openCourse : openCourseList)
		{
			HashMap<String, Object> temp = new HashMap();
			Course course = (Course)session.get(Course.class, openCourse.getC_id());
			Teacher teacher = (Teacher)session.get(Teacher.class, openCourse.getT_id());
			temp.put("课程号", course.getC_id());
			temp.put("课程名", course.getC_name());
			temp.put("教师号", teacher.getT_id());
			temp.put("教师名", teacher.getT_name());
			temp.put("学分", course.getC_credit());
			temp.put("上课时间", openCourse.getO_time());
			temp.put("上课地点", openCourse.getO_room());
			int num = session.createSQLQuery("SELECT * FROM SO WHERE o_id = " + openCourse.getO_id()).list().size();
			temp.put("选课人数", num + "/" + openCourse.getO_cap());
			可选课程.put(openCourse.getO_id(), temp);
		}
		actionContext.put("可选课程", 可选课程);

		openCourseList = session.createSQLQuery("SELECT * FROM O WHERE EXISTS ( SELECT * FROM SO WHERE SO.s_id = '" + student.getS_id() + "' AND SO.o_id = O.o_id AND  d_term = '" + Information.getTerm().getD_term() + "')").addEntity(OpenCourse.class).list();
		HashMap<Integer, HashMap<String, Object>> 已选课程 = new HashMap();
		for (OpenCourse openCourse : openCourseList)
		{
			HashMap<String, Object> temp = new HashMap();
			Course course = (Course)session.get(Course.class, openCourse.getC_id());
			Teacher teacher = (Teacher)session.get(Teacher.class, openCourse.getT_id());
			temp.put("课程号", course.getC_id());
			temp.put("课程名", course.getC_name());
			temp.put("教师号", teacher.getT_id());
			temp.put("教师名", teacher.getT_name());
			temp.put("学分", course.getC_credit());
			temp.put("上课时间", openCourse.getO_time());
			temp.put("上课地点", openCourse.getO_room());
			int num = session.createSQLQuery("SELECT * FROM SO WHERE o_id = " + openCourse.getO_id()).list().size();
			temp.put("选课人数", num + "/" + openCourse.getO_cap());
			已选课程.put(openCourse.getO_id(), temp);
		}
		actionContext.put("已选课程", 已选课程);

		return SUCCESS;
	}

	public String updateCourse(){
		ActionContext actionContext = ActionContext.getContext();
		Student student = ((Student)actionContext.getSession().get("master"));
		try{
			if(actionContext.getParameters().get("o_id") != null)
			{
				String[] parameter = ((String[])actionContext.getParameters().get("o_id"));
				int oId = Integer.parseInt(parameter[0]);

				if(session.get(OpenCourse.class, oId) == null){
					actionContext.put("error","课程不存在");
					return ERROR;
				}

				OpenCourse openCourse = (OpenCourse)session.get(OpenCourse.class, oId);

				if(openCourse == null){
					actionContext.put("error","课程不存在");
					return ERROR;
				}

				if(session.createQuery("SELECT * FROM SO WHERE s_id = '" + student.getS_id() + "' and o_id = ''" + oId + "''" ).list().size() != 0) {
					actionContext.put("error","已选该课程");
					//TODO:这里有Bug,同一门课不同老师开，可以重复选
					return ERROR;
				}


				int num = this.session.createSQLQuery("SELECT * FROM SO WHERE o_id = " + openCourse.getO_id()).list().size();
				if(num >= openCourse.getO_cap()){
					actionContext.put("error","选课人数已满");
					return ERROR;
				}

				List<SelectCourse> selectCourseList = session
						.createSQLQuery("SELECT * FROM SO WHERE s_id = '" + student.getS_id() + "' and o_id in (SELECT o_id FROM O WHERE d_term = '" + Information.getTerm().getD_term() +"')")
						.addEntity(SelectCourse.class).list();
				Information.initTimeTable();
				for(SelectCourse selectCourse : selectCourseList){
					String courseTime = ((OpenCourse)session.get(OpenCourse.class, selectCourse.getO_id())).getO_time();
					Information.getCourseTimeString('X',courseTime);
				}

				if(Information.ifTimeTableConflict(openCourse.getO_time())){
					actionContext.put("error","课时冲突");
					return ERROR;
				}

				Transaction transaction = session.beginTransaction();
				SelectCourse newSelectCourse = new SelectCourse();
				newSelectCourse.setO_id(oId);
				newSelectCourse.setS_id(student.getS_id());
				session.save(newSelectCourse);
				transaction.commit();
				return SUCCESS;
			}
		} catch (Exception ex){
			actionContext.put("error",ex.getMessage());
			return ERROR;
		}
		//TODO：退课逻辑
		actionContext.put("error","请确认参数");
		return ERROR;
	}
}
