//We use clientData JSON Object

function createClient() {

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

                var clientAvatar = document.createElement("img");
                clientAvatar.setAttribute("src","images/user-avatar-placeholder.png");
                avatarCont.appendChild(clientAvatar);

              var headerDetails = document.createElement("div");
              headerDetails.setAttribute("class","header-details");
              leftSide.appendChild(headerDetails);

                var clientName = document.createElement("h3");
                clientName.innerHTML = clientData[0].firstname + " " + clientData[0].lastname;
                headerDetails.appendChild(clientName);

                  var clientFooter = document.createElement("span");
                  clientFooter.innerHTML = "Client";
                  clientName.appendChild(clientFooter);

              var starCont = document.createElement("ul");
              headerDetails.appendChild(starCont);

                // var starList = document.createElement("li");
                // starCont.appendChild(starList);
                //
                //   var rating = document.createElement("div");
                //   rating.setAttribute("class","star-rating");
                //   rating.setAttribute("data-rating","3.5");
                //   starList.appendChild(rating);


              // var province = document.createElement("li");
              // starCont.appendChild(province);
              //
              //   var provinceText = document.createElement("img");
              //   provinceText.setAttribute("class","flag");
              //   provinceText.innerHTML = " Province";
              //   province.appendChild(provinceText);
              //
              // var verifiedBadge = document.createElement("li");
              // starCont.appendChild(verifiedBadge);
              //
              //   var badge = document.createElement("div");
              //   badge.setAttribute("class","verified-badge-with-title");
              //   badge.innerHTML = "Verified";
              //   verifiedBadge.appendChild(badge);

              var trash = document.createElement("a");
              trash.setAttribute("href","#");
              trash.setAttribute("id","trash");
              trash.setAttribute("class","button gray ripple-effect ico");
              trash.setAttribute("data-tippy-placement","top");
              //trash.setAttribute("data-tippy data-original-title","Delete Client");
              starCont.appendChild(trash);

                var trashIcon = document.createElement("i");
                trashIcon.setAttribute("class","icon-feather-trash-2");
                trash.appendChild(trashIcon);

    //To make the show notices button, we use the second anchor
    var parentNode2 = document.getElementById("anchor2");

    var buttonContainer = document.createElement("div");
    buttonContainer.setAttribute("id","deleteButton");
    buttonContainer.setAttribute("class","col-xl-12");
    parentNode2.appendChild(buttonContainer);

    var buttonLink = document.createElement("a");
    buttonLink.setAttribute("href","#");
    buttonLink.setAttribute("id","notices")
    buttonLink.setAttribute("class","button ripple-effect big margin-top-30");
    buttonLink.innerHTML = "Show Client Notices";
    buttonContainer.appendChild(buttonLink);



}

createClient();
