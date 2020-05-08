function settings() {

    //First I need to look up my parent node, which I intentionally left blank
    //Look up the tag in chrome console
    var parentNode = document.getElementById('anchor');

      //We start up with the first box
      var firstBox = document.createElement("div");
      firstBox.setAttribute("class","content with-padding padding-bottom-0");
      parentNode.appendChild(firstBox);

        var row = document.createElement("div");
        row.setAttribute("class","row");
        firstBox.appendChild(row);

          var photoColumn = document.createElement("div");
          photoColumn.setAttribute("class","col-auto");
          row.appendChild(photoColumn);

            var avatar = document.createElement("div");
            avatar.setAttribute("class","avatar-wrapper");
            avatar.setAttribute("data-tippy-placement","bottom");
            //avatar.setAttribute("data-tippy data-original-title","Change Avatar");
            photoColumn.appendChild(avatar);

              var image = document.createElement("img");
              image.setAttribute("class","profile-pic");
              image.setAttribute("src","images/user-avatar-placeholder.png");
              //image.setAttribute("alt");
              avatar.appendChild(image);

              var uploadButton = document.createElement("div");
              uploadButton.setAttribute("class","upload-button");
              avatar.appendChild(uploadButton);

              var uploadFile = document.createElement("input");
              uploadFile.setAttribute("class","file-upload");
              uploadFile.setAttribute("type","file");
              uploadFile.setAttribute("accept","image/*");
              avatar.appendChild(uploadFile);

          var textColumn = document.createElement("div");
          textColumn.setAttribute("class","col");
          row.appendChild(textColumn);

            var nameRow = document.createElement("div");
            nameRow.setAttribute("class","row");
            textColumn.appendChild(nameRow);

              var rowType = document.createElement("div");
              rowType.setAttribute("class","col-xl-6");
              nameRow.appendChild(rowType);

                var submitField = document.createElement("div");
                submitField.setAttribute("class","submit-field");;
                rowType.appendChild(submitField);

                  var name = document.createElement("h5");
                  var textName = document.createTextNode("First Name")
                  name.appendChild(textName);
                  submitField.appendChild(name);

                  var inputName = document.createElement("input");
                  inputName.setAttribute("id","firstname");
                  inputName.setAttribute("type","text");
                  inputName.setAttribute("class","with-border");
                  inputName.setAttribute("value",adminData[0].firstname);
                  submitField.appendChild(inputName);

            var rowType2 = document.createElement("div");
            rowType2.setAttribute("class","col-xl-6");
            nameRow.appendChild(rowType2);

              var submitField2 = document.createElement("div");
              submitField2.setAttribute("class","submit-field");;
              rowType2.appendChild(submitField2);

                var lastname = document.createElement("h5");
                var textName = document.createTextNode("Last Name")
                lastname.appendChild(textName);
                submitField2.appendChild(lastname);

                var inputLastName = document.createElement("input");
                inputLastName.setAttribute("id","lastname");
                inputLastName.setAttribute("type","text");
                inputLastName.setAttribute("class","with-border");
                inputLastName.setAttribute("value",adminData[0].lastname);
                submitField2.appendChild(inputLastName);

          var rowType3 = document.createElement("div");
          rowType3.setAttribute("class","col-xl-6");
          nameRow.appendChild(rowType3);

              var submitField3 = document.createElement("div");
              submitField3.setAttribute("class","submit-field");;
              rowType3.appendChild(submitField3);

                  var accountTypeHeader = document.createElement("h5");
                  var accType = document.createTextNode("Account Type");
                  accountTypeHeader.appendChild(accType);
                  submitField3.appendChild(accountTypeHeader);

                  var accountType = document.createElement("div");
                  accountType.setAttribute("class","account-type");
                  submitField3.appendChild(accountType);

                    var rectangle = document.createElement("div");
                    accountType.appendChild(rectangle);

                      var inputButton = document.createElement("input");
                      inputButton.setAttribute("type","radio");
                      inputButton.setAttribute("name","account-type-radio");
                      inputButton.setAttribute("id","freelancer-radio");
                      inputButton.setAttribute("class","account-type-radio");
                      //inputButton.setAttribute("checked","");
                      rectangle.appendChild(inputButton);

                      var buttonLabel = document.createElement("label");
                      buttonLabel.setAttribute("for","freelancer-radio");
                      buttonLabel.setAttribute("class","ripple-effect-dark");
                      rectangle.appendChild(buttonLabel);

                          var accountIcon = document.createElement("i");
                          accountIcon.setAttribute("class","icon-material-account-circle");
                          accountIcon.innerHTML = "Administrator";
                          buttonLabel.appendChild(accountIcon);


          var rowType4 = document.createElement("div");
          rowType4.setAttribute("class","col-xl-6");
          nameRow.appendChild(rowType4);

            var submitField4 = document.createElement("div");
            submitField4.setAttribute("class","submit-field");;
            rowType4.appendChild(submitField4);

              var emailHeader = document.createElement("h5");
              var emailText = document.createTextNode("Email");
              emailHeader.appendChild(emailText);
              submitField4.appendChild(emailHeader);

              var inputEmail = document.createElement("input");
              inputEmail.setAttribute("id","email");
              inputEmail.setAttribute("type","text");
              inputEmail.setAttribute("class","with-border");
              inputEmail.setAttribute("value",adminData[0].email);
              submitField4.appendChild(inputEmail);

  //Now we want to create the password box.. we use another anchor we created
  var parentNode2 = document.getElementById("anchor2");

  var newBox = document.createElement("div");
  newBox.setAttribute("class","col-xl-12");
  parentNode2.appendChild(newBox);

    var boxDiv = document.createElement("div");
    boxDiv.setAttribute("class","dashboard-box");
    newBox.appendChild(boxDiv);

      var newHeadline = document.createElement("div");
      newHeadline.setAttribute("class","headline");
      boxDiv.appendChild(newHeadline);

        var headlineHeader = document.createElement("h3");
        var headlineIcon = document.createElement("i");
        headlineIcon.setAttribute("class","icon-material-outline-lock");
        headlineHeader.appendChild(headlineIcon);
        headlineHeader.innerHTML = "Password & Security";
        newHeadline.appendChild(headlineHeader);

    var content2 = document.createElement("div");
    content2.setAttribute("class","content with-padding");
    boxDiv.appendChild(content2);

    var pwRow = document.createElement("div");
    pwRow.setAttribute("class","row");
    content2.appendChild(pwRow);

      var pwCol1 = document.createElement("div");
      pwCol1.setAttribute("class","col-xl-4");
      pwRow.appendChild(pwCol1);

        var currentpw = document.createElement("div");
        currentpw.setAttribute("id","currentpwCont");
        currentpw.setAttribute("class","submit-field");
        pwCol1.appendChild(currentpw);

          var currentHeader = document.createElement("h5");
          currentHeader.innerHTML = "Current Password";
          currentpw.appendChild(currentHeader);

          var inputCurrent = document.createElement("input");
          inputCurrent.setAttribute("id","password");
          inputCurrent.setAttribute("type","password");
          inputCurrent.setAttribute("class","with-border");
          currentpw.appendChild(inputCurrent);

      var pwCol2 = document.createElement("div");
      pwCol2.setAttribute("class","col-xl-4");
      pwRow.appendChild(pwCol2);

        var newpw = document.createElement("div");
        newpw.setAttribute("id","newPwCont");
        newpw.setAttribute("class","submit-field");
        pwCol2.appendChild(newpw);

          var newHeader = document.createElement("h5");
          newHeader.innerHTML = "New Password";
          newpw.appendChild(newHeader);

          var inputNew = document.createElement("input");
          inputNew.setAttribute("id","newpassword");
          inputNew.setAttribute("type","password");
          inputNew.setAttribute("class","with-border");
          newpw.appendChild(inputNew);

      var pwCol3 = document.createElement("div");
      pwCol3.setAttribute("class","col-xl-4");
      pwRow.appendChild(pwCol3);

        var repeatpw = document.createElement("div");
        repeatpw.setAttribute("class","submit-field");
        pwCol3.appendChild(repeatpw);

          var repeatHeader = document.createElement("h5");
          repeatHeader.innerHTML = "Repeat New Password";
          repeatpw.appendChild(repeatHeader);

          var inputRepeat = document.createElement("input");
          inputRepeat.setAttribute("id","repeat");
          inputRepeat.setAttribute("type","password");
          inputRepeat.setAttribute("class","with-border");
          repeatpw.appendChild(inputRepeat);

      var pwCol4 = document.createElement("div");
      pwCol4.setAttribute("class","col-xl-12");
      pwRow.appendChild(pwCol4);

        var checkbox = document.createElement("div");
        checkbox.setAttribute("class","checkbox");
        pwCol4.appendChild(checkbox);

          var clickBox = document.createElement("input");
          clickBox.setAttribute("type","checkbox");
          clickBox.setAttribute("id","two-step");
          checkbox.appendChild(clickBox);

          var clickLabel = document.createElement("label");
          clickLabel.setAttribute("for","two-step");
          clickLabel.innerHTML = "Enable Two-Step Verification via Email";
          checkbox.appendChild(clickLabel);

          var checkIcon = document.createElement("span");
          checkIcon.setAttribute("class","checkbox-icon");
          clickLabel.appendChild(checkIcon);


    //Now we want to create the submit button (we append to parentNode2)
    var buttonContainer = document.createElement("div");
    buttonContainer.setAttribute("class","col-xl-12");
    parentNode2.appendChild(buttonContainer);

    var buttonLink = document.createElement("a");
    buttonLink.setAttribute("id","saveButton");
    buttonLink.setAttribute("href","#");
    buttonLink.setAttribute("class","button ripple-effect big margin-top-30");
    buttonLink.innerHTML = "Save Changes";
    buttonContainer.appendChild(buttonLink);
}


settings();
