var dataNoticesGlobal = null;

console.log("Entro al javascript noticeList");

noticeList();

function noticeList () {

    // Completar obtenciçón de valores
    console.log("Aqui se inicia la petición al servlet...");

    fetch('/TeresaNoticeList', {
        method: 'get',
    }).then(function(response){
        response.json().then( (data)=> {

            console.log("Data que regresa mi servlet: ", data);

            dataNoticesGlobal = data;

            var html = "";

            for (var i = 0; i < data.length; i++) {
                record = data[i];

                html += `
                     <!-- Job Listing -->
                    ${ (record.datereplied) && (record.replyhasbeenread == 0) ? '<a onclick="readNotice(' + record.noticeID + ', ' + i + ')" class="job-listing">' : '<a onclick="openPopupNotice(' + i + ')" class="job-listing">'}
                    
                    <!-- Job Listing Details -->
                    <div class="job-listing-details">
                    ${ (record.datereplied) && (record.replyhasbeenread == 0) ? '<span id="notRead' + i + '" style="color: blue; font-size: 20px; padding-right: 15px;" class="icon-material-outline-feedback"></span>': ''}
                    
                    <!-- Details -->
                    <div class="job-listing-description">
                    <h3 class="job-listing-title">Notice ${record.subject} concerning ${record.caregiverfirstname} ${record.caregiverlastname}</h3>
                    
                    <!-- Job Listing Footer -->
                    <div class="job-listing-footer">
                    <ul>
                    <li><i class="icon-material-outline-access-time"></i> Date created: ${record.datecreated}</li>
                    ${ (record.datereplied) ? '<li><i class="icon-material-outline-access-time"></i> Date replied: ' + record.datereplied + '</li>' : '<li><i class="icon-material-outline-access-time"></i> Date replied: Notice has not been replied yet </li>'}
                    </ul>
                    </div>
                    </div>
                    
                    <!-- Bookmark -->
                    <span class="bookmark-icon"></span>
                    </div>
                    </a>
                `
            }

            dinamicContent.innerHTML = html;
            console.log("La respuesta es", data);

       });
    })

}

function readNotice (noticeID, i) {

    var misDatos = {"noticeID": noticeID};


    postDataToServlet("/TeresaNoticeList", misDatos);


    console.log("Notice has been change to read");
    var id = "notRead"+i;
    console.log("el id a modifcar es", id);

    document.getElementById(id).style.display = "none";

    openPopupNotice(i);
}

async function postDataToServlet(servlet, json) {

    function encodeFormData(data){
        return Object.keys(data)
            .map(key => encodeURIComponent(key) + '=' + encodeURIComponent(data[key])).join('&');
    }

    console.log("Aqui se inicia la petición al servlet...");

    let response = await  fetch(servlet, {
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}, method: 'POST', body: encodeFormData( json )
    });
    return await response.json();
}

function openPopupNotice (i) {

    record = dataNoticesGlobal[i];

    if (!record.datereplied) {
        $.magnificPopup.open({
            items: {
                src: `
                <div  class="container-card">
                
                <div class="sign-in-form">
                
                <div class="popup-tabs-container">
                
                <div class="popup-tab-content" id="notice">
                
                <!-- Welcome Text -->
                <div class="welcome-text">
                <h3>Notice Details</h3>
                </div>
                
                <div class="col-xl-6 col-md-6">
                <div class="section-headline margin-top-25 margin-bottom-12">
                <h5>Subject:</h5>
                </div>
                <p>${record.subject}</p>
                </div>
                
                <div class="col-xl-6 col-md-6">
                <div class="section-headline margin-top-25 margin-bottom-12">
                <h5>This notice concerns caregiver:</h5>
                </div>
                <p>${record.caregiverfirstname} ${record.caregiverlastname}</p>
                </div>
                
                <div class="col-xl-6 col-md-6">
                <div class="section-headline margin-top-25 margin-bottom-12">
                <h5>Notice's Text:</h5>
                </div>
                <p>${record.message}</p>
                </div>
                
                <div class="col-xl-6 col-md-6">
                <div class="section-headline margin-top-25 margin-bottom-12">
                <h5>Date created:</h5>
                </div>
                <p>${record.datecreated}</p>
                </div>
    
                </div>
        
                </div>
                </div>
                </div>`, // can be a HTML string, jQuery object, or CSS selector

                type: 'inline'
            }
        });

    } else {
        $.magnificPopup.open({
            items: {
                src: `
                <div  class="container-card">
                
                <div class="sign-in-form">
                
                <div class="popup-tabs-container">
                
                <div class="popup-tab-content" id="notice">
                
                <!-- Welcome Text -->
                <div class="welcome-text">
                <h3>Notice Details</h3>
                </div>
                
                <div class="col-xl-6 col-md-6">
                <div class="section-headline margin-top-25 margin-bottom-12">
                <h5>Subject:</h5>
                </div>
                <p>${record.subject}</p>
                </div>
                
                <div class="col-xl-6 col-md-6">
                <div class="section-headline margin-top-25 margin-bottom-12">
                <h5>This notice concerns caregiver:</h5>
                </div>
                <p>${record.caregiverfirstname} ${record.caregiverlastname}</p>
                </div>
                
                <div class="col-xl-6 col-md-6">
                <div class="section-headline margin-top-25 margin-bottom-12">
                <h5>Notice's Text:</h5>
                </div>
                <p>${record.message}</p>
                </div>
                
                <div class="col-xl-6 col-md-6">
                <div class="section-headline margin-top-25 margin-bottom-12">
                <h5>Date created:</h5>
                </div>
                <p>${record.datecreated}</p>
                </div>
                
                <div class="col-xl-6 col-md-6">
                <div class="section-headline margin-top-25 margin-bottom-12">
                <h5>This notice was replied by:</h5>
                </div>
                <p>${record.administratorfirstname} ${record.administratorlastname}</p>
                </div>
                
                <div class="col-xl-6 col-md-6">
                <div class="section-headline margin-top-25 margin-bottom-12">
                <h5>Reply:</h5>
                </div>
                <p>${record.reply}</p>
                </div>
                
                <div class="col-xl-6 col-md-6">
                <div class="section-headline margin-top-25 margin-bottom-12">
                <h5>Date replied:</h5>
                </div>
                <p>${record.datereplied}</p>
                </div>
    
                </div>
        
                </div>
                </div>
                </div>`, // can be a HTML string, jQuery object, or CSS selector

                type: 'inline'
            }
        });
    }
}

