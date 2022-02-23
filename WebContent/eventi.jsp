<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Eventi</title>

<!-- Inclusione stile -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="style.css">
<!-- Inclusione stile -->

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
				<a class="navbar-brand" href="index.jsp"><img src="img\alive.png"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp">Home <span class="sr-only">(current)</span></a></li>
					<li class="active"><a href="eventi.jsp">Eventi</a></li>
					<li><a href="artisti.jsp">Artisti</a></li>
					<li class="dropdown"><a id="user_nickname" href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><c:if test="${empty user.nickname}">Profilo</c:if>${user.nickname}<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<c:if test="${empty user.nickname}">
								<li><a href="login.jsp">
									Accedi
								</a></li>
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

<!------------- NON TOCCARE ----------------------------------------------------->
<!---------------- MAPPA -------------------------------------------------------->

	<div class="containermaps">
		<div id="map" class="maps"></div>
	</div>
	<div id="map"></div>
	
	<script>
		var click = 0;
		function initMap() {

			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 6, center : {
					lat : 41.9102415,
					lng : 12.3959119
				} //inizialmente centrato su Roma
			});

			infoWindow = new google.maps.InfoWindow;

			// Try HTML5 geolocation.
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					var pos = {
						lat : position.coords.latitude,
						lng : position.coords.longitude
					};

					infoWindow.setPosition(pos);
					infoWindow.setContent('Your<br>position');
					infoWindow.open(map);
					map.setCenter(pos);
				}, function() {
					handleLocationError(true, infoWindow, map.getCenter());
				});
			} else { // Browser doesn't support Geolocation
				handleLocationError(false, infoWindow, map.getCenter());
			}

			// prendo location da servlet
			var locations = '${locations}';
			var coords = [];

			var lts = locations.split(", ");
			
			// array nomi locations
			var name = [];
			var nomi = lts[0].substring(10, lts.length - 2);
			name.push(nomi);
			
			for(var i = 5; i < lts.length; i += 5){
				nomi = lts[i].substring(9, lts.length - 2);
				name.push(nomi);
			}
			
			//array coordinate
			for (var j = 3; j < lts.length; j += 4) {
				
				var lat = parseFloat(lts[j++]);

				var lng = lts[j].substring(0, lts[j].length - 1);

				if (j + 1 == lts.length) {
					var lng = lts[j].substring(0, lts[j].length - 2);
					lng = parseFloat(lng);
				} else {
					lng = parseFloat(lng);
				}

				var latLng = new google.maps.LatLng(lat, lng);
				coords.push(latLng);

 			}
			
// ------- coordinates - names map
			var objectKey = {};
			
			for(var i = 0; i < lts.length; i++){
			
				var test = {'coords':coords[i], 'location':name[i]};
			
				objectKey[coords[i]] = test;
			}

			// Add markers to the map.
			var markers = coords.map(function(loc) {

			var locName;
			
			if(loc in objectKey){
				locName = objectKey[loc].location;
			}
				
			var infowindow = new google.maps.InfoWindow({

	 			content : '<p id="locName">' + locName +
	 				'<div id="locName"><button type="button" id="locName" input type="submit" onclick="eventiLocation()">Eventi</button></div></p>'
	
			});

				var marker = new google.maps.Marker({
					position : loc,
					animation : google.maps.Animation.DROP,
					infowindow : infowindow
				});

//click on map cause closing of infowindow
				google.maps.event.addListener(map, 'click', function(){
					if(click > 0){
						restoreEvents();
						click = 0;
					}
					hideAllInfoWindows(map);
				});
				
//one marker shown at a time				
				google.maps.event.addListener(marker, 'click', function() {
					hideAllInfoWindows(map);
					infowindow.open(map, marker);

					if(click > 0){
						restoreEvents();
						click = 0;
					}
				});
				
//at infowindow close, update page with all events
				google.maps.event.addListener(infowindow, 'closeclick', function(){
					
					if(click > 0){
						restoreEvents();
						click = 0;
					}
				});

				return marker;
			});
			
// hide multiples infoWindows from map
			function hideAllInfoWindows(map) {
				markers.forEach(function(marker) {
					marker.infowindow.close(map, marker);
				});
			}
			
