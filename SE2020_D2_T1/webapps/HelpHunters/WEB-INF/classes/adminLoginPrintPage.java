
public class adminLoginPrintPage {

    public static String printLogin() {
        StringBuilder str = new StringBuilder();

        str.append("<!DOCTYPE html>");
        str.append("<html lang='en'>");
        str.append("<head>");
        str.append("<!-- Basic Page Needs -->");
        str.append("<title>Help Hunters");
        str.append("</title>");
        str.append("<meta charset='utf-8'>");
        str.append("<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'>");
        str.append("<!-- CSS-->");
        str.append("<link rel='stylesheet' href='css/style.css'>");
        str.append("<link rel='stylesheet' href='css/colors/blue.css'>");
        str.append("</head>");
        str.append("<body class='gray'>");
        str.append("<!-- Wrapper -->");
        str.append("<div id='wrapper'>");
              str.append("<!-- Left Side Content -->");
              str.append("<div class='left-side'>");
              str.append("<!-- Logo -->");
                str.append("<div id='logo'>");
                  str.append("<a href='INICIO.html'>");
                  str.append("<img src='images/logo.png' alt=''>");
                  str.append("</a>");
                str.append("</div>");
        str.append("<!-- Welcome Text -->");
        str.append("<div class='welcome-text'>");
          str.append("<h3>We're glad to see you again!");
          str.append("</h3>");
        str.append("</div>");
        str.append("<!-- Account Type -->");
        str.append("<!-- Form -->");
        str.append("<form method='get' action='adminLoginValidation' id='login-form'>");
        str.append("<center>  ");
        str.append("<div class='col-xl-6'>");
            str.append("<i class='icon-material-baseline-mail-outline'>");
            str.append("</i>");
            str.append("<input type='text' class='input-text with-border' name='emailaddress' id='emailaddress' placeholder='Email Address' required/>");
          str.append("</div>");
          str.append("<div class='col-xl-6'>");
            str.append("<i class='icon-material-outline-lock'>");
            str.append("</i>");
            str.append("<input type='password' class='input-text with-border' name='password' id='password' placeholder='Password' required/>");
          str.append("</div>");
          str.append("<a href='#' class='forgot-password'>Forgot Password?");
          str.append("</a>");
        str.append("</form>");
        str.append("<!-- Button -->");
        str.append("<div class='col-xl-6'>");
        str.append("<button class='button full-width button-sliding-icon ripple-effect' type='submit' form='login-form'>Log In");
        str.append("<i class='icon-material-outline-arrow-right-alt'>");
        str.append("</i>");
        str.append("</a>");
        str.append("</button>");
        str.append("</div>");
        str.append("<!-- Footer -->");
        str.append("<div class='dashboard-footer-spacer'>");
        str.append("</div>");
        str.append("<div class='small-footer margin-top-15'>");
          str.append("<div class='small-footer-copyrights'> © 2019 ");
          str.append("<strong>Help Hunters");
            str.append("</strong>. All Rights Reserved.");
          str.append("</div>");
          str.append("<ul class='footer-social-links'>");
            str.append("<li>");
              str.append("<a href='#' title='Facebook' data-tippy-placement='top'>");
                str.append("<i class='icon-brand-facebook-f'>");
                str.append("</i>");
              str.append("</a>");
            str.append("</li>");
            str.append("<li>");
              str.append("<a href='#' title='Twitter' data-tippy-placement='top'>");
                str.append("<i class='icon-brand-twitter'>");
                str.append("</i>");
              str.append("</a>");
            str.append("</li>");
            str.append("<li>");
              str.append("<a href='#' title='Google Plus' data-tippy-placement='top'>");
                str.append("<i class='icon-brand-google-plus-g'>");
                str.append("</i>");
              str.append("</a>");
            str.append("</li>");
            str.append("<li>");
              str.append("<a href='#' title='LinkedIn' data-tippy-placement='top'>");
                str.append("<i class='icon-brand-linkedin-in'>");
                str.append("</i>");
              str.append("</a>");
            str.append("</li>");
          str.append("</ul>");
          str.append("<div class='clearfix'>");
          str.append("</div>");
        str.append("</div>");
        str.append("<!-- Footer / End -->");
        str.append("</div>");
        str.append("</div>");
        str.append("<!-- Dashboard Content / End -->");
        str.append("</div>");
        str.append("<!-- Dashboard Container / End -->");
        str.append("</div>");
        str.append("<!-- Wrapper / End -->");
        str.append("<!-- Scripts-->");
        str.append("<script src='js/jquery-3.4.1.min.js'>");
        str.append("</script>");
        str.append("<script src='js/jquery-migrate-3.1.0.min.js'>");
        str.append("</script>");
        str.append("<script src='js/mmenu.min.js'>");
        str.append("</script>");
        str.append("<script src='js/tippy.all.min.js'>");
        str.append("</script>");
        str.append("<script src='js/simplebar.min.js'>");
        str.append("</script>");
        str.append("<script src='js/bootstrap-slider.min.js'>");
        str.append("</script>");
        str.append("<script src='js/bootstrap-select.min.js'>");
        str.append("</script>");
        str.append("<script src='js/snackbar.js'>");
        str.append("</script>");
        str.append("<script src='js/clipboard.min.js'>");
        str.append("</script>");
        str.append("<script src='js/counterup.min.js'>");
        str.append("</script>");
        str.append("<script src='js/magnific-popup.min.js'>");
        str.append("</script>");
        str.append("<script src='js/slick.min.js'>");
        str.append("</script>");
        str.append("<script src='js/custom.js'>");
        str.append("</script>");
        str.append("<!-- Snackbar // documentation: https://www.polonel.com/snackbar/ -->");
        str.append("<script src='snackbarLabel.js'>");
        str.append("</script>");
        str.append("</body>");
        str.append("</html>");

        return str.toString();

      }

