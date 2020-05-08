function displayMessages() {

	var parentNode = document.getElementById('listgoeshere');
	
	var table = document.createElement("table");
	table.setAttribute("class", "basic-table")
	parentNode.appendChild(table);
	
	var headerrow = document.createElement("tr");
	table.appendChild(headerrow);
	
	var caregiver = document.createElement("th");
	caregiver.innerHTML = "Caregiver";
	headerrow.appendChild(caregiver);

	var subject = document.createElement("th");
	subject.innerHTML = "Subject";
	headerrow.appendChild(subject);
	
	var date = document.createElement("th");
	date.innerHTML = "Date";
	headerrow.appendChild(date);
	
	var viewchat = document.createElement("th");
	viewchat.innerHTML = "";
	headerrow.appendChild(viewchat);
	
	for( var i = 0; i < messageDataItem.length; i++){
		
		var tablerow = document.createElement("tr");
		table.appendChild(tablerow);
		
		var name = document.createElement("td");
		//name.setAttribute("data-label= 'Caregiver Name'");
		name.innerHTML = messageDataItem[i].firstname+ " " +messageDataItem[i].lastname;
		tablerow.appendChild(name);
		
		var subject = document.createElement("td");
		//subject.setAttribute("data-label= 'Subject'");
		subject.innerHTML = messageDataItem[i].subject;
		tablerow.appendChild(subject);
		
		var date = document.createElement("td");
		//date.setAttribute("data-label= 'Date'");
		date.innerHTML = messageDataItem[i].datecreated;
		tablerow.appendChild(date);
		
		var view = document.createElement("td");
		//date.setAttribute("data-label= 'Date'");
		tablerow.appendChild(view);
		
		var seeMessage = document.createElement("a");
		seeMessage.setAttribute("href","displayMessageAle?firstname=" + messageDataItem[i].firstname +"&lastname=" + messageDataItem[i].lastname +"&reply=" +messageDataItem[i].reply +"&subject=" +messageDataItem[i].subject +"&message=" +messageDataItem[i].message+"&datecreated=" +messageDataItem[i].datecreated);
		seeMessage.setAttribute("class","button dark ripple-effect");
		seeMessage.innerHTML = "See Message";
		view.appendChild(seeMessage);
	}
}
displayMessages();	