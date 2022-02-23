<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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

<div class="fade-in">
	<div class="container">
		<div class="row profile">
			<div class="col-sm-3">
				<div class="profile-sidebar">
					<!-- sidebar utente -->
					<div class="profile-userpic">
						<img src="${user.avatar}" class="img-responsive" id="avatar"
							alt="">
					</div>
					<div class="profile-usertitle">
						<div class="profile-usertitle-name" id="user_nick">${user.nickname}</div>
						<div class="profile-usertitle-job">Rocker</div>
					</div>
					<!-- menu sidebar-->
					<div class="profile-usermenu">
						<ul id="profile" class="nav">
							<li class="active"><a href="#" id="buttonbio"> <i
									class="glyphicon glyphicon-home"></i> Bio
							</a></li>
							<li><a href="#" id="buttonaccount"> <i
									class="glyphicon glyphicon-user"></i> Impostazioni account
							</a></li>

							<li><a href="#" id="buttonexperience"> <i
									class="glyphicon glyphicon-road"></i> Experiences
							</a></li>
							<li><a href="#" id="buttonposta"> <i
									class="glyphicon glyphicon-envelope"></i> Posta
							</a></li>
							<li><a href="#" id="buttonprodotti"> <i
									class="glyphicon glyphicon-euro"></i> Prodotti
							</a></li>
							<li><a href="#" id="buttoninfo"> <i
									class="glyphicon glyphicon-pencil"></i> Info
							</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="container">
				<div class="col-sm-9 ">
					<div class="boxinfo">
						<div class="boxinfo--bio">${user.bio}</div>
						<div class="boxinfo--experience">
							<div style="min-height: 230px">
								<div class="cercaev">
									<p class="titolidiv" style="color:black">EXPERIENCES CREATE:<p>
									<ul id="myUL" class="experiencesCreate">
										<%-- 										<c:forEach var="e" items="${exp_create}"> --%>
										<!-- 											<li class="experience"> -->
										<%-- 											<a data-toggle="modal" data-target="#mostra_experience">${e.ID},${e.limite_adesione},${e.posti}</a></li> --%>
										<%-- 										</c:forEach> --%>
									</ul>
								</div>
								<div style="min-height: 230px">
									<p class="titolidiv" style="color:black">EXPERIENCES A CUI PARTECIPI:</p>
									<ul id="myUL" class="experiencesPartecipate">
										<%-- 										<c:forEach var="e" items="${exp_part}"> --%>
										<%-- 											<li><a>${e.ID},${e.limite_adesione},${e.posti}</a></li> --%>
										<%-- 										</c:forEach> --%>
									</ul>
								</div>
							</div>
						</div>
						<div class="boxinfo--prodotti">
							<div style="min-height: 230px">
								<p class="titolidiv" style="color:black">PRODOTTI REALIZZATI:</p>
								<ul id="myUL" class="prodottiCreati">
																			<%-- <c:forEach var="p" items="${prod_real}">
																				<li><a href="#">${p.nome}</a></li>
																			</c:forEach> --%>
								</ul>
							</div>
							<div style="min-height: 230px">
								<p class="titolidiv" style="color:black">PRODOTTI ACQUISTATI: </p>
								<ul id="myUL" class="prodottiAcquistati">
																			<%-- <c:forEach var="p" items="${acquisti}">
																				<li><a href="#">${p.nome}</a></li>
																			</c:forEach> --%>
								</ul>
							</div>
						</div>
						<div class="boxinfo--posta">
							<div style="min-height: 230px">
							<p class="titolidiv" style="color:black">POSTA IN ARRIVO </p>
								<ul id="myUL" class="messaggiRicevuti">
									<%-- 									<c:forEach var="m" items="${msg_ric}"> --%>
									<%-- 										<li>Mittente:${m.mittente}, Data arrivo: ${m.data_invio}, --%>
									<%-- 											Oggetto: ${m.oggetto}</li> --%>
									<%-- 									</c:forEach> --%>
								</ul>
							</div>
							<div style="min-height: 230px">
							<p class="titolidiv" style="color:black">POSTA IN USCITA </p>

								<ul id="myUL" class="messaggiInviati">
									<%-- 									<c:forEach var="m" items="${msg_inv}"> --%>
									<%-- 										<li>Mittente:${m.mittente}, Data arrivo: ${m.data_invio}, --%>
									<%-- 											Oggetto: ${m.oggetto}</li> --%>
									<%-- 									</c:forEach> --%>
								</ul>
							</div>

						</div>
						<div class="boxinfo--info">
							<div style="min-height: 115px"><p style="font-weight:bold; color:black">Nome:</p>${user.nome}</div>
							<div style="min-height: 115px"><p style="font-weight:bold; color:black">Cognome:</p>${user.cognome}</div>
							<c:if test="${utente_trovato.citta_residenza ne 'Inserisci la tua residenza per renderla visibile agli altri utenti' }">
							<div style="min-height: 115px"><p style="font-weight:bold; color:black">Città di residenza:</p>${user.citta_residenza}</div>
							</c:if>
						</div>
						<div class="boxinfo--impostazioni">
							<form method="post" action="" id="FormMail">
								<div class="cambiaemail">
									<div class="control-group">
										<label class="control-label" for="editemail">Email</label>
										<div class="controls">
											<input id="editemail" name="email" type="text"
												placeholder=${user.mail } class="input-xlarge" required />
										</div>
										<button type="submit" id="btnSave" name="btnSaveMail"
											class="btn btn-success">Salva</button>
									</div>
								</div>
							</form>

							<form method="post" action="" id="FormPassword">
								<div class="cambiapassword">
									<div class="control-group">
										<label class="control-label" for="editpassword">Cambia
											Password</label>
										<div class="controls">
											<input id="editpassword" name="password" type="text"
												placeholder=${user.password } class="input-xlarge" required>
										</div>
									</div>
									<button type="submit" id="btnSave" name="btnSave"
										class="btn btn-success">Salva</button>
								</div>
							</form>

							<form method="post" action="" id="FormResidenza">
								<div class="cambiaresidenza">
									<div class="control-group">
										<label class="control-label" for="editresidenza">Cambia
											Residenza</label>
										<div class="controls">
											<input id="editresidenza" name="residenza" type="text"
												placeholder=${user.citta_residenza } class="input-xlarge"
												required>
										</div>
									</div>
									<button id="btnSave" name="btnSave" class="btn btn-success">Salva</button>
								</div>
							</form>

							<form method="post" action="CambiaAvatar" enctype="multipart/form-data">
							<div class="cambiaimmagine">
								<div class="control-group">
									<label class="control-label" for="editimmagine">Cambia
										Immagine</label>
									<div class="controls">
										<input type="file" id="main-input" name="avatar"
											class="form-control form-input form-style-base">
										<h2 id="fake-btn"
											class="form-input fake-styled-btn text-center truncate"></h2>
									</div>
									<button id="btnSave" name="btnSave" class="btn btn-success">Salva</button>
								</div>
							</div>
							</form>
							
							<form method="post" action="AggiornaBio" id="FormBio">
								<div class="cambiabio">
									<div class="control-group">
										<label class="control-label" for="editbio">Cambia Bio</label>
										<div class="controls">
											<input id="editbio" name="bio" type="text"
												placeholder="${user.bio}" class="input-xlarge" required>
										</div>
										<button id="btnSave" name="btnSave" class="btn btn-success">Salva</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="utente_trovato_nick" style="display: none">${user.nickname}</div>
	</div>


	<!----------------------------------------------- MESSAGGIO ------------------------------>

	<div class="modal fade crea_experience" id="inviamessaggio">
		<div class="modal-dialog">

			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<a href="#" data-dismiss="modal" class="class pull-right"><span
							class="glyphicon glyphicon-remove"></span></a> <label for="Viaggio"
							class="col-sm-3 control-label"></label> <input type="text"
							id="nome" placeholder="Digita il testo del messaggio..."
							class="form-experience" autofocus>
					</div>

					<div class="form-group">
						<button type="submit" id="inviamess"
							class="btn btn-primary btn-block">Invia messaggio</button>
					</div>
				</form>
			</div>
		</div>
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
						<input style="color: black" type="datetime"
							id="dataLimiteAdesione" readonly>
					</div>

					<div>
						<p style="font-weight:bold">Descrizione:</p>
						<input style="color:black; width:100%; height:70px;" type="text" id="descrizione" readonly>
					</div>

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