// Add a marker clusterer to manage the markers.
			var markerCluster = new MarkerClusterer(
					map, markers, {
						imagePath : 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'
					});
		}
		
		function restoreEvents(){
			$('#myUL').html("");
			$('#myUL1').html("");
			
			dammiTuttiEventi();
			showEvents();

		}
		
		function showEvents(){
			$("#myUL li.event a").on('click', function(e) {
				$("#myUL li.event a").not(this).removeClass("selected");
				$(this).addClass("selected");
				$("#creaExperience").show();
				$("#idEvento").val($(this).attr("id"));
				dammiExperienceAssociate($(this).attr("id"));
				e.preventDefault();
			});
		}
		
		function eventiLocation() {
			click = 1;
			var y = document.getElementById("locName").innerHTML;

			$.ajax({
				type : "GET",
				url : "DammiEventiLocation",
				datatype : 'json',
				data : { "location" : y	},
				async : false,

				success : function(eventiJson) {
					$('#myUL').html("");
					$('#myUL1').html("");

					var objs = JSON.parse(eventiJson);

					var eventList = $("ul.eventi");
					eventList.empty();

					for (var i = 0; i < objs.length; i += 3) {

						var li = $('<li/>').addClass("event").appendTo(eventList);
						var artisti = "";

						for (var ai = 0; ai < objs[i + 2].length; ai++) {
							if (!artisti == "")
								artisti += ", ";

							artisti += (objs[i + 2][ai].nome);
						}

						var aaa = $('<a/>').text(
								"data=" + new Date(objs[i].data) + "-> " + objs[i].data
										+ " location=" + objs[i + 1].nome + " artisti="
										+ artisti)
							.attr("id", objs[i].ID).appendTo(li);

					}
					showEvents();
				},
				
				error : function() {
					alert("ERROR");
				}
			});
		}
		
		function handleLocationError(browserHasGeolocation, infoWindow, pos) {
			infoWindow.setPosition(pos);
			infoWindow.setContent(browserHasGeolocation ? 'Error: Geolocation service failed.'
							: 'Error: Your browser doesnt support geolocation.');
			infoWindow.open(map);
		}
		
	</script>
<!---------------- FINE MAPPA ------------------------------------------------------------------------------->

<center><h3 class="titolislider" style="color:white; margin-top:20px">Cerca gli eventi per luogo, data, artista o location!</h3></center>
<div class="fade-in">
	<div class="cercaev">
		<input type="text" id="myInput" onkeyup="myFunction()"
			placeholder="Cerca...">

		<ul id="myUL" class="eventi">
			
<%-- 			<c:forEach var="a" items="${artisti}" varStatus="Loop"> --%>
<%-- 				<li class="event"><a href="#" id="${eventi[Loop.index].ID}">${a.nome},${eventi[Loop.index].data},${locations[Loop.index].nome} --%>
<!-- 				</a></li> -->
<%-- 			</c:forEach> --%>

		</ul>

	</div>

	<script>
		function myFunction() {
			// Declare variables
			var input, filter, ul, li, a, i;
			input = document.getElementById('myInput');
			filter = input.value.toUpperCase();
			ul = document.getElementById("myUL");
			li = ul.getElementsByTagName('li');

			// Loop through all list items, and hide those who don't match the search query
			for (i = 0; i < li.length; i++) {
				a = li[i].getElementsByTagName("a")[0];
				if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
					li[i].style.display = "";
				} else {
					li[i].style.display = "none";
				}
			}
		}
	</script>

	<c:if test="${not empty user.nickname}">
	<center><h3 id="expCreate" class="titolislider" style="color:white">Experiences Create da ${user.nickname}</h3></center>
	
		<ul id="myUL1" class="experiencesCreate">
			<!-- riempita da ajax -->

		</ul>
		
	<center><h3 id="expPartecipate" class="titolislider" style="color:white">Experiences a cui partecipi</h3></center>
	
		<ul id="myUL1" class="experiencesPartecipate">
			<!-- riempita da ajax -->

		</ul>
	</c:if>
		
		
	<center><h3 id="expRimaste" class="titolislider" style="color:white">
	<c:if test="${empty user.nickname}">Tutte le Experiences</c:if>
	<c:if test="${not empty user.nickname}">Altre Experiences</c:if> 
	</h3></center>
	
		<ul id="myUL1" class="experiences">
			<!-- riempita da ajax -->

		</ul>

	<c:if test="${not empty user.nickname}">

		<div id="creaExperience">
		<center>	<h3 p class="titolislider" style="color:white">Non sei interessato a nessuna Experience?</h3></center>
			<p style="text-align: center">
				<a data-toggle="modal" data-target="#crea_experience" class="titolislider" style="border:1px solid #660000; background:#660000; border-radius: 4px; color: white;"
					onclick="inserisciExperienceDisableCheck()"> Crea la tua
					Experience!</a>
			</p>
		</div>

	</c:if>
	
	<c:if test="${user.moderatore == 'true'}">

		<div id="creaExperience">
		<p style="text-align: center">
				<a data-toggle="modal" data-target="#aggiungi_l" class="titolislider" style="border:1px solid #660000; background:#660000; border-radius: 4px; color: white;"
					onclick="inserisciExperienceDisableCheck()"> Aggiungi Location</a>
			</p>
		</div>

	</c:if>

	<div class="modal fade crea_experience" id="crea_experience">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<a href="#" data-dismiss="modal" class="class pull-right"><span
						class="glyphicon glyphicon-remove"></span></a>

					<form id="FormCreazione" class="form-horizontal" role="form"
						method="post" action="">
						
