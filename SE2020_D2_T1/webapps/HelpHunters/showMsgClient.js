function viewMsgClient() {
	var clientID = document.getElementById("cID_AJAX").value;
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if (document.getElementById("button_ajax1").innerHTML == "Client Info") {
                document.getElementById("msgClient").innerHTML = this.responseText;
                document.getElementById("button_ajax1").innerHTML = "Hide Client Info";           
            } else {
            document.getElementById("msgClient").innerHTML = " ";
            document.getElementById("button_ajax1").innerHTML = "Client Info";    
            }
        }
    };
    xhttp.open("GET", "MsgClient?clientID="+clientID+"", true);
    xhttp.send(); 
}