<!-- -----------------------------------ELENCO MESSAGGI ----------------------------------------->

	<div class="modal fade " id="mostraMessaggio">
		<div class="modal-dialog">
			<div class="modal-content">
				<div id="dati" class="modal-body">
					<a href="#" data-dismiss="modal" class="class pull-right"><span
						class="glyphicon glyphicon-remove"></span></a>
					<div>
						<p id="utenteTipo" style="font-weight:bold">UTENTE</p>
						<div style="border-radius:4px; background:white; width:100%; height:100%;">
						<p id="utente" style="color:#000000; margin:5px;"></p></div>
					</div>
					<div>
						<p style="font-weight:bold">DATA</p>
						<div style="border-radius:4px; background:white; width:100%; height:100%;">
						<p id="data" style="color:#000000; margin:5px;"></p></div>
					</div>
					<div>
						<p style="font-weight:bold">OGGETTO</p>
						<div style="border-radius:4px; background:white; width:100%; height:100%;">
						<p id="oggetto" style="color:#000000; margin:5px;"></p></div>
					</div>
					<div>
						<p style="font-weight:bold">TESTO</p>
						<div style="border-radius:4px; background:white; width:100%; height:100%;">
						<p id="testo" style="color:#000000; margin:5px;"></p></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<!---------------------------------------------PRODOTTO---------------------------------------------->

	<!---------------------------------------------PRODOTTO CREATO---------------------------------------------->

	<div class="modal fade " id="mostraProdotto">
		<div class="modal-dialog">
			<div class="modal-content">
				<div id="dati" class="modal-body">
					<a href="#" data-dismiss="modal" class="class pull-right"><span
						class="glyphicon glyphicon-remove"></span></a>
					<div>
						<p style="font-weight:bold">Nome</p>
						<p id="id_p"></p>
					</div>
					<div>
						<p style="font-weight:bold">Prezzo</p>
						<p id="nome_p"></p>
					</div>
					<div>
						<p style="font-weight:bold">Descrizione</p>
						<p id="descrizione_p"></p>
					</div>
					<div>
						
						<p style="font-weight:bold">DESIGN</p>
						<img id="immagine_p" style="border:2px solid #ffffff; margin-top:5px; margin-left:5px; border-radius:50%;">
					</div>
					</div>
				</div>
			</div>
		</div>

	<!---------------------------------------------PRODOTTO ACQUISTATO---------------------------------------------->

	<div class="modal fade " id="mostraProdottoAcquistato">
		<div class="modal-dialog">
			<div class="modal-content">
				<div id="datiAcquisto" class="modal-body">
					<a href="#" data-dismiss="modal" class="class pull-right"><span
						class="glyphicon glyphicon-remove"></span></a>
					<div>
						<p style="font-weight:bold">Nome</p>
						<p id="id_p"></p>
					</div>
					<div>
						<p style="font-weight:bold">Prezzo</p>
						<p id="nome_p"></p>
					</div>
					<div>
						<p style="font-weight:bold">Descrizione</p>
						<p id="descrizione_p"></p>
					</div>
					<div>
						<p style="font-weight:bold">Taglia:</p>
						<p id="taglia_p"></p>
					</div>
					<div>
						<p style="font-weight:bold">Quantita</p>
						<p id="quantita_p"></p>
					</div>
					<div>
						<p style="font-weight:bold">Design</p>
						<img id="immagine_p" style="border:2px solid #ffffff; margin-top:5px; margin-left:5px; border-radius:50%;">
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="js/experience.js"></script>
	<script src="js/profilo.js"></script>
	<script src="js/profiloPersonale.js"></script>

</body>
</html>