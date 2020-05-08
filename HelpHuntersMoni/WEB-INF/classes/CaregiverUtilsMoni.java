public class CaregiverUtilsMoni {

	public static String header(String user) {
        StringBuilder str = new StringBuilder();
		
		str.append("<!doctype html>");
		str.append("<html lang='en'>");
		str.append("<head>");
		str.append("<title>Help Hunters</title>");
		str.append("<meta charset='utf-8'>");
		str.append("<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'>");
		str.append("<link rel='stylesheet' href='css/style.css'>");
		str.append("<link rel='stylesheet' href='css/colors/blue.css'>");
		str.append("</head>");
		str.append("<body class='gray'>");
		str.append("<div id='wrapper'>");
		str.append("<header id='header-container' class='fullwidth dashboard-header not-sticky'>");
		str.append("<div id='header'>");
		str.append("<div class='container'>");
		str.append("<div class='left-side'>");
		str.append("<div id='logo'>");
		str.append("<a href='indexMoni.html'><img src='images/logo.png' alt=''></a>");
		str.append("</div>");
		str.append("</div>");
		str.append("<div class='right-side'>");
		str.append("<div class='header-widget'>");
		str.append("<div class='header-notifications user-menu'>");
		str.append("<div class='header-notifications-trigger'>");
		str.append("<a href='#'><div class='user-avatar status-online'><img src='images/random.png' alt=''></div></a>");
		str.append("</div>");
		str.append("<div class='header-notifications-dropdown'>");
		str.append("<div class='user-status'>");
		str.append("<div class='user-details'>");
		str.append("<div class='user-avatar status-online'><img src='images/random.png' alt=''></div>");
		str.append("<div class='user-name'>");
		str.append(""+ user +"");
		str.append("</div>");
		str.append("</div>");
		str.append("</div>");
		str.append("<ul class='user-menu-small-nav'>");
		str.append("<li><a href='dashboard'><i class='icon-material-outline-dashboard'></i> Dashboard</a></li>");
		str.append("<li><a href='caregiverProfile'><i class='icon-material-outline-face'></i> View my Profile </a></li>");
		str.append("<li><a href='caregiverSettings'><i class='icon-material-outline-settings'></i> Settings</a></li>");
		str.append("<li><a href='LogoutMoni'><i class='icon-material-outline-power-settings-new'></i> Logout</a></li>");
		str.append("</ul>");
		str.append("</div>");
		str.append("</div>");
		str.append("</div>");
		str.append("<span class='mmenu-trigger'>");
		str.append("<button class='hamburger hamburger--collapse' type='button'>");
		str.append("<span class='hamburger-box'>");
		str.append("<span class='hamburger-inner'></span>");
		str.append("</span>");
		str.append("</button>");
		str.append("</span>");
		str.append("</div>");
		str.append("</div>");
		str.append("</div>");
		str.append("</header>");
		str.append("<div class='clearfix'></div>");
			
		return str.toString();
	}
	
	public static String footer(String user) {
        StringBuilder str = new StringBuilder();
		
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
		// div del sidebar y wrapper
		str.append("</div>");
		str.append("</div>");
		str.append("</div>");
		str.append("</div>");

		return str.toString();
    }
	
	public static String script(String user) {
        StringBuilder str = new StringBuilder();
		
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
		str.append("<script>");
		str.append("$('#snackbar-user-status label').click(function() {");
		str.append("Snackbar.show({");
		str.append("text: 'Your status has been changed!',");
		str.append("pos: 'bottom-center',");
		str.append("showAction: false,");
		str.append("actionText: 'Dismiss',");
		str.append("duration: 3000,");
		str.append("textColor: '#fff',");
		str.append("backgroundColor: '#383838'");
		str.append("});");
		str.append("});");
		str.append("</script>");
		str.append("<script src='js/chart.min.js'></script>");
		str.append("<script>");
		str.append("Chart.defaults.global.defaultFontFamily = 'Nunito';");
		str.append("Chart.defaults.global.defaultFontColor = '#888';");
		str.append("Chart.defaults.global.defaultFontSize = '14';");

		str.append("var ctx = document.getElementById('chart').getContext('2d');");

		str.append("var chart = new Chart(ctx, {");
		str.append("type: 'line',");

		str.append("data: {");
		str.append("labels: ['January', 'February', 'March', 'April', 'May', 'June'],");

		str.append("datasets: [{");
		str.append("label: 'Views',");
		str.append("backgroundColor: 'rgba(42,65,232,0.08)',");
		str.append("borderColor: '#2a41e8',");
		str.append("borderWidth: '3',");
		str.append("data: [196,132,215,362,210,252],");
		str.append("pointRadius: 5,");
		str.append("pointHoverRadius:5,");
		str.append("pointHitRadius: 10,");
		str.append("pointBackgroundColor: '#fff',");
		str.append("pointHoverBackgroundColor: '#fff',");
		str.append("pointBorderWidth: '2',");
		str.append("}]");
		str.append("},");
		str.append("options: {");

		str.append("layout: {");
		str.append("padding: 10,");
		str.append("},");

		str.append("legend: { display: false },");
		str.append("title:  { display: false },");

		str.append("scales: {");
		str.append("yAxes: [{");
		str.append("scaleLabel: {");
		str.append("display: false");
		str.append("},");
		str.append("gridLines: {");
		str.append(" borderDash: [6, 10],");
		str.append(" color: '#d8d8d8',");
		str.append(" lineWidth: 1,");
		str.append("},");
		str.append("}],");
		str.append("xAxes: [{");
		str.append("scaleLabel: { display: false },");
		str.append("gridLines:  { display: false },");
		str.append("}],");
		str.append("},");

		str.append("tooltips: {");
		str.append("backgroundColor: '#333',");
		str.append("titleFontSize: 13,");
		str.append("titleFontColor: '#fff',");
		str.append("bodyFontColor: '#fff',");
		str.append("bodyFontSize: 13,");
		str.append("displayColors: false,");
		str.append("xPadding: 10,");
		str.append("yPadding: 10,");
		str.append("intersect: false");
		str.append("}");
		str.append("},");


		str.append("});");
		str.append("</script>");
		str.append("</body>");
		str.append("</html>");


		return str.toString();
    }
   
}