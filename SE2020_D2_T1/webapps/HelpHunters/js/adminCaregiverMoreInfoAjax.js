


//Now the second AJAX from this page will be to display the notices from the client's page!
var infobutton = document.getElementById("more");

infobutton.onclick = function(){ clientextrainfo()};

//  getmoreCaregiverInfo(caregiverInfo[0].caregiverID);

function clientextrainfo(){
  var parentNode2 = document.getElementById("anchor2paula");

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
  headerContent.innerHTML = "More info of caregiver " +caregiverInfo[0].caregiverID;
  headerIcon.value = +caregiverInfo[0].caregiverID;
  getmoreCaregiverInfo(headerIcon.value);
  newHeader.appendChild(headerContent);

  var newContent = document.createElement("div");
  newContent.setAttribute("class","content");
  headerCont.appendChild(newContent);

  var noticeList = document.createElement("ul");
  noticeList.setAttribute("class","dashboard-box-list");
  newContent.appendChild(noticeList);

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

 var location = document.createElement("i");
 location.setAttribute("class", "icon-material-outline-location-on");
 jobListingDetails.appendChild(location);



  var item1 =document.createElement("h4");
  item1.setAttribute("name", "provincia");
  item1.setAttribute("class", "with-border");
  item1.setAttribute("id", "provincia");
  jobListingDetails.appendChild(item1);

  var item2 =document.createElement("h4");
  item2.setAttribute("name", "precio");
  item2.setAttribute("class", "with-border");
  item2.setAttribute("id", "precio");
  item2.innerHTML ="The price per hour from this caregiver is $" + caregiverInfo[0].hourlyrate;
  jobListingDescription.appendChild(item2);



var item5 =document.createElement("h4");
jobListingDescription.appendChild(item5);

if (caregiverInfo[0].available ==0){

var statusButton = document.createElement("span");
statusButton.setAttribute("class","dashboard-status-button red");
statusButton.innerHTML = "Unavailable";
item5.appendChild(statusButton);
} else {
  var statusButton = document.createElement("span");
  statusButton.setAttribute("class","dashboard-status-button green");
  statusButton.innerHTML = "Available";
  item5.appendChild(statusButton);

}

var li = document.createElement("center");
item5.appendChild(li);
//var li2 = document.createElement("ul");
//li.appendChild(li2);
var li3 = document.createElement("li");
li.appendChild(li3);
  var trash = document.createElement("a");
    trash.setAttribute("href","request?value="+2+"&caregiverID=" +caregiverInfo[0].caregiverID);
    trash.setAttribute("id","trash");
    trash.setAttribute("class","button gray ripple-effect ico");
    trash.setAttribute("data-tippy-placement","top");
    trash.setAttribute("Title","Delete this caregiver");
    li3.appendChild(trash);

      var trashIcon = document.createElement("i");
      trashIcon.setAttribute("class","icon-feather-trash-2");
      trash.appendChild(trashIcon);



  }




    function getmoreCaregiverInfo(caregiver){
    console.log("The client I will get info from is " + caregiver);
              //The id has been passed correctly
    var request = new XMLHttpRequest();
    request.open('GET','getCaregiverExtraInfo2?json&caregiverID='+ caregiver, true);

    request.onload = function() {
    if (request.status >= 200 && request.status < 400) {
    var caregivInfo = JSON.parse(request.responseText);
    console.log(caregivInfo);

    document.getElementById("provincia").innerHTML = caregivInfo.provincia2;

                    //Now we send the info to another function for formatting!!
                  //  clientextrainfo(caregivInfo);
      }
      };
      request.send();
      }
