<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Index</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<!-- Inclusione stile -->
<meta name="viewport" content="width=device-width, initial scale=1">
</head>
<body style="background-image:none">

	<!--<div> <p class="titolidiv">INSERIRE IL CONTAINER DI BENVENUTO</p></div>-->


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
					<li class="active"><a href="index.jsp">Home <span
							class="sr-only">(current)</span></a></li>
					<li><a href="DammiLocation">Eventi</a></li>
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

<div class="fade-in">
	<div id="carousel-example-generic" class="carousel slide"
		data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0"
				class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			<li data-target="#carousel-example-generic" data-slide-to="3"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
		<div class="item active "
				style="background-image: url(img/concerto.jpg)">
					<a href="info.jsp">
				<div class="carousel-caption">
					<h3 class="titolislider" style="color:white">BENVENUTI SU ALIVEMUSIC!</h3>
					<p class="titolislider" style="color:white">Clicca qui per scoprire tutte le funzionalità del sito!</p></a>
				</div>
			</div>
			<div class="item"
				style="background-image: url(http://www.impattosonoro.it/wp-content/themes/impatto-theme/images/Foo-Fighters-1.jpg)">
				<div class="carousel-caption">
					<h3 class="titolislider">Foo Fighters in Italia per un unica
						data, non farteli scappare!</h3>
					<p class="titolislider">Firenze 15 Giugno 2018, crea la tua
						Experience!</p>
				</div>
			</div>
			<div class="item"
				style="background-image: url(https://www.eventinbus.com/blog/wp-content/uploads/2017/12/pearl-jam-tour-2018.jpg);">
				<div class="carousel-caption">
					<h3 class="titolislider">Pearl Jam in Italia!</h3>
					<p class="titolislider">Milano,Padova e Roma il 22,24,26 Giugno
						2018, crea le tue Experience!</p>
				</div>
			</div>
			<!--<div class="item" style="background-image:url(img\gns.jpg);">-->
			<div class="item" style="background-image: url(http://www.damnprofilecovers.com/app/template/262.jpg);">
				<div class="carousel-caption">
					<h3 class="titolislider">Guns n' Roses in Italia!</h3>
					<p class="titolislider">Firenze 14 Giugno 2018, crea la tua
						Experience!</p>
				</div>
			</div>

		</div>


		<a class="left carousel-control" href="#carousel-example-generic"
			role="button" data-slide="prev"> <span
			class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#carousel-example-generic"
			role="button" data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>


	</div>
	</div>

<div>
<div class="dati">
<p class = "titolidiv">IN QUESTO MOMENTO CI SONO:</p>
<div class="row">
<div class="col-sm-4">
<p id="n_eventi" style="font-size:100px"></p>
<p style="font-weight:bold; color:#1a1a1a">Eventi Attivi</p>
</div>
<div class="col-sm-4">
<p id="n_experience" style="font-size:100px"></p>
<p style="font-weight:bold; color:#1a1a1a">Experience create</p>
</div>
<div class="col-sm-4">
<p id="n_utenti" style="font-size:100px"></p>
<p style="font-weight:bold; color:#1a1a1a">Utenti registrati</p>
</div>

</div>
</div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

 $(document).ready(function(){
	 
	   dati_homepage();
	});
 
	function dati_homepage(){
		$.ajax({
			type: "POST",
			url: "DatiHomepage",
			datatype: "text",
			async:true,

			success : function(dati)
			{
				var dati_array=dati.split(' ');
				
				$("#n_eventi").append(dati_array[1]);
				$("#n_experience").append(dati_array[0]);
				$("#n_utenti").append(dati_array[2]);
				
				
			},
			error : function()
			{
				alert("ERROR");
			}

		});
	}

</script>
<!-- 	<div class="container"></div> -->

<script src="js/bootstrap.min.js"></script>

	<!-- Importante inserire JQuery qui (prima del bootstrap), altrimenti non funziona -->


</body>

</html>
