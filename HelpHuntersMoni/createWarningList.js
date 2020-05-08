
function createWarningList() {

    var parentNode = document.getElementById('warningList'); 
    
    for(i = 0; i < warnings.length; i++){

        var listItem = document.createElement("li");
        parentNode.appendChild(listItem);

        var invoiceListItem = document.createElement("div");
        invoiceListItem.setAttribute("class", "invoice-list-item");
        listItem.appendChild(invoiceListItem);

        var strong = document.createElement("strong");
        strong.innerText=warnings[i].subject;
        invoiceListItem.appendChild(strong);

        var space1 = document.createElement("div");
        space1.setAttribute("class", "margin-top-8");
        strong.appendChild(space1);

        var ultraList = document.createElement("ul");
        space1.appendChild(ultraList);

        var listItem2 = document.createElement("li");
        listItem2.innerText=warnings[i].message;
        ultraList.appendChild(listItem2);

        var space = document.createElement("div");
        space.setAttribute("class", "margin-top-8");
        listItem2.appendChild(space);

        var listItem3 = document.createElement("li");
        listItem3.innerText=warnings[i].datecreated;
        space.appendChild(listItem3);

        var hide = document.createElement("div");
        hide.setAttribute("class", "margin-top-15");
        listItem3.appendChild(hide);

        if (warnings[i].hasbeenread == 0){

            var seen = document.createElement("a");
            seen.setAttribute("id", "seen");
            seen.setAttribute("href","seen?value="+1+"&noticeID="+ warnings[i].noticeID);
            seen.setAttribute("class", "button dark ripple-effect button-sliding-icon");
            seen.innerText="Seen ";
            seen.setAttribute("onclick", "myFunction()");
            hide.appendChild(seen);

            var check = document.createElement("i");
            check.setAttribute("class","icon-feather-check");
            seen.appendChild(check);

        } else if (warnings[i].hasbeenread == 1){

            var hide = document.createElement("div");
            hide.setAttribute("class", "margin-top-15");
            listItem3.appendChild(hide);

            
        }
        

    }  
}


function myFunction() {
  alert("This warning has been read");
}




createWarningList();


