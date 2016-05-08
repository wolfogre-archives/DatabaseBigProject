package com.wolfogre.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wolfogre.Information;
import com.wolfogre.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.HashMap;
import java.util.List;

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
}
