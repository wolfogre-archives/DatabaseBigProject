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
import java.util.Map;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/27.
 */
public class TeacherAction extends ActionSupport {
	private Configuration configuration;
	private ServiceRegistry serviceRegistry;
	private SessionFactory sessionFactory;
	private Session session;

	public TeacherAction(){
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
		Teacher teacher = (Teacher)actionContext.getSession().get( "master");
		List<OpenCourse> openCourseList = session
				.createSQLQuery("SELECT * FROM O WHERE t_id = '" + teacher.getT_id() + "' and o_id in (SELECT o_id FROM O WHERE d_term = '" + Information.getTerm().getD_term() +"')")
				.addEntity(OpenCourse.class).list();
		HashMap<Character, HashMap<String, Object>> selectData = new HashMap<>();
		char ch = 'A';
		for(OpenCourse openCourse : openCourseList){
			HashMap<String, Object> temp = new HashMap<>();
			Course course = (Course)session.get(Course.class, openCourse.getC_id());
			temp.put("开课号", openCourse.getO_id());
			temp.put("课程号", course.getC_id());
			temp.put("课程名", course.getC_name());
			temp.put("学分", course.getC_credit());
			temp.put("上课时间", openCourse.getO_time());
			temp.put("上课地点", openCourse.getO_room());
			selectData.put(ch++, temp);
		}
		actionContext.put("selectData",selectData);
		return SUCCESS;
	}

	public String list() throws Exception{
		ActionContext actionContext = ActionContext.getContext();
		String[] arr = (String[])actionContext.getParameters().get("o_id");
		if(arr == null || arr.length == 0)
			return SUCCESS;
		Integer o_id = Integer.parseInt(arr[0]);
		//TODO:这里应该再次验证参数，确认老师开了这门课
		List<Student> studentList = session.createSQLQuery("SELECT * FROM S WHERE S.s_id in (SELECT SO.s_id FROM SO WHERE SO.o_id =" + o_id + ")").addEntity(Student.class).list();
		actionContext.put("studentList", studentList);

		OpenCourse openCourse = (OpenCourse)session.get(OpenCourse.class, o_id);
		Course course = (Course)session.get(Course.class, openCourse.getC_id());
		actionContext.put("course", course);

		return SUCCESS;
	}

	public String score() throws Exception{
		ActionContext actionContext = ActionContext.getContext();
		String[] arr = (String[])actionContext.getParameters().get("o_id");
		if(arr == null || arr.length == 0)
			return SUCCESS;
		Integer o_id = Integer.parseInt(arr[0]);
		//TODO:这里应该再次验证参数，确认老师开了这门课
		List<SelectCourse> selectCourseList = session.createSQLQuery("SELECT * FROM SO WHERE o_id  = " + o_id).addEntity(SelectCourse.class).list();
		HashMap<Integer, HashMap<String, Object>> scoreData = new HashMap<>();
		Integer index = 0;
		for(SelectCourse selectCourse : selectCourseList){
			HashMap<String, Object> temp = new HashMap<>();
			Student student = (Student)session.get(Student.class, selectCourse.getS_id());
			temp.put("学号", student.getS_id());
			temp.put("姓名", student.getS_name());
			temp.put("平时成绩", selectCourse.getSo_ps_score());
			temp.put("考试成绩", selectCourse.getSo_ks_score());
			scoreData.put(index++, temp);
		}
		actionContext.put("scoreData",scoreData);
		actionContext.put("o_id",o_id);

		OpenCourse openCourse = (OpenCourse)session.get(OpenCourse.class, o_id);
		Course course = (Course)session.get(Course.class, openCourse.getC_id());
		actionContext.put("course", course);
		return SUCCESS;
	}

	public String updateScore() throws Exception{
		ActionContext actionContext = ActionContext.getContext();
		String[] arr = (String[])actionContext.getParameters().get("o_id");
		if(arr == null || arr.length == 0)
			return SUCCESS;
		Integer o_id = Integer.parseInt(arr[0]);
		//TODO:这里应该再次验证参数，确认老师开了这门课

		Map<String, Object> parameters = actionContext.getParameters();
		for(Map.Entry<String, Object> p : parameters.entrySet()){
			if(p.getKey().contains("pscj") && p.getValue() != null && !((String[])p.getValue())[0].isEmpty()){
				String s_id = p.getKey().split("_")[1];
				String[] valueArr = (String[])p.getValue();
				List<SelectCourse> selectCourseList = session.createSQLQuery("SELECT * FROM SO WHERE o_id  = " + o_id + " AND s_id = '" + s_id + "'").addEntity(SelectCourse.class).list();
				Transaction transaction = session.beginTransaction();
				selectCourseList.get(0).setSo_ps_score(Double.parseDouble(valueArr[0]));
				session.update(selectCourseList.get(0));
				transaction.commit();
			}
			if(p.getKey().contains("kscj") && p.getValue() != null && !((String[])p.getValue())[0].isEmpty()){
				String s_id = p.getKey().split("_")[1];
				String[] valueArr = (String[])p.getValue();
				List<SelectCourse> selectCourseList = session.createSQLQuery("SELECT * FROM SO WHERE o_id  = " + o_id + " AND s_id = '" + s_id + "'").addEntity(SelectCourse.class).list();
				Transaction transaction = session.beginTransaction();
				selectCourseList.get(0).setSo_ks_score(Double.parseDouble(valueArr[0]));
				session.update(selectCourseList.get(0));
				transaction.commit();
			}
		}
		String[] temp = new String[1];
		temp[0] = o_id + "";
		actionContext.getParameters().put("o_id",temp);
		return SUCCESS;
	}
}
