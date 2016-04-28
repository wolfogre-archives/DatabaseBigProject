package com.wolfogre;

import com.opensymphony.xwork2.ActionContext;
import com.wolfogre.domain.Term;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/20.
 */
public class Information {
	public static Term getTerm() throws Exception{
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();

		SQLQuery sqlQuery = session.createSQLQuery("select * from D where d_begin <= NOW() and d_end >= NOW()").addEntity(Term.class);
		List<Term> resultList = (List<Term>)sqlQuery.list();
		session.close();
		sessionFactory.close();

		if(resultList.isEmpty()) {
			return null;
		}

		return resultList.get(0);

	}
	public static String getTermName() throws Exception{
		Term term = getTerm();
		if(term == null){
			return "未定义";
		}
		String termName = term.getD_term();
		String splits[] = termName.split("-");
		if (splits[1].equals("C"))
			termName = "春季学期";
		if (splits[1].equals("X"))
			termName = "夏季学期";
		if (splits[1].equals("Q"))
			termName = "秋学期";
		if (splits[1].equals("D"))
			termName = "冬季学期";
		return splits[0] + "年" + termName;
	}

	public static String getCopyRight(){
		return "Copy Right @ 上海大学 宋建鑫 13121602";
	}

	public static SimpleDateFormat getDateFormat(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
}