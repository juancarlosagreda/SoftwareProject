function logout(){

    // Completar obtenciçón de valores
    console.log("Aqui se inicia la petición al servlet...");
    // Async Functions

    fetch('/TeresaLogout', {
        method: 'get',
    }).then(function(response){
        response.json().then( (data)=>{
            console.log("La respuesta es", data );

            window.location.href = '/indexTeresa.html';
            alert(data.description);

        });
    })
}