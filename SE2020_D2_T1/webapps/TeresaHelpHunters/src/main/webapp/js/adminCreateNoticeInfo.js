
function createNoticeInfo() {

    //First I need to look up my parent node, which I intentionally left blank
    //Look up the tag in chrome console
    var parentNode = document.getElementById('anchor');

    var content = document.createElement("div");
    content.setAttribute("class","content");
    parentNode.appendChild(content);

    var startList = document.createElement("ul");
    startList.setAttribute("class","dashboard-box-list");
    content.appendChild(startList);

        //First we set up the divisions
        var listItem = document.createElement("li");
        startList.appendChild(listItem);

        var jobListing = document.createElement("div");
        jobListing.setAttribute("class", "freelancer-overview manage-candidates");
        listItem.appendChild(jobListing);

        var details = document.createElement("div");
        details.setAttribute("class", "freelancer-overview-inner");
        jobListing.appendChild(details);

          var avatar = document.createElement("div");
          avatar.setAttribute("class", "freelancer-avatar");
          details.appendChild(avatar);

            var avatarImage = document.createElement("img");
            avatarImage.setAttribute("src","images/user-avatar-placeholder.png");
            avatar.appendChild(avatarImage);

        var infoContainer = document.createElement("div");
        infoContainer.setAttribute("class","freelancer-name");
        details.appendChild(infoContainer);

          var client = document.createElement("h4");
          infoContainer.appendChild(client);

            var clientLink = document.createElement("a");
            clientLink.setAttribute("href","adminClientInfo?clientID=" + noticeData[0].clientID);
            clientLink.innerHTML = "Client " + noticeData[0].clientID;
            client.appendChild(clientLink);


      var noticeDetails = document.createElement("span");
      noticeDetails.setAttribute("class","freelancer-detail-item");
      infoContainer.appendChild(noticeDetails);

        var mailLink = document.createElement("a");
        mailLink.setAttribute("href","#");
        noticeDetails.appendChild(mailLink);

        var mailIcon = document.createElement("i");
        mailIcon.setAttribute("class","icon-feather-mail");
        mailIcon.innerHTML = " Message: " + noticeData[0].message + "<br>";
        mailLink.appendChild(mailIcon);


        //REALLY unorthodox but it is the only way this will work... doesnt recognize null values :(
        if ((noticeData[0].reply.localeCompare("Null") == 0) || (noticeData[0].reply.localeCompare("Nul") == 0) || (noticeData[0].reply.localeCompare("null") == 0)) {
            var noticeReply = document.createElement("a");
            noticeReply.setAttribute("href","#");
            noticeDetails.appendChild(noticeReply);

            var reply = document.createElement("i");
            reply.setAttribute("class","icon-feather-mail");
            reply.innerHTML = "Unreplied";
            noticeReply.appendChild(reply);

        } else {
            var noticeReply = document.createElement("a");
            noticeReply.setAttribute("href","#");
            noticeDetails.appendChild(noticeReply);

            var reply = document.createElement("i");
            reply.setAttribute("class","icon-feather-mail");
            reply.innerHTML = " Reply: " + noticeData[0].reply;
            noticeReply.appendChild(reply);
        }

      var buttonContainer = document.createElement("div");
      buttonContainer.setAttribute("class","buttons-to-right always-visible margin-top-25 margin-bottom-0");
      //buttonContainer.setAttribute("class","buttons-to-right always-visible");
      infoContainer.appendChild(buttonContainer);

        var incidenceButton = document.createElement("a");
        incidenceButton.setAttribute("id","makeIncidence");
        incidenceButton.setAttribute("href","#");
        incidenceButton.setAttribute("class","button red ripple-effect");
        incidenceButton.innerHTML = "This is an incidence  ! "
        buttonContainer.appendChild(incidenceButton);

          var incidenceIcon = document.createElement("i");
          incidenceIcon.setAttribute("class","icon-material-outline-supervisor-account");
          incidenceButton.appendChild(incidenceIcon);

          //REALLY unorthodox but it is the only way this will work... doesnt recognize null values :(
if ((noticeData[0].reply.localeCompare("Null") == 0) || (noticeData[0].reply.localeCompare("Nul") == 0) || (noticeData[0].reply.localeCompare("null") == 0)) {
      //Make the reply button visible
      var emailButton = document.createElement("a");
      emailButton.setAttribute("id","createReply");
      emailButton.setAttribute("href","#");
      emailButton.setAttribute("class","button dark ripple-effect");
      emailButton.innerHTML = "Reply to Client ";
      buttonContainer.appendChild(emailButton);

        var emailIcon = document.createElement("i");
        emailIcon.setAttribute("class","icon-feather-mail");
        emailButton.appendChild(emailIcon);
    } else {
      //Do nothing
      }
}


createNoticeInfo();