<!-- 						<input id="idEvento" style="display: none" name="eventoID" -->
<!-- 							value=""> -->

							
						<div class="form-group">
							<label for="Viaggio" class="col-sm-3 control-label">Info</label>
							<div class="col-sm-9">
		
								<input id="descrizione" type="text" name="descrizione"
									placeholder="Info su viaggio, soggiorno e concerto"
									class="form-experience" autofocus required="required">
								
							</div>
								<label for="Viaggio" class="col-sm-3 control-label">Riguarda:</label>
								<div class="col-sm-9">
								<input id="checkBoxPernottamento" name="pernottamento" type="checkbox"> Pernottamento
								<input id="checkBoxViaggio" name="viaggio" type="checkbox"> Viaggio
								<input id="checkBoxConcerto" name="concerto" type="checkbox"> Concerto
								</div>
						</div>
						<div class="form-group">
							<label for="Posti" class="col-sm-3 control-label">Data</label>
							<div class="col-sm-9">
							
								<input id="data" type="date" name="data"
									placeholder="GG/MM/AAAA" class="form-control" autofocus
									required="required">
									
							</div>
						</div>

						<div class="form-group">
							<label for="Posti" class="col-sm-3 control-label">Posti
								Experience</label>
							<div class="col-sm-9">
							
								<input id="posti" type="number" name="numeroPosti" min="2"
									placeholder="Inserisci il numero di posti" class="form-control"
									required="required">
									
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-9 col-sm-offset-3">
								<button id="creaExp" type="submit"
									class="btn btn-primary btn-block">Crea la tua
									Experience!</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade crea_experience" id="aggiungi_l">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<a href="#" data-dismiss="modal" class="class pull-right"><span
						class="glyphicon glyphicon-remove"></span></a>
					<p id="scritta_messaggio" style="margin-left:175px">AGGIUNTA LOCATION</p>
					<form id="FormCreazione" class="form-horizontal" role="form"
						method="post" action="AggiungiLocation">
						
<!-- 						<input id="idEvento" style="display: none" name="eventoID" -->
<!-- 							value=""> -->

							
						<div class="form-group">
							<label for="Viaggio" class="col-sm-3 control-label">Nome</label>
							<div class="col-sm-9">
		
								<input id="descrizione" type="text" name="nome"
									placeholder="Inserisci il nome della location"
									class="form-experience" autofocus required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="Posti" class="col-sm-3 control-label">Citta</label>
							<div class="col-sm-9">
							
								<input id="data" type="text" name="citta"
									placeholder="Inserisci citta' della location" class="form-control" autofocus
									required="required">
									
							</div>
						</div>

						<div class="form-group">
							<label for="Posti" class="col-sm-3 control-label">Stato</label>
							<div class="col-sm-9">
							
								<input id="posti" type="text" name="stato" min="2"
									placeholder="Inserisci il numero di posti" class="form-control"
									required="required">
									
							</div>
						</div>
						
						<div class="form-group">
							<label for="Posti" class="col-sm-3 control-label">Longitudine</label>
							<div class="col-sm-9">
							
								<input id="posti" type="text" name="longitudine" 
									placeholder="Inserisci la longitudine della location" class="form-control"
									required="required">
									
							</div>
						</div>
						
							<div class="form-group">
							<label for="Posti" class="col-sm-3 control-label">Latitudine</label>
							<div class="col-sm-9">
							
								<input id="posti" type="number" name="latitudine" min="2"
									placeholder="Inserisci la latitudine della location" class="form-control"
									required="required">
									
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-9 col-sm-offset-3">
								<button id="creaExp" type="submit"
									class="btn btn-primary btn-block">Aggiungi Location</button>
							</div>
						</div>
					</form>
				</div>
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
						<p id="titolifadexp">Posti rimasti:</p>
						<input style="color:black;" type="text" name="posti" id="posti" readonly>
					</div>

					<div>
						<p id="titolifadexp">Limite Adesione:</p>
						<input style="color:black" type="date" id="dataLimiteAdesione" readonly>
					</div>

					<div>
						<p id="titolifadexp">Descrizione:</p>
						<input style="color:black; width:100%; height:70px;" type="text" id="descrizione" readonly>
					</div>

					<div>
						<p id="titolifadexp">Organizzatore:</p>
						<p id="organizzatore"></p>
					</div>

					<div id="titolifadexp">
						<p>Partecipanti:</p>
						<ul id="partecipanti">
