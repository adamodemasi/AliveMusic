<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Eventi</title>
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
					<li class="active"><a href="info.jsp">Info</a></li>
				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
<div class="fade-in" style="background-color:white; border-radius:20px; margin-right:10px; margin-left:10px;">
<center><h4 style="color:#660000; padding:10px; font-weight: bold;">BENVENUTO SU ALIVEMUSIC.COM!</h4></center>
<p style="margin-left:10px; margin-right:10px;">Il sito MusicExp.com nasce con l’intento di offrirti un nuovo modo di vivere (e Condividere) l’emozione di partecipare ad un evento musicale dal vivo.
L’obiettivo è quello di offrirti la possibilità, una volta che ti sei registrato, di poter accedere a svariati servizi e funzionalità che 
ti guideranno attraverso la scelta,l’organizzazione del viaggio per i concerti delle tue band o artisti preferiti e attraverso 
l’utilizzo dello shop ufficiale del sito ti daremo anche la possibilità di finanziarti! </p>

<center><h4 style="color:#660000; padding:10px; font-weight: bold;">CONTENUTI E FUNZIONALITA’ DEL SITO WEB</h4></center>
			
<p style="margin-left:10px; margin-right:10px;">
<ul>Sezioni del sito: 
<li> Home Page: Troverai le ultime news e info varie.</li>
<li> Eventi: Troverai la lista e la mappa degli eventi e consentirà all’utente, dopo aver scelto un determinato evento dalla lista o dalla mappa, di visualizzare la lista delle Experience relative ad esso e la possibilità di crearne una nuova.</li>
<li> Artisti: Troverai la lista degli artisti, l’utente potrà visualizzare, selezionando un’artista, le relative info e gli eventi a cui partecipa.</li>
<li> Profilo, Login, Registrazione: Troverai la pagina relativa al profilo dell’utente loggato, in alternativa sarà possibile registrarsi o loggarsi tramite questa sezione.</li>
<li> Barra di Ricerca: Ti consentirà, se registrato, di utilizzare la barra di ricerca per cercare il profilo di un altro utente.</li>
<li> Shop: Troverai tutti i prodotti in vendita sul sito e le info riguardanti le modalità di creazione di un design relativo ad essi.</li>
<li> Info: Sei gia qui!</li>
</ul>
<center><h4 style="color:#660000; padding:10px; font-weight: bold;">INTERAZIONE TRA GLI UTENTI</h4></center>
<p style="margin-left:10px; margin-right:10px;">Ogni utente potrà visitare il profilo di un altro utente per visualizzarne le varie informazioni e i contenuti, inoltre avrà anche la possibilità di interagire con il suddetto utente attraverso lo scambio di messaggi.
Organizzazione delle Experience: Riguarda tutto quello che accade durante il “pre-concerto”, ovvero l’organizzazione in comitiva riguardante il viaggio, il pernottamento o l’esperienza del concerto stesso. L’utente potrà creare un Experience o aggregarsi ad una già esistente creata appunto da un altro utente.
Interazione con l’evento: Il sito offrirà all’utente la possibilità di interessarsi ad un evento, in modo tale da permettere all’utente stesso di rimanere aggiornato su tutte le Experience che verranno create per tale evento.
Shop: L’utente tramite lo shop avrà la possibilità di acquistare i prodotti (es. t-shirt, felpe, portachiavi) della propria band preferita ed inoltre avrà la possibilità di “finanziarsi” creando, dopo aver seguito la procedura contenuta all’interno dello shop, il design (immagine) di un prodotto, che successivamente dopo essere stato accettato da un moderatore verrà applicato a dei prodotti che verranno messi in vendita all’interno del sito. L’utente in base alle vendite di tali prodotti avrà la possibilità di ricevere dei soldi, dei buoni da utilizzare per l’acquisto dei biglietti relativi ai concerti o uno o più prodotti che sono già in vendita nel sito.
</p> 


<center><h4 style="color:#660000; padding:10px; font-weight: bold;">REQUISITI UTENTE</h4></center>

<p style="margin-left:10px; margin-right:10px;">Per usufruire dei servizi che offre il sito dovrai registrarti al sistema e completare il tuo profilo con le credenziali richieste (nome, cognome, nickname, password, mail), in alternativa ti vine offerta la possibilità di effettuare l’accesso tramite il tuo account social (Facebook, Google+). In un secondo momento, andando sulla pagina web del tuo profilo potrai arricchirlo inserendo la tua bio, la tua immagine del profilo e le tue preferenze.</p>

<center><h4 style="color:#660000; padding:10px; font-weight: bold;">CONTATTI E ASSISTENZA</h4></center>
<p style="margin-left:10px; margin-right:10px;">Per qualsiasi problema con il sito, con le Experience o per segnalazioni non esitare a contattarci!<p style="font-weight:bold; margin-left:10px;">musicexpinfo@gmail.com</p></p>

<center><h4 style="color:#660000; padding:10px; font-weight: bold;">CREAZIONE, MODIFICA, ELIMINAZIONE, PRODOTTO</h4></center>
<p style="margin-left:10px; margin-right:10px;">Per creare un'idea per i prodotti contenuti nel sito invia il tuo design alla mail riportata sopra. L'email dovrà avere come oggetto "Creazione Prodotto" e l'email dovrà essere quella con cui ti sei registrato al sito, il design deve essere tua al 100% e non deve contenere immagini o idea prese da internet, verranno accettate solo immagini con estensione .PNG di dimensione 500x500. Se la tua idea viene accettata il tuo design verrà applicato a dei prodotti contenuti sul sito e per ogni 50 prodotti venduti con il tuo design riceverai il 10% della somma totale delle vendite.</p>
<p style="margin-left:10px; margin-right:10px;">Per modificare un prodotto/design da te inserito invia una mail all'indirizzo indicato sopra (l'email deve essere quella con cui ti sei registrato al sito) con tutte le modifiche che vuoi apportare ad un determinato prodotto. L'email deve avere come oggetto "Modifica Prodotto"</p>
<p style="margin-left:10px; margin-right:10px;">Per eliminare un prodotto/design da te inserito invia una mail all'indirizzo indicato sopra (l'email deve essere quella con cui ti sei registrato al sito) indicato quale prodotto vorresti eliminare. L'email deve avere come oggetto "Elimina Prodotto"</p>
</div>
	<!-- Importante inserire JQuery qui (prima del bootstrap), altrimenti non funziona -->


</body>
</html>