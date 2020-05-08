var replyButton = document.getElementById("createReply");

if(replyButton != null){
replyButton.onclick = function(){
  console.log("Ready to reply!!");

  //Now we create a reply box
  var parentNode2 = document.getElementById("anchor2");

  var messageReply = document.createElement("div");
  messageReply.setAttribute("class","message-reply");
  parentNode2.appendChild(messageReply);

    var text = document.createElement("textarea");
    text.setAttribute("id","message");
    text.setAttribute("cols","1");
    text.setAttribute("rows","1");
    text.setAttribute("style","height: 48px; width: 800px")
    text.setAttribute("placeholder","  Reply to notice");
    messageReply.appendChild(text);

    var sendReply = document.createElement("button");
    sendReply.setAttribute("id","sendButton");
    sendReply.setAttribute("class","button dark ripple-effect");
    sendReply.innerHTML = "Send";
    messageReply.appendChild(sendReply);

    //Now we code what the send reply button does (depending on what is in the text box)
    if(text.value != null){
        sendReply.onclick = function(){
          showReply(text.value);
        }
  } else {
    sendReply.onclick = function(){
      //Do nothing
    }
  }
 }
}

function showReply(replymessage){
  var request = new XMLHttpRequest();
  request.open('GET', 'adminMakeIncidenceAjax?id=' + noticeData[0].noticeID + '&q=' + replymessage, true);
  request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
          newData = JSON.parse(request.responseText);

          //We verify that the text info is correct
          console.log(newData)

          //Now we delete everything
          var parentNode2 = document.getElementById("anchor2");
          while(parentNode2.firstChild){
            parentNode2.removeChild(parentNode2.firstChild);
          }

          //Now we send the info to a function to re create the page
          updatedNotice(newData, replymessage);

      }
    };
  request.send();
}


function updatedNotice(noticeData, replymessage){
  //We grasp on to our parentNode2 once again :)
  var parentNode2 = document.getElementById("anchor2");

    var column = document.createElement("div");
    column.setAttribute("class","col-xl-12");
    parentNode2.appendChild(column);

      var parentNode = document.createElement("div");
      parentNode.setAttribute("class","dashboard-box margin-top-0");
      parentNode.setAttribute("id","anchor");
      column.appendChild(parentNode);

      //Game over .. we recycle code
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

              var noticeReply = document.createElement("a");
              noticeReply.setAttribute("href","#");
              noticeDetails.appendChild(noticeReply);

              var reply = document.createElement("i");
              reply.setAttribute("class","icon-feather-mail");
              //reply.innerHTML = " Reply: " + replymessage.outerHTML;
              reply.innerHTML = " Reply: " + noticeData[0].reply;
              noticeReply.appendChild(reply);


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

}

var incidenceButton =   document.getElementById("makeIncidence");

incidenceButton.onclick = function(){

  var request = new XMLHttpRequest();
  request.open('GET', 'adminMakeIncidenceAjax?id=' + noticeData[0].noticeID, true); //Esto significa que necesito un request handler EN MI SERVLET

  request.onload = function() {
      if (request.status >= 200 && request.status < 400) {

        //Aqui kill child nodes
        var parentNode2 = document.getElementById("anchor2");
        while(parentNode2.firstChild){
          parentNode2.removeChild(parentNode2.firstChild);
        }

        //Now we have to create another headline..(appended to parent node 2)
        var newcol = document.createElement("div");
        newcol.setAttribute("class","col-xl-12");
        parentNode2.appendChild(newcol);

          var dboard = document.createElement("div");
          dboard.setAttribute("class","dashboar-box margin-top-0");
          dboard.setAttribute("id","anchor");
          newcol.appendChild(dboard);

            var newHeadline = document.createElement("div");
            newHeadline.setAttribute("class","headline");
            dboard.appendChild(newHeadline);

              var titleHeadline = document.createElement("h3");
              titleHeadline.innerHTML = " The incidence was created.";
              newHeadline.appendChild(titleHeadline);

      }
    };
  request.send();
}
