

var url_string = window.location.href;
console.log(url_string);
//URl: JS class which allows me to get parameters from the actual url.
var url = new URL(url_string);
var caregiverID = url.searchParams.get("caregiverID");
console.log(caregiverID);
var caregiverfirstname = url.searchParams.get("firstname");
var caregiverlastname = url.searchParams.get("lastname");
var hourlyrate = url.searchParams.get("hourlyrate");
console.log(caregiverfirstname, caregiverlastname, hourlyrate);

intro = document.getElementById("offercaregiver");
intro.innerHTML = "Job Offer for " + caregiverlastname + " " + caregiverlastname;

salary = document.getElementById("salaryperweek");

function returnsalary (event) {
    console.log("Change of value ", event)

    hoursPerWeek = event.target.value;
    salary.value = hoursPerWeek * hourlyrate
}


function sendOffer () {

    var misDatos = { "caregiverID" : caregiverID, "startdate" : startdate.value, "hoursperweek" : hoursperweek.value};

    console.log(misDatos);

    function encodeFormData(data){
        return Object.keys(data)
            .map(key => encodeURIComponent(key) + '=' + encodeURIComponent(data[key])).join('&');
    }

    fetch('/TeresaSendOffer', {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        method: 'POST',
        body: encodeFormData( misDatos )
    }).then(function(response){
        response.json().then( (data)=>{
            //No me deja poner notok.

            console.log("La respuesta es", data );
            alert(data.description)
            if (data.notok == false) {
                window.location.href = "JobOffersDetail.html";
            } else {
                location.reload();
            }
        });
    })

}

