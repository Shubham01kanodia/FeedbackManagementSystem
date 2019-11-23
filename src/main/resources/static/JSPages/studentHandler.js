function registerStudent()
{
	var studentFirstName = document.getElementById("studentFirstName").value;
	var studentMiddleName = document.getElementById("studentMiddleName").value;
	var studentLastName = document.getElementById("studentLastName").value;
	var studentId = document.getElementById("studentId").value;
	var studentCourse = document.getElementById("studentCourse").value;
	var studentBranch = document.getElementById("studentBranch").value;
	var studentSemester = document.getElementById("studentSemester").value;
	var studentCollege = document.getElementById("studentCollege").value;
	var studentEmailId = document.getElementById("studentEmailId").value;
	var studentMobileNumber1 = document.getElementById("studentMobileNumber1").value;
	var studentMobileNumber2 = document.getElementById("studentMobileNumber2").value;
	var studentAddressLocation = document.getElementById("studentAddressLocation").value;
	var studentAddressCity = document.getElementById("studentAddressCity").value;
	var studentAddressState = document.getElementById("studentAddressState").value;
	var studentPincode = document.getElementById("studentPincode").value;
	var studentCountry = document.getElementById("studentCountry").value;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			var status = this.responseTest;
			if(status == "true")
			{
				//Send Success
			}
			else
			{
				//Send Failure
			}
		}
	}
	xhttp.open("GET", "\RegisterStudent?studentFirstName="+studentFirstName+"&studentMiddleName="+studentMiddleName+"&studentLastName="+studentLastName+"&studentId="+studentId+"&studentCourse="+studentCourse+"&studentBranch="+studentBranch+"&studentSemester="+studentSemester+"&studentCollege="+studentCollege+"&studentEmailId="+studentEmailId+"&studentMobileNumber1="+studentMobileNumber1+"&studentMobileNumber2="+studentMobileNumber2+"&studentAddressLocation="+studentAddressLocation+"&studentAddressCity="+studentAddressCity+"&studentAddressState="+studentAddressState+"&studentPincode="+studentPincode+"&studentCountry="+studentCountry, true);
	xhttp.send();
	document.getElementById("register_student_div").hidden = true;
	document.getElementById("login_student_div").hidden = false;
}

function loginStudent()
{
	
}

function registerStudentShow()
{
	document.getElementById("login_student_div").hidden = true;
	document.getElementById("register_student_div").hidden = false;
}
function loginStudentShow()
{
	document.getElementById("home_page").hidden = true;
	document.getElementById("login_student_div").hidden = false;
}