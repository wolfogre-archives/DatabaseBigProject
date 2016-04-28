package com.wolfogre.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wolfogre.Information;
import com.wolfogre.domain.Term;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/27.
 */
public class ManagerAction extends ActionSupport {
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

		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();

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
				session.close();
				sessionFactory.close();
				return SUCCESS;
			}
			if(actionContext.getParameters().get("reg_begin") != null && actionContext.getParameters().get("reg_end") != null)
			{
				java.util.Date begin = Information.getDateFormat().parse(((String[])actionContext.getParameters().get("reg_begin"))[0]);
				java.util.Date end = Information.getDateFormat().parse(((String[])actionContext.getParameters().get("reg_end"))[0]);
				term.getD_reg_begin().setTime(begin);
				term.getD_reg_end().setTime(end);
				transaction.commit();
				session.close();
				sessionFactory.close();
				return SUCCESS;
			}
			if(actionContext.getParameters().get("inq_begin") != null && actionContext.getParameters().get("inq_end") != null)
			{
				java.util.Date begin = Information.getDateFormat().parse(((String[])actionContext.getParameters().get("inq_begin"))[0]);
				java.util.Date end = Information.getDateFormat().parse(((String[])actionContext.getParameters().get("inq_end"))[0]);
				term.getD_inq_begin().setTime(begin);
				term.getD_inq_end().setTime(end);
				transaction.commit();
				session.close();
				sessionFactory.close();
				return SUCCESS;
			}
		} catch (ParseException pe){
			actionContext.put("error","日期时间格式有误");
			transaction.rollback();
			session.close();
			sessionFactory.close();
			return ERROR;
		}

		actionContext.put("error","请确认参数");
		return ERROR;
	}
}
