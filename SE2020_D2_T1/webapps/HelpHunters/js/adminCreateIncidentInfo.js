




function CreateIncidentInfo() {

    //First I need to look up my parent node, which I intentionally left blank
    //Look up the tag in chrome console
    var parentNode = document.getElementById('anchor');

    var content = document.createElement("div");
   content.setAttribute("class","headline");
    parentNode.appendChild(content);

    var h3 = document.createElement("h3");
    content.appendChild(h3);

    var person = document.createElement("i");
    person.setAttribute("class","icon-material-outline-account-circle");
    person.innerText =" Post by Client:" + incidentInfo[0].clientID + " "
    h3.appendChild(person);

    var caregiv = document.createElement("i");
    caregiv.setAttribute("class","icon-material-outline-account-circle");
    caregiv.innerText =" About Caregiver:" + incidentInfo[0].careID
    h3.appendChild(caregiv);

    var des = document.createElement("div");
    des.setAttribute("class","headline");
    parentNode.appendChild(des);

    var descr = document.createElement("div");
    descr.setAttribute("class","job-listing");
    des.appendChild(descr);

    var h2 = document.createElement("h2");
    h2.innerText ="Description: "
    descr.appendChild(h2);

    var message = document.createElement("h4");
    message.innerText = incidentInfo[0].message
    h2.appendChild(message);


        //First we set up the divisions


        //Now we do the footer
        var jobListingFooter = document.createElement("div");
        jobListingFooter.setAttribute("class","headline");

        parentNode.appendChild(jobListingFooter);

        var size = document.createElement("div");
        size.setAttribute("class","col-xl-12");
        jobListingFooter.appendChild(size);

      /*  var center = document.createElement("center");
        size.appendChild(center);

        var warning = document.createElement("a");
        warning.setAttribute("href","#" );
        warning.setAttribute("class", "button dark ripple-effect big margin-top-30");
        warining.setAttribute("id", "botonwarning")
        warning.innerText = "Send Warning to Caregiver " + incidentInfo[0].caregiverID+" ";
        var alert =document.createElement("i");
        alert.setAttribute("class","icon-feather-alert-octagon");
        warning.appendChild(alert);
        center.appendChild(warning);*/


        var in1 = document.createElement("INPUT");
        in1.setAttribute("type", 'submit');
        in1.setAttribute("value", "Create a warning for this caregiver");
        in1.setAttribute("name", 'Save');
        in1.setAttribute("id","botonwarning");
        jobListingFooter.appendChild(in1);



}


CreateIncidentInfo();
