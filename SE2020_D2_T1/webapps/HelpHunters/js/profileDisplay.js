function profileDisplay(){

    // Completar obtenciçón de valores
    console.log("Aqui se inicia la petición al servlet...");
    // Async Functions

    fetch('/ClientProfile', {
        method: 'get',
    }).then(function(response){
        response.json().then( (data)=>{
            console.log("La respuesta es", data );

            clientName = document.getElementById("clientName");

            console.log("clientName html", clientName);
            //offersNumber = getElementById('offersNumber')
            clientName.innerHTML = data.name + " " + data.lastname + " ";
            offersNumber.innerHTML = data.employments;
            hoursNumber.innerHTML = data.hours;
            nameClient.innerHTML = data.name + " " + data.lastname;
            email.innerHTML = data.email;

            if (data.gender == 1){
                gender.innerHTML = 'lady'
            } else {
                gender.innerHTML = 'gentleman'
            }
        });
    })
}

profileDisplay();


