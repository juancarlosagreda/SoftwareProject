
function clientAnalytics(){
  //First I need to look up my parent node, which I intentionally left blank
  //Look up the tag in chrome console
  var parentNode = document.getElementById('anchor');

      var firstGraphDiv = document.createElement("div");
      firstGraphDiv.setAttribute("id","chart_div");
      firstGraphDiv.setAttribute("style","width: 900px; height: 500px;");
      parentNode.appendChild(firstGraphDiv);


  //Now we want to create the second chart with another box.. we use another anchor we created already
  var parentNode2 = document.getElementById("anchor2");

  var newBox = document.createElement("div");
  newBox.setAttribute("class","col-xl-12");
  parentNode2.appendChild(newBox);

    var boxDiv = document.createElement("div");
    boxDiv.setAttribute("class","dashboard-box");
    newBox.appendChild(boxDiv);

      var newHeadline = document.createElement("div");
      newHeadline.setAttribute("class","headline");
      boxDiv.appendChild(newHeadline);

        var headlineHeader2 = document.createElement("h3");
        var headlineIcon = document.createElement("i");
        headlineIcon.setAttribute("class","icon-material-outline-lock");
        headlineHeader2.appendChild(headlineIcon);
        headlineHeader2.innerHTML = "Client Analytics";
        newHeadline.appendChild(headlineHeader2);

          var secondGraphDiv = document.createElement("div");
          secondGraphDiv.setAttribute("id","chart_div2");
          secondGraphDiv.setAttribute("style","width: 900px; height: 500px;");
          headlineHeader2.appendChild(secondGraphDiv);


}

clientAnalytics();
