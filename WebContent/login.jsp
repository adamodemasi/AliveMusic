<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<!-- Inclusione stile -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<meta name="viewport" content="width=device-width, initial scale=1">
</head>
<body>


	<nav class="navbar navbar-default navbar-fixed-top">
		<!--Il container-flud serve per farlo adattare a tutto lo schermo, se togliamo fluid di default e circa 1200-->
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp"><img
					src="img\alive.png"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp">Home <span class="sr-only">(current)</span></a></li>
					<li><a href="eventi.jsp">Eventi</a></li>
					<li><a href="artisti.jsp">Artisti</a></li>
					<li class="dropdown"><a id="user_nickname" href="#"
						class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false"><c:if
								test="${empty user.nickname}">Profilo</c:if>${user.nickname}<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<c:if test="${empty user.nickname}">
								<li><a href="login.jsp"> Accedi </a></li>
								<li><a href="register.jsp">Registrati</a></li>
							</c:if>
							<c:if test="${not empty user.nickname}">
								<li><a href="profilo.jsp">Visualizza profilo</a></li>
								<li><a href="Logout">Logout</a></li>
							</c:if>
						</ul></li>
				</ul>
				<form class="navbar-form navbar-left" method="get"
					action="RisultatiRicerca">
					<div class="form-group">
						<input name="cerca" type="text" class="form-control"
							placeholder="Digita">
					</div>
					<button type="submit" class="btn btn-default">Cerca</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="shop.jsp">Shop</a></li>
					<li><a href="info.jsp">Info</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<!--
    you can substitue the span of reauth email for a input with the email and
    include the remember me checkbox
    -->
    <div class="fade-in" id="login">
	<div class="container">
		<div class="card card-container">
			<!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
			<img id="profile-img" class="profile-img-card" src="img/avatar.png">
			<p id="profile-name" class="profile-name-card"></p>
			<form id="FormLogin" action="LoginCheck" method="post" class="form-signin">
				<span id="reauth-email" class="reauth-email"></span> <input
					name="mail" type="email" id="inputEmail" class="form-control"
					placeholder="Indirizzo Email" required autofocus> <input
					name="password" type="password" id="inputPassword"
					class="form-control" placeholder="Password" required>
				<button class="btn btn-lg btn-primary btn-block btn-signin"
					type="submit">Accedi</button>
			</form>
			<div class="row omb_row-sm-offset-3 omb_socialButtons">

				<a href="javascript:void(0);" onclick="fbLogin()" id="fbLink"><img
										class="product img-responsive center-block" id="prova"
										src="img/login-with-facebook.png" /></a>

									<div id="facebook-result"></div>

									<!-- Display user profile data -->
									<div id="userData"></div>

									<!-- <!-- Display login status -->
									<div id="status"></div>

			</div>
			<div>
				<p>
					<a data-toggle="modal" data-target="#pass_recovery"> Password
						dimenticata?</a>
				</p>
			</div>
		</div>
	</div>
	
		

	<div class="modal fade crea_experience" id="pass_recovery">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<a href="#" data-dismiss="modal" class="class pull-right"><span
						class="glyphicon glyphicon-remove"></span></a>
					<p id="scritta_messaggio" style="margin-left:175px">RECUPERO PASSWORD</p>
					<form id="FormPass" class="form-horizontal" role="form" action="CambiaPassword">
						<div class="form-group">
							<label for="mail" class="col-sm-3 control-label">Indirizzo
								mail</label>
							<div class="col-sm-9">
								<input type="text" name="mail"
									placeholder="Inserisci un indirizzo mail per il recupero della password"
									class="form-control" autofocus>
							</div>
						</div>
						<div id="facebook-results"></div>
						<div class="form-group">
							<div class="col-sm-9 col-sm-offset-3">
								<button id="buttonpassword" type="submit"
									class="btn btn-primary btn-block">Invia Password</button>
							</div>
						</div>
					</form>
				</div>
				</div>

			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="js/login.js"></script>

	<script type="text/javascript">   
 $(document).ready(function(){

 $("#emailsbagliata").hide();
 $("#emailgiusta").hide();


    $("#buttonpassword").click(function(){
         $("#emailsbagliata").show();
         $("#emailgiusta").show();
         $("#pass_recovery").modal();
         });
  
});
 </script>
 
 <script>
 window.fbAsyncInit = function() {
	    FB.init({
	      appId            : '1384416715014329',
	      autoLogAppEvents : true,
	      xfbml            : true,
	      version          : 'v2.12'
	    });
	  };

	  (function(d, s, id){
	     var js, fjs = d.getElementsByTagName(s)[0];
	     if (d.getElementById(id)) {return;}
	     js = d.createElement(s); js.id = id;
	     js.src = "https://connect.facebook.net/en_US/sdk.js";
	     fjs.parentNode.insertBefore(js, fjs);
	   }(document, 'script', 'facebook-jssdk'));
	  
	  
function fbLogin() {
			FB
					.login(
							function(response) {
								if (response.authResponse) {
									// Get and display the user profile data
									salvaDatiInDB(response);
									// getFbUserData();
								} else {
									document.getElementById('status').innerHTML = 'Email/Password errati o hai annullato il login.';
								}
							}, {
								scope : 'email'
							});
		}
		
function salvaDatiInDB(response) {
	FB.api('/me',{
						locale : 'en_US',
						fields : 'id,first_name,last_name,email,link,gender,locale,picture'
					},
					function(response) {
						
						var nick=(response.first_name+response.last_name);
						
						var avt="http://graph.facebook.com/" + response.id + "/picture?type=normal";
						
						var stringa_vuota="";
						
						var jsonUtente = {
							nickname : nick,
							nome: response.first_name,
							cognome:response.last_name,
							citta_residenza:"Inserisci la tua residenza per renderla visibile agli altri utenti",
							mail:response.email,
							password : "utenteFacebook1",												
							bio:stringa_vuota,
							avatar:avt,
							moderatore:false
						}
						$.ajax({
									type : "POST",
									url : "LoginDaFacebookServlet",
									datatype : "json",
									data : {
										jsonUtenteFacebook : JSON.stringify(jsonUtente),
									},
									success : function(data) {
										$("#facebook-result")
												.html(
														"<div class=\"alert alert-success\" role=\"alert\">  Stai per entrare nel sito.</div>");
										setTimeout(
												function() {
													window.location.href = "profilo.jsp";
												}, 2000);
									},
									error : function() {
										$("#facebook-result")
												.html(
														"<div class=\"alert alert-danger\" role=\"alert\">Login fallito.</div>");
										setTimeout(function() {
										}, 2000);
									}
								});
		
					});
}
		
		
</script>
</body>
</html>