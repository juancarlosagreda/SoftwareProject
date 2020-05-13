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


password.onchange = function(){

  if(document.getElementById("alertContainer") != null){
    var alertContainer = document.getElementById("alertContainer");
    alertContainer.innerHTMl = "";
  }
}

newpassword.onchange = function(){

  if(document.getElementById("alertContainer2") != null){
    var alertContainer2 = document.getElementById("alertContainer2");
    alertContainer2.innerHTMl = "";

  }
}

repeatpassword.onchange = function(){

  if(document.getElementById("alertContainer2") != null){
    var alertContainer2 = document.getElementById("alertContainer2");
    alertContainer2.innerHTMl = "";

  }
}

button.onclick = function(){
  validatePasswords();
  console.log("im getting clicked");
}

function validatePasswords(){

    if(password.value.localeCompare(adminData[0].password) == 0){
      if(newpassword.value.localeCompare(repeatpassword.value) == 0){
        if(newpassword.value.localeCompare(adminData[0].password) == 0){
          //You cant have the same password
          samePasswords();
        } else {
          //You are good to go
          if(newpassword.value.localeCompare("") == 0){
            //properChange();
            buttonAction();
          } else {
            //properChangePassword();
            buttonActionPW();
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

  if(document.getElementById("alertContainer") == null){
    var textNodeCont2 = document.createElement("span");
    textNodeCont2.setAttribute("id","alertContainer2");
    var askPassword = document.createTextNode("Your new password is the same as the original one.");
    textNodeCont2.appendChild(askPassword);
    textNodeCont2.style.color = "red";
    newpwCont.appendChild(textNodeCont2);
  }
}

function newDontMatch(){

  if(document.getElementById("alertContainer2") == null){
    var textNodeCont2 = document.createElement("span");
    textNodeCont2.setAttribute("id","alertContainer2");
    var askPassword = document.createTextNode("The passwords don't match");
    textNodeCont2.appendChild(askPassword);
    textNodeCont2.style.color = "red";
    newpwCont.appendChild(textNodeCont2);
  }
}

function incorrectPassword(){

  if(document.getElementById("alertContainer") == null){
    var textNodeCont = document.createElement("span");
    textNodeCont.setAttribute("id","alertContainer");
    var askPassword = document.createTextNode("Your password is incorrect");
    textNodeCont.appendChild(askPassword);
    textNodeCont.style.color = "red";
    currentpwCont.appendChild(textNodeCont);
  }

}

function buttonAction(){
  button.setAttribute("href", 'adminSettingsAjax?id='+adminData[0].administratorID+'&f=' + firstname.value + '&l=' + lastname.value +'&e=' + email.value);
}



function buttonActionPW(){
  button.setAttribute("href",'adminSettingsAjax?id='+adminData[0].administratorID+'&f='+ firstname.value +'&l='+ lastname.value +'&e='+ email.value +'&p='+newpassword.value);
 }
