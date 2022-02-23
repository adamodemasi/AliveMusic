<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registrazione</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<!-- Inclusione stile -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/registrazione.js"></script>

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
		<div class="card card-container" style="min-width:800px">
		<p id="scritta_messaggio" style="margin-left:235px;margin-bottom:30px">MODULO REGISTRAZIONE</p>
		<form id="FormRegister" action="RegistraUtente" method="post" class="form-horizontal">
			<div class="form-group">
				<label for="nome" class="col-sm-3 control-label">Indirizzo
					Mail</label>
				<div class="col-sm-9">
					<input name="mail" type="text" id="nome" placeholder="Mail"
						class="form-control" autofocus required>
				</div>
			</div>
			<div class="form-group">
				<label for="nome" class="col-sm-3 control-label">Password</label>
				<div class="col-sm-9">
					<input name="password" type="password" id="nome" placeholder="Password"
						class="form-control" required>
				</div>
			</div>
			<div class="form-group">
				<label for="nome" class="col-sm-3 control-label">Nome</label>
				<div class="col-sm-9">
					<input name="nome" type="text" id="nome" placeholder="Nome"
						class="form-control" required>
				</div>
			</div>
			<div class="form-group">
				<label for="cognome" class="col-sm-3 control-label">Cognome</label>
				<div class="col-sm-9">
					<input name="cognome" type="text" id="cognome"
						placeholder="Cognome" class="form-control" required>
				</div>
			</div>
			<div class="form-group">
				<label for="nickname" class="col-sm-3 control-label">Nickname</label>
				<div class="col-sm-9">
					<input name="nickname" type="text" id="Nickname"
						placeholder="Nickname" class="form-control" required> <span
						class="help-block">Scegliere un nickname qualsiasi, es:
						Gabriele_94</span>
				</div>
			</div>

			<div class="form-group">
				<label for="citta" class="col-sm-3 control-label">Città di
					residenza</label>
				<div class="col-sm-9">
					<input name="citta" type="text" id="cittaResidenza"
						class="form-control" required>
				</div>
				<div class="form-group">
					<div class="col-sm-9 col-sm-offset-3">
						<div class="checkbox">
							<label> <input id="termini" type="checkbox">Accetto
								i <a data-toggle="modal" data-target="#mostra_termini">termini</a>
							</label>
						</div>
					</div>
				</div>
				<!-- /.form-group -->
			</div>
			<button id="registrati" class="btn btn-lg btn-primary btn-block btn-signin"
				type="submit">Registrati</button>
				<div id="facebook-result"></div>
				
			

			<div class="row omb_row-sm-offset-3 omb_socialButtons">
				<div class="btn-group">
					<!--<button type="button" class="btn btn-facebook">Registrati con Facebook</button>-->
				</div>
			</div>
		</form>
		</div>
		<!-- /form -->
	</div>
</div>

<div class="modal fade " id="mostra_termini">
		<div class="modal-dialog">
			<div class="modal-content">
				<div id="dati" class="modal-body">
					<a href="#" data-dismiss="modal" class="class pull-right"><span
						class="glyphicon glyphicon-remove"></span></a>

					<div>
						<p id="titolifadexp" style="color:#f2f2f2">Termini e condizioni:</p>
						<div style="border-radius:4px; background:white; width:100%; height:100%">
						<p style="color:#000000; margin:5px;">La consultazione e l'accesso al sito AliveMusic sono soggetti alle normative vigenti e implicano l'accettazione dei Termini e delle Condizioni di seguito riportati ("Termini e Condizioni"), che prevalgono anche rispetto a ogni altro diverso accordo intervenuto o che si ritenga intervenuto tra il visitatore del Sito (il "Visitatore") e Doc Generici s.r.l. (la "Società").

La Società si riserva di modificare in qualsiasi momento e senza preavviso i Termini e Condizioni qui riportati I Visitatori sono invitati a controllare periodicamente questa sezione del Sito per prendere visione delle eventuali modifiche. </p>
						</div>
						
					</div>
					<div>
						<p id="titolifadexp" style="color:#f2f2f2">Dati Personali:</p>
						<div style="border-radius:4px; background:white; width:100%; height:100%">
						<p style="color:#000000; margin:5px;">L'accesso e l'uso del Sito non comportano l'acquisizione di dati personali del Visitatore salvo ove espressamente segnalato (Area Riservata). In tali casi, il Visitatore sarà tenuto a prendere visione dell'informativa e –se del caso- ad esprimere il proprio consenso secondo quanto previsto dal D. Lgs. 30 giugno 2003 n. 196.

Tuttavia, le procedure e il sistema informatico preposto al funzionamento del Sito potranno acquisire, nel corso del loro normale funzionamento, alcuni dati la cui trasmissione è implicita nell'uso e protocolli di comunicazione Internet. Queste informazioni non sono raccolte per essere associate a interessati identificati ma per loro stessa natura potrebbero, attraverso elaborazioni ed associazioni con altri dati detenuti dalla Società o da terzi, permettere l'identificazione del Visitatore. In questa categoria di dati rientrano gli indirizzi IP o i nomi a dominio dei computer utilizzati dagli utenti che si connettono al Sito, gli indirizzi in URI (Uniform Resource Identifier) delle risorse richieste, l'ora di richiesta, il metodo di richiesta, la dimensione dei file ottenuto in risposta, il codice numerico contrassegnante la risposta del server e altri parametri relativi al sistema operativo e all'ambiente informatico del Visitatore. Questi dati potranno essere utilizzati dalla Società al solo fine di ricavarne informazioni assolutamente anonime e aggregate sull'uso del Sito allo scopo, ad es., di identificare le pagine o i contesti preferiti dagli utenti al fine di fornire servizi sempre più adeguati e verificarne il corretto funzionamento. Solo in caso di violazioni o ipotetici reati informatici che coinvolgano il Sito tali dati potranno essere utilizzati per l'accertamento di responsabilità.

Segnaliamo infine che l'accesso al Sito potrà comportare la registrazione, sul disco fisso del dispositivo del Visitatore (computer, tablet, palmare o telefono) di "cookies". I "cookies" sono piccoli file di testo che i siti visitati dall'utente inviano al suo terminale (solitamente al browser), dove vengono memorizzati per essere poi ritrasmessi agli stessi siti alla successiva visita del medesimo utente. Le informazioni salvate nei cookies resteranno anonime e non consentiranno l'identificazione del Visitatore.

Tuttavia, il Visitatore potrà in ogni momento richiedere la disattivazione dei cookies modificando le impostazioni del suo browser. La maggior parte dei browsers accetta i cookies automaticamente, ma è possibile cancellare i cookies già registrati ed evitare la registrazione di nuovi cookies selezionando l'apposita opzione (eventualmente consultando le istruzioni del browser). </p>
						</div>
						
					</div>


			

			</div>
		</div>
	</div>
</div>




<script type="text/javascript" src="js/registrazione.js"></script>
	<!-- Importante inserire JQuery qui (prima del bootstrap), altrimenti non funziona -->

</body>
</html>