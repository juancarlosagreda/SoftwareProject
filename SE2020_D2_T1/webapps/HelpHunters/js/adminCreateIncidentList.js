
function CreateIncidentList() {

    //First I need to look up my parent node, which I intentionally left blank
    //Look up the tag in chrome console
    var parentNode = document.getElementById('anchor');

    var content = document.createElement("div");
    content.setAttribute("class","content");
    parentNode.appendChild(content);

    var startList = document.createElement("ul");
    startList.setAttribute("class","dashboard-box-list");
    content.appendChild(startList);

    for(i = 0; i < incidentsData.length; i++){
        //First we set up the divisions
        var listItem2 = document.createElement("li");
        startList.appendChild(listItem2);

        var jobListing = document.createElement("div");
        jobListing.setAttribute("class", "job-listing");
        listItem2.appendChild(jobListing);

        var jobListingDetails = document.createElement("div");
        jobListingDetails.setAttribute("class", "job-listing-details");
        jobListing.appendChild(jobListingDetails);

        var jobListingDescription = document.createElement("div");
        jobListingDescription.setAttribute("class", "job-listing-description");
        jobListingDetails.appendChild(jobListingDescription);

        //Then we create the header and the core of the table box
        var incidence = document.createElement("h2");
        jobListingDescription.appendChild(incidence);

        var incidencenumber = document.createElement("a");
        var number = document.createTextNode("Incidencia: " + incidentsData[i].noticeID);
        incidencenumber.setAttribute("href","adminIncidenceInfo?noticeID=" + incidentsData[i].noticeID);
        incidencenumber.appendChild(number);
        incidence.appendChild(incidencenumber);


        var joblistingtitle = document.createElement("div");
        joblistingtitle.setAttribute("class", "job-listing-title");
        incidencenumber.appendChild(joblistingtitle);

        var stat = document.createElement("h4");
        joblistingtitle.appendChild(stat);

        var sub = document.createElement("i");
        sub.setAttribute("class","icon-material-outline-assignment"+"Subject:");
        sub.innerHTML = incidentsData[i].subject;
        stat.appendChild(sub);


        //Now we do the footer
        var jobListingFooter = document.createElement("div");
        jobListingFooter.setAttribute("class","job-listing-footer");
        jobListingDescription.appendChild(jobListingFooter);

        var uList = document.createElement("ul");
        jobListingFooter.appendChild(uList);

        var item = document.createElement("li");
        uList.appendChild(item);
        var icon = document.createElement("i");
        icon.setAttribute("class","icon-material-outline-date-range");
        icon.innerHTML = incidentsData[i].datecreated;
        item.appendChild(icon);





      }

}


CreateIncidentList();
