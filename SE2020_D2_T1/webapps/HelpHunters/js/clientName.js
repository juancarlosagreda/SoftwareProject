async function getUserClientDataServlet(){
    let response = await fetch('/ClientProfile', {method: 'get'})
    let userData = response.json();
    return userData;
}

async function setDataProfile(){
    userData = await getUserClientDataServlet();
    console.log(userData);
    clientName = document.getElementById("clientName");
    clientName.innerHTML = userData.name + " " + userData.lastname + " ";

    //setUserDataIntoForm(userData);
}

setDataProfile();