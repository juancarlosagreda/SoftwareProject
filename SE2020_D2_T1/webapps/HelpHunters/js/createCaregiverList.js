
function createCaregiverList() {

    //First I need to look up my parent node, which I intentionally left blank
    //Look up the tag in chrome console
    var parentNode = document.getElementById('anchor');

    var content = document.createElement("div");
    content.setAttribute("class","content");
    parentNode.appendChild(content);

    var startList = document.createElement("ul");
    startList.setAttribute("class","dashboard-box-list");
    content.appendChild(startList);

    for(i = 0; i < caregiversData.length; i++){
        //First we set up the divisions
        var listItem = document.createElement("li");
        startList.appendChild(listItem);

        var jobListing = document.createElement("div");
        jobListing.setAttribute("class", "job-listing");
        listItem.appendChild(jobListing);

        var jobListingDetails = document.createElement("div");
        jobListingDetails.setAttribute("class", "job-listing-details");
        jobListing.appendChild(jobListingDetails);

        var jobListingDescription = document.createElement("div");
        jobListingDescription.setAttribute("class", "job-listing-description");
        jobListingDetails.appendChild(jobListingDescription);

        //Then we create the header and the core of the table box
        var header = document.createElement("h3");
        jobListingDescription.appendChild(header);

        var caregiverName = document.createElement("a");
        var name = document.createTextNode(caregiversData[i].firstname + " " + caregiversData[i].lastname);
        caregiverName.setAttribute("href","adminCaregiverInfo?caregiverID=" + caregiversData[i].caregiverID);
        caregiverName.appendChild(name);
        header.appendChild(caregiverName);
        var statusButton = document.createElement("span");
        statusButton.setAttribute("class","dashboard-status-button "+ caregiversData[i].color+"");
        statusButton.innerHTML = caregiversData[i].available;
        caregiverName.appendChild(statusButton);

        //Now we do the footer
        var jobListingFooter = document.createElement("div");
        jobListingFooter.setAttribute("class","job-listing-footer");
        jobListingDescription.appendChild(jobListingFooter);

        var uList = document.createElement("ul");
        jobListingFooter.appendChild(uList);

        var item = document.createElement("li");
        uList.appendChild(item);
        var icon = document.createElement("i");
        icon.setAttribute("class","icon-feather-at-sign");
        icon.innerHTML = caregiversData[i].email;
        item.appendChild(icon);

        var item2 = document.createElement("li");
        uList.appendChild(item2);
        var dollar = document.createElement("i");
        dollar.setAttribute("class","icon-feather-dollar-sign");
        dollar.innerHTML = caregiversData[i].hourlyrate;
        item2.appendChild(dollar);

        var link = document.createElement("a");
        //In this link we want to include AJAX to remove this caregiver.
        link.setAttribute("href","adminClientInfo?caregiverID=" + caregiversData[i].caregiverID);
        link.setAttribute("class","button gray ripple-effect ico");
        link.setAttribute("title","Remove caregiver");
        link.setAttribute("data-tippy-placement","top");
        uList.appendChild(link);
        var trash = document.createElement("i");
        trash.setAttribute("class","icon-feather-trash-2");
        link.appendChild(trash);

        //Now we enable the Buttons
        var visibleButtons = document.createElement("div");
        trash.appendChild(visibleButtons);
      }

}


createClientList();
