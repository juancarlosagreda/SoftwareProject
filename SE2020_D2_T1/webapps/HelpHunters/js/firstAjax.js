var options = document.getElementById("optionPicker");

options.onchange = function(){
  var optionsValue = document.getElementById("currentValue").value;
  createNewList(optionsValue);
  //alert("new current value: " + optionsValue)
  //alert("The sex chosen is:" + optionsValue); //YEET
}

function createClientList(clientsData) {

  console.log(clientsData);
  //First I need to look up my parent node, which I intentionally left blank
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
    headerTitle.innerHTML = " Clients";
    headline.appendChild(headerTitle);

  var content = document.createElement("div");
  content.setAttribute("class","content");
  parentNode.appendChild(content);

    var startList = document.createElement("ul");
    startList.setAttribute("class","dashboard-box-list");
    startList.setAttribute("id","deleteNodes");
    content.appendChild(startList);

        for(i = 0; i < clientsData.length; i++){
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

            var clientName = document.createElement("a");
            var name = document.createTextNode(clientsData[i].firstname + " " + clientsData[i].lastname);
            clientName.setAttribute("href","adminClientInfo?clientID=" + clientsData[i].clientID);
            clientName.appendChild(name);
            header.appendChild(clientName);
            var statusButton = document.createElement("span");
            statusButton.setAttribute("class","dashboard-status-button "+ clientsData[i].color+"");
            statusButton.innerHTML = clientsData[i].status;
            clientName.appendChild(statusButton);

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
            icon.innerHTML = clientsData[i].email;
            item.appendChild(icon);
          }
}

function createNewList(sex){
  var request = new XMLHttpRequest();
  request.open('GET', 'adminAjax?sex=' + sex, true); //Esto significa que necesito un request handler EN MI SERVLET

  request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
        clientsData = JSON.parse(request.responseText);
        console.log(clientsData);
        //Aqui kill child nodes
        var parentNode = document.getElementById("anchor");
        while(parentNode.firstChild){
          parentNode.removeChild(parentNode.firstChild);
        }

        // Basically paste mi js que  crea la tabla
        // Para que se vea mas lindo hago una function :)
        createClientList(clientsData);

      }
    };
    request.send();
  }


//Event handler
