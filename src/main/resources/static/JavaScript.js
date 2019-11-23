function registerStudent()
{
	var name = document.getElementById("name").value;
	var branch = document.getElementById("branch").value;
	var semester = document.getElementById("semester").value;
	var email = document.getElementById("emailId").value;
	console.log("Name is "+name);
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			var str = this.responseTest;
		}
	}
	xhttp.open("GET", "\RegisterStudent?name="+name+"&branch="+branch+"&semester="+semester+"&email="+email, true);
	xhttp.send();
}