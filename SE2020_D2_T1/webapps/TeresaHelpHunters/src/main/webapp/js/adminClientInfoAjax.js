var trash = document.getElementById("trash");

trash.onclick = function(){
  console.log("The client I will delete is client " + clientData[0].clientID);

  //Aqui kill child nodes
  var parentNode = document.getElementById("anchor");
  while(parentNode.firstChild){
    parentNode.removeChild(parentNode.firstChild);
  }
  var notices = document.getElementById("deleteButton");
  while(notices.firstChild){
    notices.removeChild(notices.firstChild);
  }
  //Now we create the system message that we have deleted the client :)
  var parentNode2 = document.getElementById("anchor2");

  var newBox = document.createElement("div");
  newBox.setAttribute("class","col-xl-12");
  parentNode2.appendChild(newBox);

    var boxDiv = document.createElement("div");
    boxDiv.setAttribute("class","dashboard-box");
    newBox.appendChild(boxDiv);

      var newHeadline = document.createElement("div");
      newHeadline.setAttribute("class","headline");
      boxDiv.appendChild(newHeadline);

        var headlineHeader = document.createElement("h3");
        var headlineIcon = document.createElement("i");
        headlineIcon.setAttribute("class","icon-material-outline-lock");
        headlineHeader.appendChild(headlineIcon);
        headlineHeader.innerHTML = "System message: Client " + clientData[0].clientID + " was successfully deleted.";
        newHeadline.appendChild(headlineHeader);

  //Now we execute our AJAX to remove the client from the DB
  deleteClient(clientData[0].clientID);
}


function deleteClient(clientID){
  console.log("The client I will delete forever is " + clientID);
  //The id has been passed correctly
  var request = new XMLHttpRequest();
  request.open('GET','adminDeleteClient?clientID='+ clientID, true);

  request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
        var linesDeleted = JSON.parse(request.responseText);
        console.log(linesDeleted);
      }
    };
    request.send();
}


//Now the second AJAX from this page will be to display the notices from the client's page!
var noticeButton = document.getElementById("notices");

noticeButton.onclick = function(){
  console.log("Ready to show notices from client " + clientData[0].clientID);

  //Now we want to make our AJAX Request
  getClientNotices(clientData[0].clientID);
}

function getClientNotices(clientID){
  console.log("The client I will get notices from is " + clientID);
  //The id has been passed correctly
  var request = new XMLHttpRequest();
  request.open('GET','adminClientNotices?clientID='+ clientID, true);

  request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
        var clientNotices = JSON.parse(request.responseText);
        console.log(clientNotices);

        //Now we send the info to another function for formatting!!
        formatClientNotices(clientNotices, clientID);

        //Now I will disable the Button
        noticeButton.onclick = function(){
          //Do nothing :o
        }
      }
    };
    request.send();
}

function formatClientNotices(notices, clientID){
  var parentNode2 = document.getElementById("anchor2");

    var mainHeaderDiv = document.createElement("div");
    mainHeaderDiv.setAttribute("class","col-xl-12");
    parentNode2.appendChild(mainHeaderDiv);

      var headerCont = document.createElement("div");
      headerCont.setAttribute("class","dashboard-box margin-top-0");
      headerCont.setAttribute("id","anchor3");
      mainHeaderDiv.appendChild(headerCont);

        var newHeader = document.createElement("div");
        newHeader.setAttribute("class","headline");
        headerCont.appendChild(newHeader);

          var headerContent = document.createElement("h3");
          var headerIcon = document.createElement("i");
          headerIcon.setAttribute("class","icon-material-outline-buisiness-center");
          headerContent.appendChild(headerIcon);
          headerContent.innerHTML = "Notices from client " + clientID;
          newHeader.appendChild(headerContent);

        var newContent = document.createElement("div");
        newContent.setAttribute("class","content");
        headerCont.appendChild(newContent);

          var noticeList = document.createElement("ul");
          noticeList.setAttribute("class","dashboard-box-list");
          newContent.appendChild(noticeList);

          for(i = 0; i < notices.length; i++){
              //First we set up the divisions
              var listItem = document.createElement("li");
              noticeList.appendChild(listItem);

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
                      incidenceTitle.setAttribute("href","adminNoticeInfo?noticeID=" + notices[i].noticeID);
                      var incidenceHeader = document.createElement("h2");
                      var headerLink = document.createElement("a");
                      headerLink.setAttribute("href","adminNoticeInfo?noticeID=" + notices[i].noticeID);
                      headerLink.innerHTML = notices[i].subject;
                      jobListingDescription.appendChild(incidenceTitle);
                      jobListingDescription.appendChild(incidenceHeader);
                      incidenceHeader.appendChild(headerLink);

                      var whichClient = document.createElement("h4");
                      whichClient.setAttribute("class","job-listing-title");
                      whichClient.innerHTML = "Client " + notices[i].clientID;
                      jobListingDescription.appendChild(whichClient);

                      var noticeDescription = document.createElement("h4");
                      noticeDescription.setAttribute("class","job-listing-title");
                      noticeDescription.innerHTML = "Message " + notices[i].message;
                      jobListingDescription.appendChild(noticeDescription);

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
              icon.innerHTML = notices[i].datecreated;
              item.appendChild(icon);


              //Now we enable the Buttons
              var visibleButtons = document.createElement("div");
              headerCont.appendChild(visibleButtons);
            }
}
