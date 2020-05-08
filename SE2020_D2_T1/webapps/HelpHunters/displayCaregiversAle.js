function displayCaregivers() {

	var parentNode = document.getElementById('listgoeshere');

	var content = document.createElement("div");
    content.setAttribute("class","content");
    parentNode.appendChild(content);

	//var ultralist = document.createElement("ul");
    //content.appendChild(ultralist);

		  for( var i = 0; i < caregiverDataItem.length; i++){

	//var listitem = document.createElement("li");
	//ultralist.appendChild(listitem);

	var jobListing = document.createElement("div");
    jobListing.setAttribute("class", "job-listing");
    content.appendChild(jobListing);


	var jobListingDetails = document.createElement("div");
    jobListingDetails.setAttribute("class", "job-listing-details");
    jobListing.appendChild(jobListingDetails);
	
	var jobListingCompanyLogo = document.createElement("div");
    jobListingCompanyLogo.setAttribute("class", "job-listing-company-logo");
    jobListingDetails.appendChild(jobListingCompanyLogo);
	
	var caregiverImage = document.createElement("img");
    caregiverImage.setAttribute("src", "images/random.png");
    jobListingCompanyLogo.appendChild(caregiverImage);

	var jobListingDescription = document.createElement("div");
    jobListingDescription.setAttribute("class", "job-listing-description");
    jobListingDetails.appendChild(jobListingDescription);

	var header = document.createElement("h3");
	header.setAttribute("class", "job-listing-title");
	header.innerHTML = caregiverDataItem[i].firstname + " " + caregiverDataItem[i].lastname;
    jobListingDescription.appendChild(header);

	var jobListingFooter = document.createElement("div");
    jobListingFooter.setAttribute("class","job-listing-footer");
    jobListingDescription.appendChild(jobListingFooter);

	//var ul = document.createElement("ul");
    //jobListingFooter.appendChild(ul);

	//var li = document.createElement("li");
    //ul.appendChild(li);

	//var i = document.createElement("i");
	//i.setAttribute("class","icon-material-outline-location-on");
	//i.innerHTML = caregiverDataItem[i].province;
	//jobListingFooter.appendChild(i);

	var send = document.createElement("a");
	send.setAttribute("href","caregiverProfileAle?firstname=" + caregiverDataItem[i].firstname +"&lastname=" + caregiverDataItem[i].lastname +"&caregiverID=" +caregiverDataItem[i].caregiverID +"&hourlyrate=" +caregiverDataItem[i].hourlyrate +"&description=" +caregiverDataItem[i].description);
	send.setAttribute("class","button ripple-effect");
	send.innerHTML = "View Profile";
	jobListingDetails.appendChild(send);


		}
}

displayCaregivers();
