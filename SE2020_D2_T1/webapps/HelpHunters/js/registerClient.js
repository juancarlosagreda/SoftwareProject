function registerUser(){
    console.log("Iniciando el registro", " el nombre es ", fisrtname.value, " el apellido es ", lastname.value, " el género es ", female.checked, " y no es ", male.checked, " el email es ", email.value, " el password1 ", password1.value, " el password2 es ", password2.value);
    // Completar obtenciçón de valores
    var genero;

    if (female.checked == true){
        genero = 1;
    }else{
        genero = 2;
    }


    // crear json
    let misDatos = { "firstname": fisrtname.value, "lastname": lastname.value, "genero": genero, "email": email.value, "password1": password1.value, "password2": password2.value};

    console.log("los datoys que voy a enviar por ajax son ",misDatos);


    function encodeFormData(data){
        return Object.keys(data)
            .map(key => encodeURIComponent(key) + '=' + encodeURIComponent(data[key])).join('&');
    }
    console.log("Aqui se inicia la petición al servlet...");
    // Async Functions

    fetch('/ClientRegistration', {
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