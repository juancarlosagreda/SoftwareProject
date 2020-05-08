public class adminUtils {

  public static String header(String title, String notifications, int administratorID, String firstname,
    String lastname, String email, String password) {
        StringBuilder str = new StringBuilder();

        str.append("<!doctype html>");
        str.append("<html lang='en'>");
        str.append("<head>");
        str.append("<!-- Basic Page Needs -->");
        str.append("<title>Help Hunters</title>");
        str.append("<meta charset='utf-8'>");
        str.append("<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'>");
        str.append("<!-- CSS -->");
        str.append("<link rel='stylesheet' href='css/style.css'>");
        str.append("<link rel='stylesheet' href='css/colors/blue.css'>");
        str.append("</head>");
        str.append("<body class='gray'>");
        str.append("<!-- Wrapper -->");
        str.append("<div id='wrapper'>");
        str.append("<!-- Header Container-->");
        str.append("<header id='header-container' class='fullwidth dashboard-header not-sticky'>");
          str.append("<!-- Header -->");
          str.append("<div id='header'>");
            str.append("<div class='container'>");
              str.append("<!-- Left Side Content -->");
              str.append("<div class='left-side'>");
                str.append("<!-- Logo -->");
                str.append("<div id='logo'>");
                  str.append("<a href='INICIO.html'><img src='images/logo.png' alt=''></a>");
                str.append("</div>");
                str.append("<!-- User Menu -->");
                str.append("<div class='header-widget'>");
                  str.append("<!-- Messages -->");
                  str.append("<div class='header-notifications user-menu'>");
                    str.append("<div class='header-notifications-trigger'>");
                      str.append("<a href='#'><div class='user-avatar status-online'><img src='images/user-avatar-small-01.jpg' alt=''></div></a>");
                    str.append("</div>");
                    str.append("<!-- Dropdown -->");
                    str.append("<div class='header-notifications-dropdown'>");
                      str.append("<!-- User Status -->");
                      str.append("<div class='user-status'>");
                        str.append("<!-- User Name / Avatar -->");
                        str.append("<div class='user-details'>");
                          str.append("<div class='user-avatar status-online'><img src='images/user-avatar-small-01.jpg' alt=''></div>");
                          str.append("<div class='user-name'>");
                            str.append("Admin Name <span>Administrator</span>");
                          str.append("</div>");
                        str.append("</div>");
                    str.append("</div>");
                    str.append("<ul class='user-menu-small-nav'>");
                      str.append("<li><a href='adminSettings'><i class='icon-material-outline-settings'></i> Settings</a></li>");
                      str.append("<li><a href='adminLogin'><i class='icon-material-outline-power-settings-new'></i> Logout</a></li>");
                    str.append("</ul>");
                    str.append("</div>");
                  str.append("</div>");
                str.append("</div>");
                str.append("<!-- User Menu / End -->");
                str.append("<!-- Mobile Navigation Button -->");
                str.append("<span class='mmenu-trigger'>");
                  str.append("<button class='hamburger hamburger--collapse' type='button'>");
                    str.append("<span class='hamburger-box'>");
                      str.append("<span class='hamburger-inner'></span>");
                    str.append("</span>");
                  str.append("</button>");
                str.append("</span>");
              str.append("</div>");
              str.append("<!-- Right Side Content / End -->");
            str.append("</div>");
          str.append("</div>");
          str.append("<!-- Header / End -->");
        str.append("</header>");
        str.append("<div class='clearfix'></div>");
        str.append("<!-- Header Container / End -->");
        str.append("<!-- Dashboard Container -->");
        str.append("<div class='dashboard-container'>");
          str.append("<!-- Dashboard Sidebar -->");
          str.append("<div class='dashboard-sidebar'>");
            str.append("<div class='dashboard-sidebar-inner' data-simplebar>");
            str.append("  <div class='dashboard-nav-container'>");
                str.append("<!-- Navigation -->");
                str.append("<div class='dashboard-nav'>");
                  str.append("<div class='dashboard-nav-inner'>");
                    str.append("<ul data-submenu-title='Start'>");
                      str.append("<li><a href='adminNotice'><i class='icon-material-outline-dashboard'></i> Notices</a></li>");
                      str.append("<li><a href='adminClient'><i class='icon-material-outline-question-answer'></i> Clients </a></li>");
                      str.append("<li><a href='adminCaregiver'><i class='icon-material-outline-star-border'></i> Caregivers</a></li>");
                      str.append("<li><a href='adminIncident'><i class='icon-material-outline-rate-review'></i> Incidences</a></li>");
                    str.append("</ul>");
                    str.append("<ul data-submenu-title='Analytics'>");
                      str.append("<li><a href='adminIncidenceAnalytics'><i class='icon-material-outline-dashboard'></i> Incidences</a></li>");
                      str.append("<li><a href='adminClientAnalytics'><i class='icon-material-outline-question-answer'></i> Clients </a></li>");
                      str.append("<li><a href='adminCaregiverAnalytics'><i class='icon-material-outline-star-border'></i> Caregivers</a></li>");
                      str.append("<li><a href='adminJobAnalytics'><i class='icon-material-outline-rate-review'></i> Jobs</a></li>");
                    str.append("</ul>");
                    str.append("<ul data-submenu-title='Account'>");
                      str.append("<li><a href='adminSettings'><i class='icon-material-outline-settings'></i> Settings</a></li>");
                      str.append("<li><a href='adminLogin'><i class='icon-material-outline-power-settings-new'></i> Logout</a></li>");
                    str.append("</ul>");
                  str.append("</div>");
                str.append("</div>");
                str.append("<!-- Navigation / End -->");
              str.append("</div>");
            str.append("</div>");
          str.append("</div>");
          str.append("<!-- Dashboard Sidebar / End -->");
          str.append("<!-- Dashboard Content -->");
          str.append("<div class='dashboard-content-container' data-simplebar>");
            str.append("<div class='dashboard-content-inner' >");
            str.append("  <!-- Dashboard Headline -->");
              str.append("<div class='dashboard-headline'>");
                str.append("<h3>" + title + "</h3>");
                str.append("<div class='col-xl-6 col-md-6' id='filter'>");
                  str.append("<div class='section-headline margin-top-45 margin-bottom-12' >");
                  str.append("</div>");
                  //str.append("<select class='selectpicker' data-live-search='true' onchange='firstAjax.js' id='options'>");
                  //  str.append("<option>Male</option>");
                //    str.append("<option>Male</option>");
                  //  str.append("<option>Female</option>");
                  str.append("</select>");
                str.append("</div>");
                str.append("<!-- Breadcrumbs -->");
                str.append("<nav id='breadcrumbs' class='dark'>");
                  str.append("<ul>");
                    str.append("<li><a href='adminRequest'>Dashboard</a></li>");
                    str.append("<li>" +  title + "</li>");
                  str.append("</ul>");
                str.append("</nav>");
              str.append("</div>");
              str.append("<!-- Row -->");
              str.append("<div class='row' id='anchor2'>");
                str.append("<!-- Dashboard Box -->");
                str.append("<div class='col-xl-12' id='anchor2paula'>");
                  str.append("<div class='dashboard-box margin-top-0' id='anchor'>");
                    str.append("<!-- Headline -->");
                    str.append("<div class='headline' >");
                      str.append("<h3><i class='icon-material-outline-business-center'></i> "+ notifications + "</h3>");
                    str.append("</div>");
                    //str.append("<div class='headline' >");
                   //str.append("</div>");
                    // str.append("<div class='content with-padding padding-bottom-0'>");
                    // str.append("</div>");
                  str.append("</div>");
                str.append("</div>");
              str.append("</div>");
              str.append("<!-- Row / End -->");
              str.append("<!-- Footer -->");
              str.append("<div class='dashboard-footer-spacer'></div>");
              str.append("<div class='small-footer margin-top-15'>");
                str.append("<div class='small-footer-copyrights'>");
                  str.append("© 2020 <strong>Help Hunters</strong>. All Rights Reserved.");
                str.append("</div>");
                str.append("<ul class='footer-social-links'>");
                  str.append("<li>");
                    str.append("<a href='#' title='Facebook' data-tippy-placement='top'>");
                      str.append("<i class='icon-brand-facebook-f'></i>");
                    str.append("</a>");
                  str.append("</li>");
                  str.append("<li>");
                    str.append("<a href='#' title='Twitter' data-tippy-placement='top'>");
                      str.append("<i class='icon-brand-twitter'></i>");
                    str.append("</a>");
                  str.append("</li>");
                  str.append("<li>");
                    str.append("<a href='#' title='Google Plus' data-tippy-placement='top'>");
                      str.append("<i class='icon-brand-google-plus-g'></i>");
                    str.append("</a>");
                  str.append("</li>");
                  str.append("<li>");
                    str.append("<a href='#' title='LinkedIn' data-tippy-placement='top'>");
                      str.append("<i class='icon-brand-linkedin-in'></i>");
                    str.append("</a>");
                  str.append("</li>");
                str.append("</ul>");
                str.append("<div class='clearfix'></div>");
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
        str.append("<script src='js/jquery-3.4.1.min.js'></script>");
        str.append("<script src='js/jquery-migrate-3.1.0.min.js'></script>");
        str.append("<script src='js/mmenu.min.js'></script>");
        str.append("<script src='js/tippy.all.min.js'></script>");
        str.append("<script src='js/simplebar.min.js'></script>");
        str.append("<script src='js/bootstrap-slider.min.js'></script>");
        str.append("<script src='js/bootstrap-select.min.js'></script>");
        str.append("<script src='js/snackbar.js'></script>");
        str.append("<script src='js/clipboard.min.js'></script>");
        str.append("<script src='js/counterup.min.js'></script>");
        str.append("<script src='js/magnific-popup.min.js'></script>");
        str.append("<script src='js/slick.min.js'></script>");
        str.append("<script src='js/custom.js'></script>");
        str.append("<!-- Snackbar // documentation: https://www.polonel.com/snackbar/ -->");
        str.append("<script src='js/chart.min.js'></script>");

        return str.toString();
    }

    public static String footer() {
        StringBuilder str = new StringBuilder();
        str.append("</body>");
        str.append("</html>");

        return str.toString();
    }
}
