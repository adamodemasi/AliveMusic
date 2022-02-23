<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Artisti</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="css/artistaSingolo.css">
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
					<li class="active"><a href="artisti.jsp">Artisti</a></li>
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
						<input name="cerca" type="text" class="form-control" placeholder="Digita">
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

<div class ="fade-in" style="margin-bottom:10px;">

	<div id="dati">

<!-- 		<p>IMMAGINE COPERTINA</p> -->
		<div><img id="immagineCopertina" style="width:100%; height:315px; border-radius:0px;"></div>
		
		
					<!-- sidebar utente -->
		<div class="container-fluid">
		<div class="row profile">
			<div class="col-sm-3">
				<div class="artist-sidebar">
		<center><img id="immagineBand" style="border:2px solid #ffffff; margin-top:5px; margin-left:5px; border-radius:50%;"></center>
		
		</div>
		<c:if test="${user.moderatore=='true'}">
		<center><a data-toggle="modal" data-target="#crea_evento" class="btn btn-success" id="btnSave" style="margin-top:10px;margin-right:18%"> Crea evento per l'artista</a></center>		
		</c:if>
		</div>
		
		<div class="col-sm-9" style="background:#ffffff; border:1px solid #660000; border-radius:10px; min-height:250px;">
		<h1 id="nome" style="color:#660000; text-shadow:2px 2px black; font-weight:bold;">name</h1>
		
		<div id="boxGenere" style="border-radius:0; background-color:transparent; width:100%; height:50px;">
		<p style="font-weight:bold; color:#000000;">Genere:</p>
		<p id="genere" style="float:left; color:#660000; font-weight:bold;"></p>
		</div>
		
		
		<div id="boxSito" style="background-color:transparent;">
		<p style="font-weight:bold; color:#000000; margin-top:10px; background-color:transparent;">Sito Web:</p>
		<a id="sitoWeb" href="${artista.sito_web}" style="float:left; color:#660000; font-weight:bold;"></a>
		
		</div>
		
		<br><div id="boxBio" style="background-color:transparent;">
		<p style="font-weight:bold; color:#000000; margin-top:10px; background-color:transparent;">Bio:</p>
		<a id="bio" href="${artista.bio}" style="float:left; color:#660000; font-weight:bold;"></a>
		
		</div></br>
		
		</div>
		</div>
		</div>

	</div>

	<!-- lista eventi ( per ora solo id evento poi modificare eventi.jsp con ajax deve prendere gli eventi in modo tale da poterla richiamare anche qui) -->

	<!-- 	<div class="cercaev"> -->
	<!-- 		<input type="text" id="myInput" onkeyup="myFunction()" -->
	<!-- 			placeholder="Cerca luogo, data, artista o location dell'evento.."> -->
	<center><h3 class="titolislider" style="color:white">Eventi</h3></center>
	<div style="margin:20px; border-radius:10px; background:#e6e6e6; border:1px solid #660000">
	<ul id="myUL" class="eventi" style="padding:10px;">

		<!-- 			<li class="event"><a href="#" >---</a></li> -->

	</ul>
	</div>

	<!-- 	</div> -->

	<!-- 	<script> -->
	<!-- // 		function myFunction() { -->
	<!-- // 			// Declare variables -->
	<!-- // 			var input, filter, ul, li, a, i; -->
	<!-- // 			input = document.getElementById('myInput'); -->
	<!-- // 			filter = input.value.toUpperCase(); -->
	<!-- // 			ul = document.getElementById("myUL"); -->
	<!-- // 			li = ul.getElementsByTagName('li'); -->

	<!-- // 			// Loop through all list items, and hide those who don't match the search query -->
	<!-- // 			for (i = 0; i < li.length; i++) { -->
	<!-- // 				a = li[i].getElementsByTagName("a")[0]; -->
	<!-- // 				if (a.innerHTML.toUpperCase().indexOf(filter) > -1) { -->
	<!-- // 					li[i].style.display = ""; -->
	<!-- // 				} else { -->
	<!-- // 					li[i].style.display = "none"; -->
	<!-- // 				} -->
	<!-- // 			} -->
	<!-- // 		} -->
	<!-- 	</script> -->




	<!-- 	lista experience -->

	<center><h3 class="titolislider" style="color:white">Experience</h3></center>
	<div class="boxinfoexperience" style=" border-radius:10px; background:#e6e6e6; border:1px solid #660000">


