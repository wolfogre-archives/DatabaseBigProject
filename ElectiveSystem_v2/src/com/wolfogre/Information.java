package com.wolfogre;

import com.opensymphony.xwork2.ActionContext;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

/**
 * Created by Jason Song(wolfogre.com) on 2016/4/20.
 */
public class Information {
	public static String getNowTerm() throws Exception{
		//DbDao dbDao = new DbDao("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/elective_system_v1","root", "DBlocal");
		//ResultSet resultSet = dbDao.query("select * from D where d_begin <= NOW() and d_end >= NOW()");
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();

		//Transaction transaction = session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select * from D where d_begin <= NOW() and d_end >= NOW()");
		List resultList = sqlQuery.list();
		String result = null;
		if(!resultList.isEmpty()) {
			ActionContext actionContext = ActionContext.getContext();
			String term = resultList.getString("d_term");
			//TODO:这儿需要修复
			String splits[] = term.split("-");
			if (splits[1].equals("C"))
				term = "春季学期";
			if (splits[1].equals("X"))
				term = "夏季学期";
			if (splits[1].equals("Q"))
				term = "秋学期";
			if (splits[1].equals("D"))
				term = "冬季学期";
			result = splits[0] + "年" + term;
		}
		dbDao.closeConnection();
		return result;
	}
}