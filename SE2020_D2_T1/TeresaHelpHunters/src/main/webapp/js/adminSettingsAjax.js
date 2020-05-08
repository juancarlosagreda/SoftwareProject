//First we import all of the values and items that we are working with
//We also have to know that we have adminData JSON in our source code
var firstname = document.getElementById("firstname");
var lastname = document.getElementById("lastname");
var email = document.getElementById("email");
var password = document.getElementById("password");
var newpassword = document.getElementById("newpassword");
var repeatpassword = document.getElementById("repeat");
var button = document.getElementById("saveButton");

//Our containers we will append text nodes to:
var currentpwCont = document.getElementById("currentpwCont");
var newpwCont = document.getElementById("newPwCont");

firstname.onchange = function(){
  //console.log("The first name was " + adminData[0].firstname + " and now is " + firstname.value);
  validatePasswords();
}

lastname.onchange = function(){
  //console.log("The last name was " + adminData[0].lastname + " and now is " + lastname.value);
  validatePasswords();
}

email.onchange = function(){
  //console.log("The email was " + adminData[0].email + " and now is " + email.value);
  validatePasswords();
}

password.onchange = function(){
  //console.log("The password was " + adminData[0].password + " and now is " + password.value);
  validatePasswords();
  //Need to add de-clutter
}

newpassword.onchange = function(){
  validatePasswords();

  if(document.getElementById("alertContainer2") != null){
    var alertContainer2 = document.getElementById("alertContainer2")
    while(alertContainer2.firstChild){
      alertContainer2.removeChild(alertContainer2.firstChild);
    }
  }
}

repeatpassword.onchange = function(){
  validatePasswords();

  if(document.getElementById("alertContainer2") != null){
    var alertContainer2 = document.getElementById("alertContainer2")
    while(alertContainer2.firstChild){
      alertContainer2.removeChild(alertContainer2.firstChild);
    }
  }
}

function validatePasswords(){
    if(password.value.localeCompare(adminData[0].password) == 0){
      //properChange();
      if(newpassword.value.localeCompare(repeatpassword.value) == 0){
        if(newpassword.value.localeCompare(adminData[0].password) == 0){
          //You cant have the same password
          samePasswords();
        } else {
          //You are good to go
          if(newpassword.value.localeCompare("") == 0){
            properChange();
          } else {
            properChangePassword();
          }
        }
      } else {
        //Your original password is incorrect
        newDontMatch();
      }
    } else {
      //Your original password is incorrect
      incorrectPassword();
    }
}

function samePasswords(){

  //First off we check if the textnode container 2 has something so we can erase it
  if(document.getElementById("alertContainer2") != null){
    var alertContainer2 = document.getElementById("alertContainer2")
    while(alertContainer2.firstChild){
      alertContainer2.removeChild(alertContainer2.firstChild);
    }
  }
  //Now that we know that there is nothing there... we fill it up
  //Note that we are appending to the new password box
  var textNodeCont2 = document.createElement("span");
  textNodeCont2.setAttribute("id","alertContainer2");
  var askPassword = document.createTextNode("Your new password is the same as the original one.");
  textNodeCont2.appendChild(askPassword);
  textNodeCont2.style.color = "red";
  newpwCont.appendChild(textNodeCont2);


  //Now we set up our button action
  button.onclick = function(){
    noButtonAction();
  }
}

function properChangePassword(){


  var textNodeCont = document.createElement("span");
  textNodeCont.setAttribute("id","alertContainer");
  var askPassword = document.createTextNode("Your password is correct.");
  textNodeCont.appendChild(askPassword);
  textNodeCont.style.color = "green";
  currentpwCont.appendChild(textNodeCont);

  //Now that we know that there is nothing there... we fill it up
  //Note that we are appending to the new password box
  var textNodeCont2 = document.createElement("span");
  textNodeCont2.setAttribute("id","alertContainer2");
  var askPassword = document.createTextNode("Your passwords match");
  textNodeCont2.appendChild(askPassword);
  textNodeCont2.style.color = "green";
  newpwCont.appendChild(textNodeCont2);

  //Now we set up the button :)
  button.onclick = function(){
    buttonActionPW();
  }
}