      public static String printLoginError() {
          StringBuilder str = new StringBuilder();

          str.append("<!DOCTYPE html>");
          str.append("<html lang='en'>");
          str.append("<head>");
          str.append("<!-- Basic Page Needs -->");
          str.append("<title>Help Hunters");
          str.append("</title>");
          str.append("<meta charset='utf-8'>");
          str.append("<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'>");
          str.append("<!-- CSS-->");
          str.append("<link rel='stylesheet' href='css/style.css'>");
          str.append("<link rel='stylesheet' href='css/colors/blue.css'>");
          str.append("</head>");
          str.append("<body class='gray'>");
          str.append("<!-- Wrapper -->");
          str.append("<div id='wrapper'>");
                str.append("<!-- Left Side Content -->");
                str.append("<div class='left-side'>");
                str.append("<!-- Logo -->");
                  str.append("<div id='logo'>");
                    str.append("<a href='INICIO.html'>");
                    str.append("<img src='images/logo.png' alt=''>");
                    str.append("</a>");
                  str.append("</div>");
          str.append("<!-- Welcome Text -->");
          str.append("<div class='welcome-text'>");
            str.append("<h3>We're glad to see you again!");
            str.append("</h3>");
          str.append("</div>");
          str.append("<!-- Account Type -->");
          str.append("<!-- Form -->");
          str.append("<form method='get' action='adminLoginValidation' id='login-form'>");
          str.append("<center>  ");
          str.append("<div class='col-xl-6'>");
              str.append("<i class='icon-material-baseline-mail-outline'>");
              str.append("</i>");
              str.append("<input type='text' class='input-text with-border' name='emailaddress' id='emailaddress' placeholder='Email Address' required/>");
            str.append("</div>");
            str.append("<div class='col-xl-6'>");
              str.append("<i class='icon-material-outline-lock'>");
              str.append("</i>");
              str.append("<input type='password' class='input-text with-border' name='password' id='password' placeholder='Password' required/>");
            str.append("</div>");
            str.append("<a href='#' class='forgot-password'>Forgot Password?");
            str.append("</a>");
          str.append("</form>");
          str.append("<!-- Button -->");
          str.append("<div class='col-xl-6'>");
          str.append("<button class='button full-width button-sliding-icon ripple-effect' type='submit' form='login-form'>Log In");
          str.append("<i class='icon-material-outline-arrow-right-alt'>");
          str.append("</i>");
          str.append("</a>");
          str.append("</button>");
          str.append("</div>");
          str.append("<!-- Footer -->");
          str.append("<div class='dashboard-footer-spacer'>");
          str.append("</div>");
          str.append("<div class='small-footer margin-top-15'>");
            str.append("<div class='small-footer-copyrights'> © 2019 ");
            str.append("<strong>Help Hunters");
              str.append("</strong>. All Rights Reserved.");
            str.append("</div>");
            str.append("<ul class='footer-social-links'>");
              str.append("<li>");
                str.append("<a href='#' title='Facebook' data-tippy-placement='top'>");
                  str.append("<i class='icon-brand-facebook-f'>");
                  str.append("</i>");
                str.append("</a>");
              str.append("</li>");
              str.append("<li>");
                str.append("<a href='#' title='Twitter' data-tippy-placement='top'>");
                  str.append("<i class='icon-brand-twitter'>");
                  str.append("</i>");
                str.append("</a>");
              str.append("</li>");
              str.append("<li>");
                str.append("<a href='#' title='Google Plus' data-tippy-placement='top'>");
                  str.append("<i class='icon-brand-google-plus-g'>");
                  str.append("</i>");
                str.append("</a>");
              str.append("</li>");
              str.append("<li>");
                str.append("<a href='#' title='LinkedIn' data-tippy-placement='top'>");
                  str.append("<i class='icon-brand-linkedin-in'>");
                  str.append("</i>");
                str.append("</a>");
              str.append("</li>");
            str.append("</ul>");
            str.append("<div class='clearfix'>");
            str.append("</div>");
          str.append("</div>");
          str.append("<!-- Footer / End -->");
          str.append("</div>");
          str.append("</div>");
          str.append("<!-- Dashboard Content / End -->");
          str.append("</div>");
          str.append("<!-- Dashboard Container / End -->");
          str.append("</div>");
          str.append("<!-- Wrapper / End -->");
          str.append("<!-- Scripts-->");
          str.append("<script src='js/jquery-3.4.1.min.js'>");
          str.append("</script>");
          str.append("<script src='js/jquery-migrate-3.1.0.min.js'>");
          str.append("</script>");
          str.append("<script src='js/mmenu.min.js'>");
          str.append("</script>");
          str.append("<script src='js/tippy.all.min.js'>");
          str.append("</script>");
          str.append("<script src='js/simplebar.min.js'>");
          str.append("</script>");
          str.append("<script src='js/bootstrap-slider.min.js'>");
          str.append("</script>");
          str.append("<script src='js/bootstrap-select.min.js'>");
          str.append("</script>");
          str.append("<script src='js/snackbar.js'>");
          str.append("</script>");
          str.append("<script src='js/clipboard.min.js'>");
          str.append("</script>");
          str.append("<script src='js/counterup.min.js'>");
          str.append("</script>");
          str.append("<script src='js/magnific-popup.min.js'>");
          str.append("</script>");
          str.append("<script src='js/slick.min.js'>");
          str.append("</script>");
          str.append("<script src='js/custom.js'>");
          str.append("</script>");
          str.append("<!-- Snackbar // documentation: https://www.polonel.com/snackbar/ -->");
          str.append("<script src='snackbarLabel.js'>");
          str.append("</script>");
          str.append("<script>alert(\"The username or password is incorrect.\")</script>");
          str.append("</body>");
          str.append("</html>");

          return str.toString();

        }
}
