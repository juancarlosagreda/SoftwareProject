var searchButton = document.getElementById("queryButton");
//var mySearch = document.getElementById("queryString");

searchButton.onclick = function(){
  var mySearch = document.getElementById("queryString").value;
  createNewList(mySearch);
}

function createNoticeList(noticeData) {

  //Now I need to look up my parent node, which I intentionally left blank
  //Look up the tag in chrome console
  var parentNode = document.getElementById('anchor');

  //Now, because I deleted all of the children, I have to put back up the first row manually
  var headline = document.createElement("div");
  headline.setAttribute("class","headline");
  parentNode.appendChild(headline);

    var headerTitle = document.createElement("h3");
    var headerIcon = document.createElement("i");
    headerIcon.setAttribute("class","icon-material-outline-business-center");
    headerTitle.appendChild(headerIcon);
    headerTitle.innerHTML = " Number of results: " + noticeData.length;
    headline.appendChild(headerTitle);

  var content = document.createElement("div");
  content.setAttribute("class","content");
  parentNode.appendChild(content);

  var startList = document.createElement("ul");
  startList.setAttribute("class","dashboard-box-list");
  content.appendChild(startList);

  for(i = 0; i < noticeData.length; i++){
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
      incidenceTitle.setAttribute("href","adminNoticeInfo?noticeID=" + noticeData[i].noticeID);
      var incidenceHeader = document.createElement("h2");
      var headerLink = document.createElement("a");
      headerLink.setAttribute("href","adminNoticeInfo?noticeID=" + noticeData[i].noticeID);
      headerLink.innerHTML = noticeData[i].subject;
      jobListingDescription.appendChild(incidenceTitle);
      jobListingDescription.appendChild(incidenceHeader);
      incidenceHeader.appendChild(headerLink);

      var whichClient = document.createElement("h4");
      whichClient.setAttribute("class","job-listing-title");
      whichClient.innerHTML = "Client " + noticeData[i].clientID;
      jobListingDescription.appendChild(whichClient);

      var noticeDescription = document.createElement("h4");
      noticeDescription.setAttribute("class","job-listing-title");
      noticeDescription.innerHTML = "Message: " + noticeData[i].message;
      jobListingDescription.appendChild(noticeDescription);

      //REALLY unorthodox but it is the only way this will work... doesnt recognize null values :(
      if ((noticeData[i].reply.localeCompare("Null") == 0) || (noticeData[i].reply.localeCompare("Nul") == 0) || (noticeData[i].reply.localeCompare("null") == 0)) {
          var noticeReply = document.createElement("h4");
          noticeReply.setAttribute("class","job-listing-title");
          noticeReply.innerHTML = "Unreplied";
          jobListingDescription.appendChild(noticeReply);
      } else {
          var noticeReply = document.createElement("h4");
          noticeReply.setAttribute("class","job-listing-title");
          noticeReply.innerHTML = "Reply: " + noticeData[i].reply;
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
      icon.innerHTML = noticeData[i].datecreated;
      item.appendChild(icon);


      //Now we enable the Buttons
      var visibleButtons = document.createElement("div");
      parentNode.appendChild(visibleButtons);
    }

}

function createNewList(q){
  var request = new XMLHttpRequest();
  request.open('GET', 'adminNoticeListAjax?q=' + q, true); //Esto significa que necesito un request handler EN MI SERVLET

  request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
        noticeData = JSON.parse(request.responseText);
        console.log(noticeData);
        //Aqui kill child nodes
        var parentNode = document.getElementById("anchor");
        while(parentNode.firstChild){
          parentNode.removeChild(parentNode.firstChild);
        }

        // Basically paste mi js que  crea la tabla
        // Para que se vea mas lindo hago una function :)
        createNoticeList(noticeData);

      }
    };
    request.send();
  }


//Event handler
