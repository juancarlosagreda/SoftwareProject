
function createNoticeList() {

  //For AJAX implementation I will include the search bar filter
  //So we will append a new filter
  var filterParent = document.getElementById("filter");
      var filterText = document.createElement("div");
      filterText.setAttribute("class","message-reply");
      filterParent.appendChild(filterText);

        var text = document.createElement("textarea");
        text.setAttribute("id","queryString");
        text.setAttribute("cols","1");
        text.setAttribute("rows","1");
        text.setAttribute("style","height: 48px; width: 500px")
        text.setAttribute("placeholder","  Look up notices");
        filterText.appendChild(text);

        var sendQuery = document.createElement("button");
        sendQuery.setAttribute("id","queryButton");
        sendQuery.setAttribute("class","button light ripple-effect");
        sendQuery.innerHTML = "Search";
        filterText.appendChild(sendQuery);


    //Now I need to look up my parent node, which I intentionally left blank
    //Look up the tag in chrome console
    var parentNode = document.getElementById('anchor');

    var content = document.createElement("div");
    content.setAttribute("class","content");
    parentNode.appendChild(content);

    var startList = document.createElement("ul");
    startList.setAttribute("class","dashboard-box-list");
    content.appendChild(startList);

    for(i = 0; i < noticesData.length; i++){
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
        var incidenceTitle = document.createElement("a");
        incidenceTitle.setAttribute("href","adminNoticeInfo?noticeID=" + noticesData[i].noticeID);
        var incidenceHeader = document.createElement("h2");
        var headerLink = document.createElement("a");
        headerLink.setAttribute("href","adminNoticeInfo?noticeID=" + noticesData[i].noticeID);
        headerLink.innerHTML = noticesData[i].subject;
        jobListingDescription.appendChild(incidenceTitle);
        jobListingDescription.appendChild(incidenceHeader);
        incidenceHeader.appendChild(headerLink);

        var whichClient = document.createElement("h4");
        whichClient.setAttribute("class","job-listing-title");
        whichClient.innerHTML = "Client " + noticesData[i].clientID;
        jobListingDescription.appendChild(whichClient);

        var noticeDescription = document.createElement("h4");
        noticeDescription.setAttribute("class","job-listing-title");
        noticeDescription.innerHTML = "Message: " + noticesData[i].message;
        jobListingDescription.appendChild(noticeDescription);

      //REALLY unorthodox but it is the only way this will work... doesnt recognize null values :(
      if ((noticesData[i].reply.localeCompare("Null") == 0) || (noticesData[i].reply.localeCompare("Nul") == 0) || (noticesData[i].reply.localeCompare("null") == 0)) {
          var noticeReply = document.createElement("h4");
          noticeReply.setAttribute("class","job-listing-title");
          noticeReply.innerHTML = "Unreplied";
          jobListingDescription.appendChild(noticeReply);
      } else {
          var noticeReply = document.createElement("h4");
          noticeReply.setAttribute("class","job-listing-title");
          noticeReply.innerHTML = "Reply: " + noticesData[i].reply;
          jobListingDescription.appendChild(noticeReply);
      }

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
        icon.innerHTML = noticesData[i].datecreated;
        item.appendChild(icon);


        //Now we enable the Buttons
        var visibleButtons = document.createElement("div");
        parentNode.appendChild(visibleButtons);
      }

}


createNoticeList();
