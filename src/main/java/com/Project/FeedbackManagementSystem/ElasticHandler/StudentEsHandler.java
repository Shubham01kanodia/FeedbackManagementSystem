package com.Project.FeedbackManagementSystem.ElasticHandler;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.rest.RestStatus;

import com.Project.FeedbackManagementSystem.FeedbackManagementSystemApplication;
import com.Project.FeedbackManagementSystem.ConfHandler.ElasticColumnMapping;

public class StudentEsHandler {

	public static String EMPTY_STRING = "";
	public static String ZERO_VALUE = "0";
	
	public Boolean insertDataInElastic(ElasticColumnMapping elasticColumnMapping, Map<String, Object> dataList)
	{
		XContentBuilder builder = null;
		try 
		{
			builder = XContentFactory.jsonBuilder();
			builder.startObject();
			Map<String, String> columnsList = elasticColumnMapping.getColumnsList();
			for(Map.Entry<String, String> entry : columnsList.entrySet())
			{
				Object value = null;
				switch (entry.getValue()) {
				case "String":
				{	
					Object inputValue = dataList.get(entry.getKey());
					if(inputValue != null)
					{
						value = inputValue.toString();
					}
					else
					{
						value = StudentEsHandler.EMPTY_STRING;
					}
					builder.field(entry.getKey(), value);
				}
					break;
				
				case "Integer":
					try 
					{
						Object inputValue = dataList.get(entry.getKey());
						if((inputValue != null) && (!inputValue.toString().isEmpty()))
						{
							value = Integer.parseInt(inputValue.toString());
						}
						else
						{
							value = Integer.parseInt(StudentEsHandler.ZERO_VALUE);
						}
						builder.field(entry.getKey(),value);
					}
					catch(ClassCastException ex)
					{
						value = Integer.parseInt(StudentEsHandler.ZERO_VALUE);
						builder.field(entry.getKey(), StudentEsHandler.ZERO_VALUE);
						ex.printStackTrace();
					}
					break;
					
				case "Long":
					try 
					{
						Object inputValue = dataList.get(entry.getKey());
						if((inputValue != null) && (!inputValue.toString().isEmpty()))
						{
							value = Long.parseLong(inputValue.toString());
						}
						else
						{
							value = Long.parseLong(StudentEsHandler.ZERO_VALUE);
						}
						builder.field(entry.getKey(), value);
					}
					catch(ClassCastException ex)
					{
						value = Long.parseLong(StudentEsHandler.ZERO_VALUE);
						builder.field(entry.getKey(), value);
						ex.printStackTrace();
					}
					break;
					
				case "Double":
					try 
					{
						Object inputValue = dataList.get(entry.getKey());
						if((inputValue != null) && (!inputValue.toString().isEmpty()))
						{
							value = Double.parseDouble(inputValue.toString());
						}
						else
						{
							value = Double.parseDouble(StudentEsHandler.ZERO_VALUE);
						}
						builder.field(entry.getKey(), value);
					}
					catch(ClassCastException ex)
					{
						value = Double.parseDouble(StudentEsHandler.ZERO_VALUE);
						builder.field(entry.getKey(), value);
						ex.printStackTrace();
					}
					break;
					
				case "Boolean":
					try 
					{
						Object inputValue = dataList.get(entry.getKey());
						if((inputValue != null) && (!inputValue.toString().isEmpty()))
						{
							value = Boolean.parseBoolean(inputValue.toString());
						}
						else
						{
							value = Boolean.FALSE;
						}
						builder.field(entry.getKey(), value);
					}
					catch(ClassCastException ex)
					{
						value = Boolean.FALSE;
						builder.field(entry.getKey(), value);
						ex.printStackTrace();
					}
					break;
				default:
					break;
				}
			}
			builder.endObject();
			String primaryKey = dataList.get(elasticColumnMapping.getPrimaryField()).toString();
			IndexResponse response = FeedbackManagementSystemApplication.getTransportClient()
					.prepareIndex(elasticColumnMapping.getIndexName(), elasticColumnMapping.getDocumentName()
							, primaryKey)
			        .setSource(builder)
			        .get();
			
			if(response.status() == RestStatus.OK)
			{	
				return true;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
