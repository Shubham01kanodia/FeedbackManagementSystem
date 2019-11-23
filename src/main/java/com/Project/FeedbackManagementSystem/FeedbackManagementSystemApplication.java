package com.Project.FeedbackManagementSystem;

import java.net.InetAddress;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Project.FeedbackManagementSystem.ConfHandler.ElasticConfiguration;



@SpringBootApplication
public class FeedbackManagementSystemApplication {
	
	private static TransportClient client;

	private int elasticSearchPort = 9300;
	 
	public static void main(String[] args) {
		SpringApplication.run(FeedbackManagementSystemApplication.class, args);
	}
	
	@PostConstruct
	public void connectElasticsearch()
	{
		FeedbackManagementSystemApplication.client = this.initializeClient();
		ElasticConfiguration.loadElasticConfiguration();
	}
	
	@PreDestroy
	public void closeElasticsearchConnection()
	{
		this.closeClient(FeedbackManagementSystemApplication.client);
	}
	
	public TransportClient initializeClient()
	{
		TransportClient transportClient = null;
		PreBuiltTransportClient prebuiltTS = null;
		try {
			prebuiltTS = new PreBuiltTransportClient(Settings.EMPTY);
			transportClient = prebuiltTS.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), this.elasticSearchPort));
			//TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transportClient;
	}
	public void closeClient(TransportClient client)
	{
		try {
			client.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static TransportClient getTransportClient()
	{
		return FeedbackManagementSystemApplication.client;
	}

}