function properChange(){


  var textNodeCont = document.createElement("span");
  textNodeCont.setAttribute("id","alertContainer");
  var askPassword = document.createTextNode("Your password is correct");
  textNodeCont.appendChild(askPassword);
  textNodeCont.style.color = "green";
  currentpwCont.appendChild(textNodeCont);
  //console.log("Inside proper change function")



  //Now we set up our button action
  button.onclick = function(){
    //console.log("Clicked");
    buttonAction();
  }
}

function newDontMatch(){
  //First off we check if the textnode container 2 has something so we can erase it

  var textNodeCont2 = document.createElement("span");
  textNodeCont2.setAttribute("id","alertContainer2");
  var askPassword = document.createTextNode("The passwords don't match");
  textNodeCont2.appendChild(askPassword);
  textNodeCont2.style.color = "red";
  newpwCont.appendChild(textNodeCont2);



  //Now we set up our button action
  button.onclick = function(){
    noButtonAction();
  }
}

function incorrectPassword(){
  //First off we check if the textnode container  has something so we can erase it

  // var textNodeCont = document.createElement("span");
  // textNodeCont.setAttribute("id","alertContainer");
  // var askPassword = document.createTextNode("Your password is incorrect");
  // textNodeCont.appendChild(askPassword);
  // textNodeCont.style.color = "red";
  // currentpwCont.appendChild(textNodeCont);

  //Now we set up our button action
  button.onclick = function(){
    noButtonAction();
  }
}

function noButtonAction(){
  //Do nothing :)
}

function buttonAction(){
  console.log("Inside button action");
  var request = new XMLHttpRequest();
  request.open('GET', 'adminSettingsAjax?id='+adminData[0].administratorID+'&f=' + firstname.value + '&l=' + lastname.value +'&e=' + email.value, true);
  request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
        newInfo = JSON.parse(request.responseText);
        console.log(newInfo);


      //Now we delete and re make

      //Aqui kill child nodes
      var parentNode2 = document.getElementById("anchor2");
      while(parentNode2.firstChild){
        parentNode2.removeChild(parentNode2.firstChild);

      var col = document.createElement("div");
      col.setAttribute("class","col-xl-12");
      parentNode2.appendChild(col);

        var dashboard = document.createElement("div");
        dashboard.setAttribute("class","dashboard-margin-top-0");
        dashboard.setAttribute("id","anchor");
        col.appendChild(dashboard);

          var headline = document.createElement("div");
          headline.setAttribute("class","headline");
          dashboard.appendChild(headline);

            var header = document.createElement("h3");
            var icon = document.createElement("i");
            icon.setAttribute("class","icon-material-outline-business-center");
            header.appendChild(icon);
            header.innerHTML = " The changes have been saved.";
            headline.appendChild(header);
          }
        }
    };
    request.send();
}



function buttonActionPW(){
  var request = new XMLHttpRequest();
  request.open('GET', 'adminSettingsAjax?id='+adminData[0].administratorID+'&f='+ firstname.value +'&l='+ lastname.value +'&e='+ email.value +'&p='+newpassword.value, true);

  request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
        newInfo = JSON.parse(request.responseText);
        console.log(newInfo);


        //Now we delete and re make

        //Aqui kill child nodes
        var parentNode2 = document.getElementById("anchor2");
        while(parentNode2.firstChild){
          parentNode2.removeChild(parentNode2.firstChild);
        }
        var col = document.createElement("div");
        col.setAttribute("class","col-xl-12");
        parentNode2.appendChild(col);

          var dashboard = document.createElement("div");
          dashboard.setAttribute("class","dashboard-margin-top-0");
          dashboard.setAttribute("id","anchor");
          col.appendChild(dashboard);

            var headline = document.createElement("div");
            headline.setAttribute("class","headline");
            dashboard.appendChild(headline);

              var header = document.createElement("h3");
              var icon = document.createElement("i");
              icon.setAttribute("class","icon-material-outline-business-center");
              header.appendChild(icon);
              header.innerHTML = " The changes have been saved.";
              headline.appendChild(header);

    };
    request.send();
  }
 }
