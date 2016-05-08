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
import java.util.Calendar;
import java.util.Date;
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
		return "Copy Right &copy; 上海大学 宋建鑫 13121602";
	}

	public static SimpleDateFormat getDateFormat(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	private static char[][] timeTable;

	public static void initTimeTable(){
		timeTable = new char[13][7];
	}

	public static String getCourseTimeString(char index, String courseTime){
		String split1[] = courseTime.split("\\$");
		String result  = "";
		for(String s: split1){
			String split2[] = s.split("#");
			int day = Integer.parseInt(split2[0]);
			int begin = Integer.parseInt(split2[1]);
			int end = Integer.parseInt(split2[2]);
			switch (day){
				case 1:result += "星期一";break;
				case 2:result += "星期二";break;
				case 3:result += "星期三";break;
				case 4:result += "星期四";break;
				case 5:result += "星期五";break;
				case 6:result += "星期六";break;
				case 7:result += "星期日";break;
			}
			result += begin + "-" + end;
			result += " ";
			for(int i = begin - 1; i <= end - 1; ++i)
				timeTable[i][day - 1] = index;
		}
		return result;
	}

	public static String getCourseTimeString(String courseTime){
		String split1[] = courseTime.split("\\$");
		String result  = "";
		for(String s: split1){
			String split2[] = s.split("#");
			int day = Integer.parseInt(split2[0]);
			int begin = Integer.parseInt(split2[1]);
			int end = Integer.parseInt(split2[2]);
			switch (day){
				case 1:result += "星期一";break;
				case 2:result += "星期二";break;
				case 3:result += "星期三";break;
				case 4:result += "星期四";break;
				case 5:result += "星期五";break;
				case 6:result += "星期六";break;
				case 7:result += "星期日";break;
			}
			result += begin + "-" + end;
			result += " ";
		}
		return result;
	}

	public static char[][] getTimeTable(){
		return timeTable;
	}

	public static boolean ifTimeTableConflict(String courseTime){
		String split1[] = courseTime.split("\\$");
		for(String s: split1){
			String split2[] = s.split("#");
			int day = Integer.parseInt(split2[0]);
			int begin = Integer.parseInt(split2[1]);
			int end = Integer.parseInt(split2[2]);
			for(int i = begin - 1; i <= end - 1; ++i)
				if(timeTable[i][day - 1] != Character.MIN_VALUE)
					return true;
		}
		return false;
	}

	public static Date getSelectBeginTime() throws Exception {
		return getTerm().getD_sel_begin().getTime();
	}

	public static Date getSelectEndTime()  throws Exception {
		return getTerm().getD_sel_end().getTime();
	}

	public static boolean isSelectTime()  throws Exception{
		Date now = new Date();
		return getSelectBeginTime().before(now) && getSelectEndTime().after(now);
	}

	public static Date getRegisterBeginTime() throws Exception {
		return getTerm().getD_reg_begin().getTime();
	}

	public static Date getRegisterEndTime()  throws Exception {
		return getTerm().getD_reg_end().getTime();
	}

	public static boolean isInRegisterTime()  throws Exception{
		Date now = new Date();
		return getRegisterBeginTime().before(now) && getRegisterEndTime().after(now);
	}

	public static Date getInquireBeginTime() throws Exception {
		return getTerm().getD_inq_begin().getTime();
	}

	public static Date getInquireEndTime()  throws Exception {
		return getTerm().getD_inq_end().getTime();
	}

	public static boolean isInInquireTime()  throws Exception{
		Date now = new Date();
		return getInquireBeginTime().before(now) && getInquireEndTime().after(now);
	}

	public static double getGPA(double score){
/*
成绩与绩点的关系
（1）理论课程
百分制成绩				成绩等级		绩点
90～100			     		A			4.0
85～89.9					A—			3.7
82～84.9					B＋			3.3
78～81.9					B			3.0
75～77.9					B—			2.7
72～74.9					C＋			2.3
68～71.9					C			2.0
66～67.9					C—			1.7
64～65.9					D			1.5
60～63.9					D—			1.0
＜60						F			  0
 */
		if (score >= 90)
			return 4.0;
		if (score >= 85)
			return 3.7;
		if (score >= 82)
			return 3.3;
		if (score >= 78)
			return 3.0;
		if (score >= 75)
			return 2.7;
		if (score >= 72)
			return 2.3;
		if (score >= 68)
			return 2.0;
		if (score >= 66)
			return 1.7;
		if (score >= 64)
			return 1.5;
		if (score >= 60)
			return 1.0;
		return 0;
	}
}