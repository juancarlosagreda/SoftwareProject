//We use caregiverInfo JSON Object

function createCaregiver() {

  var parentNode = document.getElementById("anchor");

  var clientContainer = document.createElement("div");
  clientContainer.setAttribute("class","single-page-header freelancer-header");
  clientContainer.setAttribute("data-background-image","images/home-background.jpg");
  parentNode.appendChild(clientContainer);

    var clientCont = document.createElement("div");
    clientCont.setAttribute("class","container");
    clientContainer.appendChild(clientCont);

      var mainRow = document.createElement("div");
      mainRow.setAttribute("class","row");
      clientCont.appendChild(mainRow);

        var mainCol = document.createElement("div");
        mainCol.setAttribute("class","col-md-12");
        mainRow.appendChild(mainCol);

          var noHeader = document.createElement("div");
          noHeader.setAttribute("class","single-page-header-inner");
          mainCol.appendChild(noHeader);

            var leftSide = document.createElement("div");
            leftSide.setAttribute("main","left-side");
            noHeader.appendChild(leftSide);

              var avatarCont = document.createElement("div");
              avatarCont.setAttribute("class","header-image freelancer-avatar");
              leftSide.appendChild(avatarCont);

              if (caregiverInfo[0].gender == 2){

                var clientAvatar1 = document.createElement("img");
                clientAvatar1.setAttribute("src","images/female-placeholder.png");
                avatarCont.appendChild(clientAvatar1);

              } else {

                var clientAvatar = document.createElement("img");
                clientAvatar.setAttribute("src","images/user-avatar-placeholder.png");
                avatarCont.appendChild(clientAvatar);
                }

              var headerDetails = document.createElement("div");
              headerDetails.setAttribute("class","header-details");
              leftSide.appendChild(headerDetails);

                var clientName = document.createElement("h3");
                clientName.innerHTML = caregiverInfo[0].firstname + " " + caregiverInfo[0].lastname;
                headerDetails.appendChild(clientName);

                  var clientFooter = document.createElement("span");
                  clientFooter.innerHTML = "Caregiver";
                  clientName.appendChild(clientFooter);


                  var starCont2 = document.createElement("ul");
                  headerDetails.appendChild(starCont2);


              var starList2 = document.createElement("li");
              starCont2.appendChild(starList2);


              var date = document.createElement("i");
              date.setAttribute("class","icon-material-outline-date-range");
              date.innerHTML = caregiverInfo[0].birthdate;
              starList2.appendChild(date);

              var starCont = document.createElement("ul");
              headerDetails.appendChild(starCont);

                var starList = document.createElement("li");
                starCont.appendChild(starList);

                var mail = document.createElement("i");
                mail.setAttribute("class", "icon-feather-mail");
                mail.innerHTML= " " +caregiverInfo[0].email;
                starList.appendChild(mail);






                  //var rating = document.createElement("div");
                  //rating.setAttribute("class","star-rating");
                  //rating.setAttribute("data-rating","3.5");
                  //starList.appendChild(rating);







              var li3 = document.createElement("li");
              starCont.appendChild(li3);

                var descr = document.createElement("h4");
                descr.innerHTML = caregiverInfo[0].description;
                li3.appendChild(descr);






              /*  var trash = document.createElement("a");
                trash.setAttribute("href","request?value="+2+"&caregiverID=" +caregiverInfo[0].caregiverID);
                trash.setAttribute("id","trash");
                trash.setAttribute("class","button gray ripple-effect ico");
                trash.setAttribute("data-tippy-placement","top");
                trash.setAttribute("Title","Delete this caregiver");

                //trash.setAttribute("data-tippy data-original-title","Delete Client");
                starList2.appendChild(trash);

                  var trashIcon = document.createElement("i");
                  trashIcon.setAttribute("class","icon-feather-trash-2");
                  trash.appendChild(trashIcon);*/



              //  var badge = document.createElement("div");
                //badge.setAttribute("class","verified-badge-with-title");
              //  badge.innerHTML = "Verified";
              //  verifiedBadge.appendChild(badge);



    //To make the show notices button, we use the second anchor
    var parentNode2 = document.getElementById("anchor2paula");

    var center = document.createElement("center");
    parentNode2.appendChild(center);

    var buttonContainer = document.createElement("div");
    buttonContainer.setAttribute("id","more");
    buttonContainer.setAttribute("class","col-xl-12");
    center.appendChild(buttonContainer);



    var buttonLink = document.createElement("input");
    buttonLink.setAttribute("type","submit");
    buttonLink.setAttribute("id","more");
    buttonLink.setAttribute("name","more");
    buttonLink.setAttribute("value","Show more Caregiver Info");
    buttonContainer.appendChild(buttonLink);



}

createCaregiver();
