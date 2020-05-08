
function CreateIncidenceAnalyticsList() {

    //First I need to look up my parent node, which I intentionally left blank
    //Look up the tag in chrome console
  //  var parentNode = document.getElementById('anchor');

  /*  var firstGraphDiv = document.createElement("div");
    firstGraphDiv.setAttribute("id","bar");
    firstGraphDiv.setAttribute("style","width: 900px; height: 500px;");
    parentNode.appendChild(firstGraphDiv);*/


    var parentNode2 = document.getElementById("anchor2paula");

              var newBox2 = document.createElement("div");
              newBox2.setAttribute("class","col-xl-12");
              parentNode2.appendChild(newBox2);

                var boxDiv2 = document.createElement("div");
                boxDiv2.setAttribute("class","dashboard-box");
                newBox2.appendChild(boxDiv2);

                  var newHeadline2 = document.createElement("div");
                  newHeadline2.setAttribute("class","headline");
                  boxDiv2.appendChild(newHeadline2);

                    var headlineHeader2 = document.createElement("h3");
                    var headlineIcon = document.createElement("i");
                    headlineIcon.setAttribute("class","icon-material-outline-lock");
                    headlineHeader2.appendChild(headlineIcon);
                    headlineHeader2.innerHTML = "Incidences/Notices ";
                    newHeadline2.appendChild(headlineHeader2);

                    var legend =document.createElement("div");
                    var legend = document.createElement ("class", "headline");
                    boxDiv2.appendChild(legend);

                    var startList = document.createElement("ul");
                    startList.setAttribute("class","dashboard-box-list");
                    legend.appendChild(startList);


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
                        var header = document.createElement("h4");
                        header.innerHTML = "Classification 0: Not defined";
                        jobListingDescription.appendChild(header);

                        var header2 = document.createElement("h4");
                        header2.innerHTML = "Classification 1: Incident";
                        jobListingDescription.appendChild(header2);

                        var header3 = document.createElement("h4");
                        header3.innerHTML = "Classification 2: Great news";
                        jobListingDescription.appendChild(header3);





                    var content2 = document.createElement("div");
                    content2.setAttribute("class","content with-padding");
                    boxDiv2.appendChild(content2);

                      var secondGraphDiv = document.createElement("div");
                      secondGraphDiv.setAttribute("id","pie");
                      secondGraphDiv.setAttribute("style","width: 900px; height: 500px;");
                      content2.appendChild(secondGraphDiv);



      }




CreateIncidenceAnalyticsList();
