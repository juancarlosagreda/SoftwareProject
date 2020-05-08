

function createRequestList() {


    var parentNode = document.getElementById('anchor');


    for(i = 0; i < requestsData.length; i++){
        //First we set up the divisions

        var content = document.createElement("div");
        content.setAttribute("class","content");
        parentNode.appendChild(content);

        var je = document.createElement("div");
        je.setAttribute("class", "headline");
        content.appendChild(je);

        var startList = document.createElement("ul");
        startList.setAttribute("class","dashboard-box-list");
        je.appendChild(startList);



        var listItem = document.createElement("li");
        startList.appendChild(listItem);

        var candidateListing = document.createElement("div");
        candidateListing.setAttribute("class", "freelancer-overview manage-candidates");
        listItem.appendChild(candidateListing);

        var candidateListingDetails = document.createElement("div");
        candidateListingDetails.setAttribute("class", "freelancer-overview-inner");
        candidateListing.appendChild(candidateListingDetails);

        var candidateListingDescription = document.createElement("div");
        candidateListingDescription.setAttribute("class", "freelancer-name");
        candidateListingDetails.appendChild(candidateListingDescription);

        //Then we create the header and the core of the table box
        var header = document.createElement("h2");
        candidateListingDescription.appendChild(header);

        var name = document.createTextNode(requestsData[i].firstname + " " + requestsData[i].lastname);
        header.appendChild(name);

        var candmail = document.createElement("span");
        candmail.setAttribute("class", "freelancer-detail-item");
        var iconmail = document.createElement("i");
        iconmail.setAttribute("class", "icon-feather-mail");
        iconmail.innerText= " "+ requestsData[i].email;
        candmail.appendChild(iconmail);
        candidateListingDescription.appendChild(candmail);

        var ulist3 = document.createElement("ul");
        ulist3.setAttribute("class", "dashboard-task-info bid-info");
        candidateListingDescription.appendChild(ulist3);

        var ilist3 = document.createElement("li");
        ulist3.appendChild(ilist3);

        var str3= document.createElement("strong");
        str3.innerText="$ " + requestsData[i].hourlyrate
        ilist3.appendChild(str3);
        var span3= document.createElement("span");
        span3.innerText="Per Hour";
        str3.appendChild(span3);

        var ilist4 = document.createElement("li");
        ulist3.appendChild(ilist4);

        var location=document.createElement("i");
        location.setAttribute("class","icon-material-outline-location-on");
        var str4= document.createElement("strong");

        str4.innerText= requestsData[i].prov

        ilist4.appendChild(location);

        ilist4.appendChild(str4);



        //Now we do the footer
        var candidateListingFooter = document.createElement("div");
        candidateListingFooter.setAttribute("class","buttons-to-right always-visible margin-top-25 margin-bottom-0");
        candidateListingDescription.appendChild(candidateListingFooter);

        var accept = document.createElement("a");

        accept.setAttribute("id", "accept");
        accept.setAttribute("href","request?value=" +1+ "&caregiverID=" +requestsData[i].caregiverID );
        accept.setAttribute("class"," button ripple-effect");
        accept.innerText="Accept Offer     "
        candidateListingFooter.appendChild(accept);

        var check = document.createElement("i");
        check.setAttribute("class", "icon-feather-user-check");

        accept.appendChild(check);

        var decline = document.createElement("a");
        decline.setAttribute("id", "decline");
        decline.setAttribute("href","request?value=" +2+ "&caregiverID=" +requestsData[i].caregiverID);
        decline.setAttribute("class","button dark ripple-effect");
        decline.innerText="Decline Offer     "
        candidateListingFooter.appendChild(decline);

        var equi = document.createElement("i");
        equi.setAttribute("class", "icon-feather-user-x");

        decline.appendChild(equi);






        //Now we enable the Buttons
        var visibleButtons = document.createElement("div");
        decline.appendChild(visibleButtons);
      }

}


createRequestList();
