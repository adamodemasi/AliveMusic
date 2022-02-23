<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Risultati Ricerca</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<!-- Inclusione stile -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>



<meta name="viewport" content="width=device-width, initial scale=1">
</head>
<body>

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
	
	<center><h2 class="titolislider" style="color:white">Utenti</p></center>
	<ul id="myUL" class="fade-in" style="min-height:540px;">
		<c:forEach var="u" items="${risultati_utenti}" varStatus="Loop">
			<li class="utente"> 
<%-- 			<img alt="" src="${u.avatar}"> --%>

			<c:if test="${ (not empty user.nickname) && (user.nickname eq u.nickname) }">
			<a href="profilo.jsp" id="${u.nickname}"><img src="${u.avatar}"  width="64px" height="64px" style="border-radius:50%;"> <p  style="display:inline-block; font-weight:bold; color:#1a1a1a;">Nickname:</p> ${u.nickname} <p  style="display:inline-block; font-weight:bold; color:#1a1a1a;">Nome:</p> ${u.nome} <p  style="display:inline-block; font-weight:bold; color:#1a1a1a;">Cognome:</p> ${u.cognome}</a>
			</c:if>
			
			<c:if test="${ (empty user.nickname) || (user.nickname ne u.nickname) }">
			<a href="profiloUtente.jsp" id="${u.nickname}"><img src="${u.avatar}" width="64px" height="64px" style="border-radius:50%; border:2px solid white;">  <p  style="display:inline-block; font-weight:bold; color:#1a1a1a;">Nickname:</p> ${u.nickname} <p  style="display:inline-block; font-weight:bold; color:#1a1a1a;">Nome:</p> ${u.nome} <p  style="display:inline-block; font-weight:bold; color:#1a1a1a;">Cognome:</p> ${u.cognome}</a>
			</c:if>
			
			</li>
		</c:forEach>
	</ul>
	
	<%-- <p>Artisti<p>
	<ul id="myUL" class="fade-in" style="min-height:540px;">
		<c:forEach var="a" items="${risultato_artisti}" varStatus="Loop">
			<li class="utente"> 
			<img alt="" src="${u.avatar}">
			<a href="passaArtista" id="${a.nome}"><img src="${a.immagine_band}"  width="64px" height="64px" style="border-radius:50%;"> <p  style="display:inline-block; font-weight:bold; color:#1a1a1a;">${a.nome} <p style="display:inline-block; font-weight:bold; color:#1a1a1a;"></p></a>
			
			</li>
		</c:forEach>
	</ul> --%>
	


	<!-- Importante inserire JQuery qui (prima del bootstrap), altrimenti non funziona -->

<script type="text/javascript" src="js/risultatiRicerca.js"></script>

</body>
</html>