<!-- 							ajax ci mette i li con i nick dei partecipanti -->
						</ul>

			<button style="float:right" type="submit" id="partecipa" class="btn btn-success">Partecipa</button>
 			<button style="float:right" type="submit" id="annulla_partecipa" class="btn btn-success">Annulla Partecipa</button>
 			<button style="float:right" type="submit" id="modifica" class="btn btn-success">Modifica</button>
 			<button style="float:right" type="submit" id="elimina" class="btn btn-success">Elimina</button>
					</div>
 		

				</div>
			</div>
		</div>
	</div>
</div>

<div style="background:white; border-radius:10px; margin:10px; border:1px solid #660000">
	<div class="container">
	<h4 style="font-weight:bold; color:#262626">LEGGENDA TIPO EXPERIENCES (PER COLORE):</h4>
		<div class="row" style="display:inline-block; margin-top:10px">
			<div class="col-md-2">
			<p style="background:red; color:white; display:inline-block; border-radius:3px; height:20px;"> Pernottamento </p>
			</div>
			<div class="col-md-2">
			<p style="background:blue; color:white;  display:inline-block; border-radius:3px; min-height:20px;"> Concerto </p>
			</div>
			<div class="col-md-2">
			<p style="background:green; color:white; display:inline-block; border-radius:3px; min-height:20px;"> Pernottamento </p>
			</div>
			<div class="col-md-2">
			<p style="background:#00cccc; color:white; display:inline-block; border-radius:3px; min-height:20px;"> Pernottamento & Concerto </p>	
			</div>
			<div class="col-md-2">
			<p style="background:#4d2600; color:white;  display:inline-block; border-radius:3px; min-height:20px;"> Viaggio & Concerto </p>
			</div>
			<div class="col-md-2">
			<p style="background:#ff6600; color:white;  display:inline-block; border-radius:3px; min-height:20px;"> Pernottamento & Viaggio </p>
			</div>
			<div class="col-md-2">
			<p style="background:#cc9900; color:white; display:inline-block; border-radius:2px"> Pernottamento & Viaggio & Concerto</p> 
			</div>
		</div>
	</div>
</div>
<!--<script>
function sortList() {
  var list, i, switching, b, shouldSwitch;
  list = document.getElementById("myUL");
  switching = true;
  /* Make a loop that will continue until
  no switching has been done: */
  while (switching) {
    // Start by saying: no switching is done:
    switching = false;
    b = list.getElementsByTagName("li");
    // Loop through all list items:
    for (i = 0; i < (b.length - 1); i++) {
      // Start by saying there should be no switching:
      shouldSwitch = false;
      /* Check if the next item should
      switch place with the current item: */
      if (b[i].innerHTML.toLowerCase() > b[i + 1].innerHTML.toLowerCase()) {
        /* If next item is alphabetically lower than current item,
        mark as a switch and break the loop: */
        shouldSwitch= true;
        break;
      }
    }
    if (shouldSwitch) {
      /* If a switch has been marked, make the switch
      and mark the switch as done: */
      b[i].parentNode.insertBefore(b[i + 1], b[i]);
      switching = true;
    }
  }
}
</script>-->

	<!-- Importante inserire JQuery qui (prima del bootstrap), altrimenti non funziona -->
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBN9KSVp5NZ5ek2odACLlh9BLOpQOAYtrM&callback=initMap">
	</script>
	<script src="js/markerclusterer.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/eventoExperiences.js"></script>
	<script src="js/experience.js"></script>
	<script src="js/eventi.js"></script>

</body>
</html>