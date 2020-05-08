var dataOffersGlobal = null;

function jobOffers(){

    // Completar obtenciçón de valores
    console.log("Aqui se inicia la petición al servlet...");
    // Async Functions

    fetch('/JobOffersDetail', {
        method: 'get',
    }).then(function(response){
        response.json().then( (data)=>{
            dataOffersGlobal = data;

            //console.log(texto);
            var html2 = "";

            for (var i = 0; i < data.length ;i++){

                record = data[i];


                if(record.status == 1){
                    var message = "Pending caregiver's response... <span class=\"icon-material-outline-access-time\">"
                }else if (record.status == 2){
                    var message = "Pending authorization by an administrator... <span class=\"icon-material-outline-access-time\">"
                }else if (record.status == 3){
                    var message = "Offer declined by the caregiver <span class=\"icon-material-outline-highlight-off\">"
                }else if (record.status == 4){
                    var message = "Authorized <span class=\"verified-badge\" title=\"Verified Employer\" data-tippy-placement=\"top\"></span>"
                }else if (record.status == 5){
                    var message = "This job offer was unauthorized <span class=\"icon-material-outline-highlight-off\">"
                }else if (record.status == 6) {
                    var message = "Terminated <span class=\"verified-badge\" title=\"Verified Employer\" data-tippy-placement=\"top\"></span>";
                }

                if (!record.startdate && record.status == 3) {
                    record.startdate = "Not Working";
                    record.enddate = "Declined offer";
                } else if (!record.startdate && record.status == 5) {
                    record.startdate = "Not Working";
                    record.enddate = "Declined authorization";
                } else if (!record.startdate && !record.dateauthorized) {
                    record.startdate = "Not Currently Working";
                    record.enddate = "Offer hasn't been authorized yet";
                } else if (record.startdate) {
                    if (!record.enddate) {
                        record.enddate = "Still taking place today";
                    }
                }


                console.log(record);
            var price = record.hoursperweek * record.caregiverhourlyrate;

            html2 +=`
                <!-- Job Listing -->
                <a onclick="openPopupDetail(${i})" class="job-listing">
                <!-- Job Listing Details -->
                    <div class="job-listing-details">
                    
                    <!-- Details -->
                    <div class="job-listing-description">
                        <h4 class="job-listing-company">${message} </h4>
                        <h3 class="job-listing-title">${record.caregiverfirstname} ${record.caregiverlastname}</h3>
                    </div>
                    </div>
                    
                    <div class="job-listing-footer">
                    <span class="bookmark-icon"></span>
                    <ul>
                        <li><i class="icon-material-outline-account-balance-wallet"></i> ${price} €</li>
                        <li><i class="icon-material-outline-access-time"></i> ${record.startdate} - ${record.enddate}</li>
                    </ul>
                    </div>
                </a>
                `

            }



            dinamicContent.innerHTML = html2;
            console.log("La respuesta es", data );

        });
    })
}

function openPopupDetail(i){

    record = dataOffersGlobal[i];

    console.log("Datos que pasan al popup: ", record);

    if(record.status == 1){
        var message = "Pending caregiver's response... <span class=\"icon-material-outline-access-time\">"
    }else if (record.status == 2){
        var message = "Pending authorization by an administrator... <span class=\"icon-material-outline-access-time\">"
    }else if (record.status == 3){
        var message = "Offer declined by the caregiver <span class=\"icon-material-outline-highlight-off\">"
    }else if (record.status == 4){
        var message = "Authorized <span class=\"icon-material-outline-check\">"
    }else if (record.status == 5){
        var message = "This job offer was unauthorized <span class=\"icon-material-outline-highlight-off\">";
    }else if (record.status == 6) {
        var message = "Terminated <span class=\"icon-material-outline-check\">";
    }

    if(record.caregivergender == 1){
        var gender = "Female";
    }else{
        var gender = "Male";
    }

    if (!record.startdate && record.status == 2){
        record.startdate = "-";
        record.enddate = "-";
        record.dateauthorized = "-";
    } else if (!record.startdate && !record.dateauthorized) {
        record.startdate = "-";
        record.dateauthorized = "The job has not been authorized yet";
        record.enddate = "-";
    } else if (record.startdate) {
        if (!record.enddate) {
            record.enddate = "Still taking place today";
        }
    }

    if (!record.dataauthorized){

    }

    var price = record.hoursperweek * record.caregiverhourlyrate;

    console.log(record);
    $.magnificPopup.open({
        items: {
            src: `
<div  class="container-card">

<div class="sign-in-form">

<div class="popup-tabs-container">

<div class="popup-tab-content" id="job">

<!-- Welcome Text -->
<div class="welcome-text">
<h3>Job Offer Details</h3>
</div>

<div class="col-xl-6 col-md-6">
<div class="section-headline margin-top-25 margin-bottom-12">
<h5>Caregiver</h5>
</div>
<p>${record.caregiverfirstname} ${record.caregiverlastname}</p>
</div>

<div class="col-xl-6 col-md-6">
<div class="section-headline margin-top-25 margin-bottom-12">
<h5>Status</h5>
</div>
<p>${message}</p>
</div>

<div class="col-xl-6 col-md-6">
<div class="section-headline margin-top-25 margin-bottom-12">
<h5>Gender</h5>
</div>
<p>${gender}</p>
</div>

<div class="col-xl-6 col-md-6">
<div class="section-headline margin-top-25 margin-bottom-12">
<h5>Hours per week</h5>
</div>
<p>${record.hoursperweek} hours per week</p>
</div>

<div class="col-xl-6 col-md-6">
<div class="section-headline margin-top-25 margin-bottom-12">
<h5>Total cost per week</h5>
</div>
<p>${price} € per week</p>
</div>

<div class="col-xl-6 col-md-6">
<div class="section-headline margin-top-25 margin-bottom-12">
<h5>Start Date</h5>
</div>
<p>${record.startdate}</p>
</div>

${ (record.status == 4 || record.status == 6) ? '<div class="col-xl-6 col-md-6"><div class="section-headline margin-top-25 margin-bottom-12"><h5>Date Authorized</h5></div><p>' + record.dateauthorized + '</p></div>': ""}

${ (record.status==6) ? '<div class="col-xl-6 col-md-6"><div class="section-headline margin-top-25 margin-bottom-12"><h5>End Date</h5></div><p>' + record.enddate + '</p></div>': ''}

${ (record.status==4) ? '<br><button onclick="terminateJob(' + record.employmentID + ')" class="margin-top-10 button full-width button-sliding-icon ripple-effect" >Terminate Job <i class="icon-material-outline-arrow-right-alt"></i></button>': ""}
        <!-- Button -->

<br><button onclick="writeNotice(${i})" class="margin-top-10 button full-width button-sliding-icon ripple-effect" >Write a notice to the company <i class="icon-material-outline-arrow-right-alt"></i></button>

        </div>

        </div>
        </div>
        </div>`, // can be a HTML string, jQuery object, or CSS selector

            type: 'inline'
        }
    });
}

