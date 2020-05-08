
function caregiverAnalytics(){
  //First I need to look up my parent node, which I intentionally left blank
  //Look up the tag in chrome console
  var parentNode = document.getElementById('anchor');

  var firstGraphDiv = document.createElement("div");
  firstGraphDiv.setAttribute("id","chart_div");
  firstGraphDiv.setAttribute("style","width: 900px; height: 500px;");
  parentNode.appendChild(firstGraphDiv);

//Now we want to create the new box.. we use the same anchor2
var parentNode2 = document.getElementById("anchor2");

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
                headlineHeader2.innerHTML = "Caregiver Analytics";
                newHeadline2.appendChild(headlineHeader2);

                var content2 = document.createElement("div");
                content2.setAttribute("class","content with-padding");
                boxDiv2.appendChild(content2);

                  var secondGraphDiv = document.createElement("div");
                  secondGraphDiv.setAttribute("id","chart_div2");
                  secondGraphDiv.setAttribute("style","width: 900px; height: 500px;");
                  content2.appendChild(secondGraphDiv);


}

caregiverAnalytics();
