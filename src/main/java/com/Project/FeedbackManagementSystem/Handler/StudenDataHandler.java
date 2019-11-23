package com.Project.FeedbackManagementSystem.Handler;

import java.util.Map;


import com.Project.FeedbackManagementSystem.ConfHandler.ElasticColumnMapping;
import com.Project.FeedbackManagementSystem.ConfHandler.ElasticConfiguration;
import com.Project.FeedbackManagementSystem.ElasticHandler.StudentEsHandler;

public class StudenDataHandler {

	public boolean registerStudent(Map<String, Object> dataList)
	{
		try {
			//StudentEsHandler.initializeClient();
			StudentEsHandler studentEsHandler = new StudentEsHandler();
			//ElasticConfiguration.loadElasticConfiguration();
			ElasticColumnMapping studentsColumnMapping = ElasticConfiguration.getStudent_basic_details();
			boolean status = studentEsHandler.insertDataInElastic(studentsColumnMapping, dataList);
			return status;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			//StudentEsHandler.client.close();
		}
		return false;
	}
}
