package com.wolfogre.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wolfogre.Information;
import com.wolfogre.domain.SelectCourse;
import com.wolfogre.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

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
		Student student = (Student)session.get(Student.class, "master");
		List<SelectCourse> selectCourseList = session
				.createSQLQuery("SELECT * FROM SO WHERE s_id = '" + student.getS_id() + "' and o_id in (SELECT o_id FROM O WHERE d_term = '" + Information.getTerm().getD_term() +"')")
				.addEntity(SelectCourse.class).list();
		actionContext.put("selectCourseList",selectCourseList);
		//TODO;这里有空引用异常
		return SUCCESS;
	}
}
