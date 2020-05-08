
function createJobList() {

    var parentNode = document.getElementById('jobList'); 

    for(i = 0; i < jobs.length; i++){

        var listItem = document.createElement("li");
        parentNode.appendChild(listItem);

        var joblisting = document.createElement("div");
        joblisting.setAttribute("class", "job-listing");
        listItem.appendChild(joblisting);

        var joblistingdetails = document.createElement("div");
        joblistingdetails.setAttribute("class", "job-listing-details");
        joblisting.appendChild(joblistingdetails);

        var joblistingdescription = document.createElement("div");
        joblistingdescription.setAttribute("class", "job-listing-description");
        joblistingdetails.appendChild(joblistingdescription);

        var header = document.createElement("h3");
        header.setAttribute("class", "job-listing-title");
        header.innerText=jobs[i].firstname + " " + jobs[i].lastname ;
        joblistingdescription.appendChild(header);

        var span = document.createElement("span");
        span.setAttribute("class", "dashboard-status-button green");
        span.innerText="Pending";
        header.appendChild(span);

        var div = document.createElement("div");
        div.setAttribute("class", "buttons-to-right always-visible");
        joblistingdescription.appendChild(div);

        var client = document.createElement("a");
        client.setAttribute("id", "client");
        client.setAttribute("href","viewClientProfile?clientID="+ jobs[i].clientID);
        client.setAttribute("class", "button ");
        client.innerHTML="View Profile"
        div.appendChild(client);

        var space = document.createElement("div");
        space.setAttribute("class", "margin-top-10");
        header.appendChild(space);

        var par = document.createElement("p");
        div.appendChild(par);

        var accept = document.createElement("a");
        accept.setAttribute("id", "accept");
        accept.setAttribute("href","status?value="+2+"&employmentID="+jobs[i].employmentID);
        accept.setAttribute("class", "button dark ripple-effect ");
        accept.innerText="Accept"
        par.appendChild(accept);

        var decline = document.createElement("a");
        decline.setAttribute("id", "decline");
        decline.setAttribute("href","status?value="+3+"&employmentID="+jobs[i].employmentID);
        decline.setAttribute("class", "button dark ripple-effect");
        decline.innerText="Decline"
        par.appendChild(decline);

        var space2 = document.createElement("div");
        space2.setAttribute("class", "margin-top-15");
        div.appendChild(space2);
    }
}

createJobList();



