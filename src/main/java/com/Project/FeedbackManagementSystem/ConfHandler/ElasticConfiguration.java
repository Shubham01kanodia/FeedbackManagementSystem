package com.Project.FeedbackManagementSystem.ConfHandler;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ElasticConfiguration {
	public static ElasticColumnMapping student_basic_details;
	public static void loadElasticConfiguration()
	{
		AbstractApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("ElasticConfiguration.xml");
			context.getBean("Index_Names");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			context.close();
		}
	}
	public static ElasticColumnMapping getStudent_basic_details() {
		return student_basic_details;
	}
	public static void setStudent_basic_details(ElasticColumnMapping student_basic_details) {
		ElasticConfiguration.student_basic_details = student_basic_details;
	}
	@Override
	public String toString() {
		return "ElasticConfiguration [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	


}
