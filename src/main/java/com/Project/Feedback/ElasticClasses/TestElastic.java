package com.Project.Feedback.ElasticClasses;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.Project.FeedbackManagementSystem.ConfHandler.ElasticColumnMapping;
import com.Project.FeedbackManagementSystem.ConfHandler.ElasticConfiguration;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;


public class TestElastic {
	public static TransportClient client;
	
	public static void main(String[] args) throws Exception {

		System.out.println("Starting Program");
		try
		{
			/*Client client = new PreBuiltTransportClient(Settings.EMPTY)
			        .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));*/
			
			TestElastic.initializeClient();
			TestElastic testElastic = new TestElastic();
			ElasticConfiguration.loadElasticConfiguration();
			ElasticColumnMapping studentsColumnMapping = ElasticConfiguration.getStudent_basic_details();
			/*String[] searchFields = {"Student_First_Name", "Student_Middle_Name"};
			String[] searchValues = {"Shri", "Nitikesh"};
			testElastic.searchDataInElasticOnMultipleFields(studentsColumnMapping, searchFields , searchValues);
			//testElastic.searchDataInElastic(studentsColumnMapping, "Student_First_Name" , "Shri");
			*/Map<String, Object> dataList = new HashMap<>();
			dataList.put("Student_ID","Stud101");
			dataList.put("Student_College_ID","0830cs");
			dataList.put("Student_First_Name","Shri");
			dataList.put("Student_Last_Name","Kanodia");
			dataList.put("Student_Middle_Name","shubham");
			dataList.put("Student_Mobile_Number_1",new Long(102355));
			dataList.put("Student_Mobile_Number_2", new Long(526352));
			
			dataList.put("Student_ID","Stud102");
			dataList.put("Student_College_ID","0830cs");
			dataList.put("Student_First_Name","Shri");
			dataList.put("Student_Last_Name","Kanodiya");
			dataList.put("Student_Middle_Name","Nitikesh");
			dataList.put("Student_Mobile_Number_1",new Long(1005500));
			dataList.put("Student_Mobile_Number_2", new Long(98989));

			
			testElastic.insertDataInElastic(studentsColumnMapping, dataList);
			/*String indexName = "twitter";
			String documentName = "tweet";
			testElastic.insertValueInElastic(indexName,documentName,"4","Ram", "trying out Elasticsearch", 10002l);
			testElastic.insertValueInElastic(indexName,documentName,"2","Shubham", "trying Elasticsearch", 202);
			//testElastic.deleteValueFromElastic(indexName, documentName, "1");
			//testElastic.deleteValueFromElasticForGivenField(indexName, "user", "Shubham");
			testElastic.updateValueInElasticForGivenField(indexName, documentName, "1", "user", "Ram");
			testElastic.getValueFromElastic(indexName, documentName, "1");
			testElastic.getValueFromElastic(indexName, documentName, "2");*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			TestElastic.client.close();
		}
	}
	public void searchDataInElasticOnMultipleFields(ElasticColumnMapping elasticColumnMapping, String[] searchFields, String[] searchValues)
	{
		try {
			BoolQueryBuilder boolQuery = new BoolQueryBuilder();
			for(int loop = 0; loop<searchFields.length; loop++)
			{
				boolQuery.must(QueryBuilders.matchQuery(searchFields[loop], searchValues[loop]));
			}
			SearchResponse response = client.prepareSearch(elasticColumnMapping.getIndexName())
			        .setTypes(elasticColumnMapping.getDocumentName())
			        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
			        .setQuery(boolQuery)                // Query
			        .get();
			SearchHit[] results = response.getHits().getHits();
	        for(SearchHit hit : results){

	            String sourceAsString = hit.getSourceAsString();
	            ObjectMapper objectMapper = new ObjectMapper();
	            Map<String, Object> dataMap = new HashMap<String, Object>();
	            dataMap = objectMapper.readValue(sourceAsString, new TypeReference<Map<String, String>>(){});
	            System.out.println("Searched values are "+dataMap);
	        }
		}
		catch(Exception exception)
		{
			
		}
	}
	public void searchDataInElastic(ElasticColumnMapping elasticColumnMapping, String searchField, String searchValue)
	{
		try {
			SearchResponse response = client.prepareSearch(elasticColumnMapping.getIndexName())
			        .setTypes(elasticColumnMapping.getDocumentName())
			        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
			        .setQuery(QueryBuilders.matchQuery(searchField, searchValue))                 // Query
			        //.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
			        //.setFrom(0).setSize(60).setExplain(true)
			        .get();
			
			SearchHit[] results = response.getHits().getHits();
	        for(SearchHit hit : results){

	            String sourceAsString = hit.getSourceAsString();
	            ObjectMapper objectMapper = new ObjectMapper();
	            Map<String, Object> dataMap = new HashMap<String, Object>();
	            dataMap = objectMapper.readValue(sourceAsString, new TypeReference<Map<String, String>>(){});
	            System.out.println("Searched values are "+dataMap);
	        }
		}
		catch(Exception exp)
		{
			
		}
	}
	public void insertDataInElastic(ElasticColumnMapping elasticColumnMapping, Map<String, Object> dataList)
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
					value = dataList.get(entry.getKey()).toString();
					builder.field(entry.getKey(), value);
					break;
				
				case "Integer":
					try 
					{
						value = (Integer)dataList.get(entry.getKey());
						builder.field(entry.getKey(), value);
					}
					catch(ClassCastException ex)
					{
						ex.printStackTrace();
					}
					break;
					
				case "Long":
					try 
					{
						value = (Long)dataList.get(entry.getKey());
						builder.field(entry.getKey(), value);
					}
					catch(ClassCastException ex)
					{
						ex.printStackTrace();
					}
					break;
					
				case "Double":
					try 
					{
						value = (Double)dataList.get(entry.getKey());
						builder.field(entry.getKey(), value);
					}
					catch(ClassCastException ex)
					{
						ex.printStackTrace();
					}
					break;
					
				case "Boolean":
					try 
					{
						value = (Boolean)dataList.get(entry.getKey());
						builder.field(entry.getKey(), value);
					}
					catch(ClassCastException ex)
					{
						ex.printStackTrace();
					}
					break;
				default:
					break;
				}
			}
			builder.endObject();
			IndexResponse response = TestElastic.client.prepareIndex(elasticColumnMapping.getIndexName(), elasticColumnMapping.getDocumentName(), UUID.randomUUID().toString())
			        .setSource(builder)
			        .get();
			System.out.println(response);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void initializeClient()
	{
		try {
			TestElastic.client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
			//TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertValueInElastic(String indexName, String documentName, String idValue,String user, String message, long longValue)
	{
		XContentBuilder builder = null;
		try {
			/*builder = jsonBuilder().startObject()
				        .field("user", user)
				        .field("message", message)
				        .field("long", longValue)
				    .endObject();*/
			builder = XContentFactory.jsonBuilder();
			builder.startObject();
			builder.field("user", user);
			builder.field("message", message);
			builder.field("long", longValue);
			builder.endObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		IndexResponse response = TestElastic.client.prepareIndex(indexName, documentName, idValue)
		        .setSource(builder)
		        .get();
		System.out.println("Insertion : "+response.status());
	}
	public void deleteValueFromElastic(String indexName, String documentName, String idValue)
	{
		DeleteResponse response = TestElastic.client.prepareDelete(indexName, documentName, idValue).get();
		System.out.println("Deletion : "+response.status());
	}
	public void getValueFromElastic(String indexName, String documentName, String idValue)
	{
		GetResponse response = TestElastic.client.prepareGet(indexName, documentName, idValue).get();
		System.out.println("Get : "+response.getSourceAsString());
	}
	public void deleteValueFromElasticForGivenField(String indexName,String field, String value)
	{
		BulkByScrollResponse response =
				  DeleteByQueryAction.INSTANCE.newRequestBuilder(TestElastic.client)
				    .filter(QueryBuilders.matchQuery(field, value)) 
				    .source(indexName)                                  
				    .get();                                            
		System.out.println("No. of Records deleted : "+response.getDeleted());
	}
	public void updateValueInElasticForGivenField(String indexName, String documentName, String idValue,String field, String value)
	{
		try {
			UpdateResponse updateResponse = TestElastic.client.prepareUpdate(indexName, documentName, idValue)
			        .setDoc(jsonBuilder()               
			                .startObject()
			                    .field(field, value)
			                .endObject())
			            .get();
			System.out.println("Updation : "+ updateResponse.status());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
