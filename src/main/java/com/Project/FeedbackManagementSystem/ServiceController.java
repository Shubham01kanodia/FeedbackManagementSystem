package com.Project.FeedbackManagementSystem;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Project.FeedbackManagementSystem.Handler.StudenDataHandler;


@RestController
public class ServiceController {

	@RequestMapping("/")
	String welcomeMsg()
	{
		return "Welcome to Feedback Management System";
	}
	@RequestMapping("/RegisterStudent")
	//@RequestMapping("/")
	boolean registerStudent(@RequestParam(value="studentFirstName")String studentFirstName, @RequestParam(value="studentMiddleName")String studentMiddleName, 
			@RequestParam(value="studentLastName")String studentLastName, @RequestParam(value="studentId")String studentId, 
			@RequestParam(value="studentCourse")String studentCourse, @RequestParam(value="studentBranch")String studentBranch, 
			@RequestParam(value="studentSemester")String studentSemester, @RequestParam(value="studentCollege")String studentCollege, 
			@RequestParam(value="studentEmailId")String studentEmailId, @RequestParam(value="studentMobileNumber1")String studentMobileNumber1, 
			@RequestParam(value="studentMobileNumber2")String studentMobileNumber2, @RequestParam(value="studentAddressLocation")String studentAddressLocation, 
			@RequestParam(value="studentAddressCity")String studentAddressCity, @RequestParam(value="studentAddressState")String studentAddressState, 
			@RequestParam(value="studentPincode")String studentPincode, @RequestParam(value="studentCountry")String studentCountry)
	{
		boolean status = false;
		Map<String, Object> dataList = new HashMap<>();
		dataList.put("studentFirstName",studentFirstName);
		dataList.put("studentMiddleName",studentMiddleName);
		dataList.put("studentLastName",studentLastName);
		dataList.put("studentId",studentId);
		dataList.put("studentCourse",studentCourse);
		dataList.put("studentBranch",studentBranch);
		dataList.put("studentSemester",studentSemester);
		dataList.put("studentCollege",studentCollege);
		dataList.put("studentEmailId",studentEmailId);
		dataList.put("studentMobileNumber1",studentMobileNumber1);
		dataList.put("studentMobileNumber2",studentMobileNumber2);
		dataList.put("studentAddressLocation",studentAddressLocation);
		dataList.put("studentAddressCity",studentAddressCity);
		dataList.put("studentAddressState",studentAddressState);
		dataList.put("studentPincode",studentPincode);
		dataList.put("studentCountry",studentCountry);
		StudenDataHandler studenDataHandler = new StudenDataHandler();
		status = studenDataHandler.registerStudent(dataList);
		return status;
	}
}
