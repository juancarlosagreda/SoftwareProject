function logout(){

    // Completar obtenciçón de valores
    console.log("Aqui se inicia la petición al servlet...");
    // Async Functions

    fetch('/Logout', {
        method: 'get',
    }).then(function(response){
        response.json().then( (data)=>{
            console.log("La respuesta es", data );

            window.location.href = '/index.html';
            alert(data.description);

        });
    })
}