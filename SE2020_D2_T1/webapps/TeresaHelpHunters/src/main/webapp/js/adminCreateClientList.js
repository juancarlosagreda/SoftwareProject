
function createClientList() {

    //For AJAX implementation I will include the male/female filter
    //So we will append a new filter
    var filterParent = document.getElementById("filter");

      var select = document.createElement("div");
      select.setAttribute("class","btn-group bootstrap-select");
      select.setAttribute("id","optionPicker")
      filterParent.appendChild(select);

        //First we create the button we can click
        // var selectButton = document.createElement("button");
        // selectButton.setAttribute("type","button");
        // selectButton.setAttribute("class","btn dropdown-toggle btn-default");
        // selectButton.setAttribute("data-toggle","dropdown");
        // selectButton.setAttribute("role","button");
        // selectButton.setAttribute("title","Filter");
        // select.appendChild(selectButton);

          var option = document.createElement("span");
          option.setAttribute("class","filter-option pull-left");
          option.innerHTML = "Filter";

        //Then we create the combo box

        //Then we create the selectpicker
        var selectOptions = document.createElement("select");
        selectOptions.setAttribute("class","selectpicker");
        selectOptions.setAttribute("data-live-search","true");
        selectOptions.setAttribute("tabindex","-98");
        selectOptions.setAttribute("id","currentValue");
        select.appendChild(selectOptions);

          var all = document.createElement("option");
          all.innerHTML = "All";
          selectOptions.appendChild(all);

          var male = document.createElement("option");
          male.innerHTML = "Male";
          selectOptions.appendChild(male);

          var female = document.createElement("option");
          female.innerHTML = "Female";
          selectOptions.appendChild(female);

    //First I need to look up my parent node, which I intentionally left blank
    //Look up the tag in chrome console
    var parentNode = document.getElementById('anchor');

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


createClientList();