<!-- fare le 3 liste???? -->
		<ul id="myUL" class="experiences experiencesCreate experiencesPartecipate" style="padding:10px;">
			<!-- riempita da ajax -->

		</ul>

	</div>


	<div class="modal fade " id="mostra_experience">
		<div class="modal-dialog">
			<div class="modal-content">
				<div id="dati" class="modal-body">
					<a href="#" data-dismiss="modal" class="class pull-right"><span
						class="glyphicon glyphicon-remove"></span></a>
					<div>
						<p style="font-weight:bold">Posti rimasti:</p>
						<input style="color: black" type="number" name="posti" id="posti" readonly>
					</div>
					<div>
						<p style="font-weight:bold">Limite Adesione:</p>
						<input style="color: black" type="date"
							id="dataLimiteAdesione" readonly>
					</div>
					<div>
						<p style="font-weight:bold">Descrizione:</p>
					<input style="color:black; width:100%; height:70px;" type="text" id="descrizione" readonly>					</div>
					<div>
						<p style="font-weight:bold">Organizzatore:</p>
						<p id="organizzatore"></p>
					</div>
					<div>
						<p style="font-weight:bold">Partecipanti:</p>
						<ul id="partecipanti">
							<!-- 							ajax ci mette i li con i nick dei partecipanti -->
						</ul>
					</div>
					<button style="float: right" type="submit" id="partecipa"
						class="btn btn-success">Partecipa</button>
					<button style="float: right" type="submit" id="annulla_partecipa"
						class="btn btn-success">Annulla Partecipa</button>
					<button style="float: right" type="submit" id="modifica"
						class="btn btn-success">Modifica</button>
					<button style="float: right" type="submit" id="elimina"
						class="btn btn-success">Elimina</button>
				</div>
			</div>
		</div>
	</div>


<!-- ------------------------------------MODAL FADE PER EVENTO -->
<div class="modal fade crea_evento" id="crea_evento">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<a href="#" data-dismiss="modal" class="class pull-right"><span
						class="glyphicon glyphicon-remove"></span></a>

			<p id="scritta_messaggio" style="margin-left:200px">CREA UN EVENTO</p>
			<form id="Form" class="form-horizontal" role="form" method="post" action="CreaEvento">
            <div class="form-group" style="margin-top:7px">
              <label for="Posti" class="col-sm-3 control-label">Data</label>
              <div class="col-sm-9">
              
                <input id="data" type="date" name="data"
                  placeholder="GG/MM/AAAA" class="form-control" autofocus
                  required="required">
              </div>
              
              <label for="Posti" class="col-sm-3 control-label">Location</label>
              <div class="col-sm-9">
              		<select name="locat">
					    <option value="${selected}" selected>${selected}</option>
					    <c:forEach items="${locations}" var="loc">
					        <c:if test="${loc.nome != selected.nome}">
					            <option value="${loc.nome}">${loc.nome}</option>
					        </c:if>
					    </c:forEach>
					</select>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-9 col-sm-offset-3">
                <button id="buttoncreate" type="submit"
                  class="btn btn-primary btn-block">Crea Evento</button>
              </div>
            </div>
          </form>
				</div>
			</div>
		</div>
</div>



</div>


	<!-- Importante inserire JQuery qui (prima del bootstrap), altrimenti non funziona -->

	<!-- 	<script src="js/eventi.js"></script> -->
	<!-- 	<script src="js/experience.js"></script> -->
	<script src="js/artistaSingolo.js"></script>
	<script src="js/eventoExperiences.js"></script>
	<script src="js/experience.js"></script>

</body>
</html>