function writeNotice(i){
    $.magnificPopup.close();

    record = dataOffersGlobal[i];

    $.magnificPopup.open({
        items: {
            src: ` <div  class="container-card popup-tab-content">
 
             <div class="welcome-text">
                <h3>We look forward to hearing from you!</h3>
             </div>
             
             <div><h4>Notice concerning ${record.caregiverfirstname} ${record.caregiverlastname}</h4></div>
            
            <div class="col-xl-6 col-md-6">
                <div class="section-headline margin-top-25 margin-bottom-12">
                    <h5>Subject</h5>
                </div>
                <input id="subject" class="with-border">
            </div>
            
            <div class="col-xl-6 col-md-6">
                <div class="section-headline margin-top-25 margin-bottom-12">
                    <h5>Notice</h5>
                </div>
                <textarea id="notice" type="text" placeholder="Notice"> </textarea>
            </div>
             
             
             <div class="radio" style="margin: 10px;">
                <input id="Complaint"  name="classification" type="radio">
                <label for="Complaint"><span class="radio-label"></span> Complaint</label>
                </div>
               <div class="radio" style="margin: 10px;">
                <input id="Comment"  name="classification" type="radio">
                <label for="Comment"><span class="radio-label"></span> Comment</label>
                </div>
               <div class="radio" style="margin: 10px;">
                <input id="Rather"  name="classification" type="radio" checked>
                <label for="Rather"><span class="radio-label"></span> Rather not say</label>
             </div>
             
             <br>
             <button onclick="sendNotice(${i})" class="margin-top-10 button full-width button-sliding-icon ripple-effect" > Send <i class="icon-material-outline-arrow-right-alt"></i></button>


            </div>`,
            type: 'inline'
        }});
}
function sendNotice(i){
    record = dataOffersGlobal[i];
    rather = document.getElementById("Rather").checked;
    comment = document.getElementById("Comment").checked;
    complaint = document.getElementById("Complaint").checked;

    subject = document.getElementById("subject").value;
    notice = document.getElementById("notice").value;



    if(rather){
        var classification = 0;
    }else if (complaint){
        var classification = 1;
    }else if (comment) {
        var classification = 2;
    }

    console.log(" classification ",classification );
    console.log("record", record);

    let misDatos = {"subject": subject, "notice": notice, "classification": classification,
        "employmentID": record.employmentID}

    function encodeFormData(data){
        return Object.keys(data)
            .map(key => encodeURIComponent(key) + '=' + encodeURIComponent(data[key])).join('&');
    }

    fetch('/FileNotice', {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        method: 'POST',
        body: encodeFormData( misDatos )
    }).then(function(response){
        response.json().then( (data)=>{
            //No me deja poner notok.
            if (data.notok == false){
                $.magnificPopup.close();
            }

            console.log("La respuesta es", data );
            alert(data.description)
        });
    })
}

jobOffers();

function terminateJob(employmentID) {

    console.log("employmentID", employmentID);

    let misDatos = {"employmentID": employmentID}

    function encodeFormData(data){
        return Object.keys(data)
            .map(key => encodeURIComponent(key) + '=' + encodeURIComponent(data[key])).join('&');
    }

    fetch('/TerminateJob', {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        method: 'POST',
        body: encodeFormData( misDatos )
    }).then(function(response){
        response.json().then( (data)=>{
            console.log("La respuesta es", data );
            alert(data.description);
            location.reload();
        });
    })
}

