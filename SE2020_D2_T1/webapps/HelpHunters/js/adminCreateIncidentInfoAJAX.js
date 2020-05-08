




aqui = document.getElementById('botonwarning');
aqui.onclick = function() {newwarning()};

function newwarning(){
  var parentNode2 = document.getElementById('anchor2paula');

//  var content = document.createElement("div");
//  content.setAttribute("class","headline");
//  parentNode2.appendChild(content);


    var content4 = document.createElement("div");
    content4.setAttribute("class","row");
    parentNode2.appendChild(content4);

    var content6 = document.createElement("div");
    content6.setAttribute("class","dashboard-box margin-top-10");
    parentNode2.appendChild(content6);

    var div2=document.createElement("div");
    div2.setAttribute("class", "content with-padding padding-bottom-10");
    parentNode2.appendChild(div2);

    var form = document.createElement("form");
    form.setAttribute("method", "get");
    form.setAttribute("action", "sendWarning");
    form.setAttribute("id", "warning-form");
    content6.appendChild(form);

    //str.append("<form method='get' action='adminLoginValidation' id='login-form'>");




  var content2 =document.createElement("i");
  content2.setAttribute("class", "icon-feather-folder-plus");
  content2.innerText = "Warning Form";
  form.appendChild(content2);



  var div3=document.createElement("div");
  div3.setAttribute("class", "row");
  form.appendChild(div3);

  var div4=document.createElement("div");
  div4.setAttribute("class", "col-xl-4");
  form.appendChild(div4);

  var div5=document.createElement("div");
  div5.setAttribute("class", "submit-field");
  div5.innerText ="Incident ID:";
  form.appendChild(div5);

  var inp = document.createElement("input");
  //inp.setAttribute("type", "text");
  inp.setAttribute("id", "id");
  inp.setAttribute("class","with-border");
  inp.setAttribute("name","id");
  inp.setAttribute("value", "incidentInfo[0].noticeID")
  inp.value =  +incidentInfo[0].noticeID;
  getData(inp.value);
  console.log(inp.value);
  form.appendChild(inp);



  var div6=document.createElement("div");
  div6.setAttribute("class", "col-xl-4");
  form.appendChild(div6);

  var div7=document.createElement("div");
  div7.setAttribute("class", "submit-field");
  div7.innerText ="Employment ID";
  form.appendChild(div7);

  var inp2 = document.createElement("input");
  inp2.setAttribute("name", "employmentID");
  inp2.setAttribute("class","with-border");
  inp2.setAttribute("id","primero");
  form.appendChild(inp2);

  var div16=document.createElement("div");
  div16.setAttribute("class", "col-xl-4");
  form.appendChild(div16);

  var div17=document.createElement("div");
  div17.setAttribute("class", "submit-field");
  div17.innerText ="Caregiver ID";
  form.appendChild(div17);

  var inp12 = document.createElement("input");
  inp12.setAttribute("name", "caregiverID");
  inp12.setAttribute("class","with-border");
  inp12.setAttribute("id","segundo");
  form.appendChild(inp12);


  var div8 = document.createElement("div");
  div8.setAttribute("class","col-xl-12");
  form.appendChild(div8);

  var div9 = document.createElement("div");
  div9.setAttribute("class","submit-field");
  form.appendChild(div9);

  var h5 =document.createElement("h5");
  h5.innerText ="Subject: ";
  form.appendChild(h5);

  var text = document.createElement("input");
  text.setAttribute("text", "type");

  text.setAttribute("id", "subject");
  text.setAttribute("name", "subject");
  text.setAttribute("class","with-border");
  form.appendChild(text);

  var h5 =document.createElement("h5");
  h5.innerText ="Message to Caregiver: ";
  form.appendChild(h5);

  var text = document.createElement("input");
  text.setAttribute("text", "type");

  text.setAttribute("id", "message");
  text.setAttribute("name", "message");
  text.setAttribute("cols", "30");
  text.setAttribute("rows","5");
  text.setAttribute("class","with-border");
  form.appendChild(text);

  var warning = document.createElement("button");
  warning.setAttribute("type","submit" );
  warning.setAttribute("class", "button ripple-effect big margin-top-30");
  warning.innerText = "Send Warning to Caregiver " + incidentInfo[0].caregiverID+" ";
  var alert =document.createElement("i");
  alert.setAttribute("class","icon-feather-alert-octagon");
  warning.appendChild(alert);
  form.appendChild(warning);


}

function getData(notID, inp2, inp12) {
    var request = new XMLHttpRequest();
    request.open('GET', 'getEmploymentID?json&notID=' + notID, true);
    request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
        var employment = JSON.parse(request.responseText);
        console.log(request);

        document.getElementById("primero").value = employment.employmentID;
        document.getElementById("segundo").value = employment.caregiverID;

         console.log(employment);
         console.log(primero);

      }
    };
    request.send();
}
