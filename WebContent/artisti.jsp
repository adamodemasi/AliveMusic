<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Artisti</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="style.css"> <!-- Inclusione stile -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>


	<meta name="viewport" content="width=device-width, initial scale=1">
</head>
<body>


<nav class="navbar navbar-default navbar-fixed-top">
	<!--Il container-flud serve per farlo adattare a tutto lo schermo, se togliamo fluid di default e circa 1200-->
   <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.jsp"><img src="img\alive.png"></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
     <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="index.jsp">Home <span class="sr-only">(current)</span></a></li>
        <li><a href="eventi.jsp">Eventi</a></li>
        <li  class="active"><a href="artisti.jsp">Artisti</a></li>
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
         	 </ul>
        </li>
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


    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>





<div class="fade-in">
<div class="container">
	<div class="cercaev">
	<div class="row">
		<input type="text" id="myInput" onkeyup="myFunction()"
			placeholder="Cerca un artista...">
			</div></div>
	</div>

<div class="container">
<div class="row">
	<div class="col-sm-1" style="margin-top:20px">
		<div>
		<p class="titolidiv" style="color:black">Generi: </p>
  			<p id="checkgen"><input id ="myCheckRock" type="checkbox" name="Rock" value="gen" checked onclick=generi()>Rock<br> </p>
  			<p id="checkgen"><input id ="myCheckMetal" type="checkbox" name="Metal" value="gen" checked onclick=generi()>Metal<br> </p>
  			<p id="checkgen"><input id ="myCheckRap" type="checkbox" name="Rap" value="gen" checked onclick=generi()>Rap/Hip-Hop<br></p>
 		    <p id="checkgen"><input id ="myCheckPop" type="checkbox" name="Pop" value="gen" checked onclick=generi()>Pop<br></p>
  			<p id="checkgen"><input id ="myCheckClass"type="checkbox" name="Class" value="gen" checked onclick=generi()>Classic<br></p>
		</div>
</div>

	<div class="col-sm-11">
		<div id="artisti" class="list">

  		</div>
   </div>
	</div>
</div>

</div>

<script>	
		function generi() {
	    var checkBox = document.getElementById("myCheckRock");
	    var checkBox1 = document.getElementById("myCheckMetal");
	    var checkBox2 = document.getElementById("myCheckRap");
	    var checkBox3 = document.getElementById("myCheckPop");
	    var checkBox4 = document.getElementById("myCheckClass");
	    
	    if (checkBox.checked == true){
	    	if(document.getElementsByTagName) {
	    		 var lista_td = document.getElementsByTagName("article");
	    		 for(i=0; i < lista_td.length; i++)
	    		 {
	    		  	var c = lista_td.item(i);
	    		  	var p = lista_td.item(i).getElementsByTagName("p");
	    		  		for(j=0; j < p.length; j++)
	    		  		{
	    		  			var x = p.item(j);
	    		  			if(x.innerText == "Rock")
	    		  				$(c).show(250)
	    		  		}
	    		 	}	
	    		 } 			

	       
	    } else {
	    	if(document.getElementsByTagName) {
	    		 var lista_td = document.getElementsByTagName("article");
	    		 for(i=0; i < lista_td.length; i++)
	    		 {
	    		  	var c = lista_td.item(i);
	    		  	var p = lista_td.item(i).getElementsByTagName("p");
	    		  		for(j=0; j < p.length; j++)
	    		  		{
	    		  			var x = p.item(j);
	    		  			if(x.innerText == "Rock")
	    		  				$(c).hide(250)
	    		  		}
	    		 	}	
	    		 } 		
	    }	
	    
	    if (checkBox1.checked == true){
	    	if(document.getElementsByTagName) {
	    		 var lista_td = document.getElementsByTagName("article");
	    		 for(i=0; i < lista_td.length; i++)
	    		 {
	    		  	var c = lista_td.item(i);
	    		  	var p = lista_td.item(i).getElementsByTagName("p");
	    		  		for(j=0; j < p.length; j++)
	    		  		{
	    		  			var x = p.item(j);
	    		  			if(x.innerText == "Metal")
	    		  				$(c).show(250)
	    		  		}
	    		 	}	
	    		 } 			

	       
	    } else {
	    	if(document.getElementsByTagName) {
	    		 var lista_td = document.getElementsByTagName("article");
	    		 for(i=0; i < lista_td.length; i++)
	    		 {
	    		  	var c = lista_td.item(i);
	    		  	var p = lista_td.item(i).getElementsByTagName("p");
	    		  		for(j=0; j < p.length; j++)
	    		  		{
	    		  			var x = p.item(j);
	    		  			if(x.innerText == "Metal")
	    		  				$(c).hide(250)
	    		  		}
	    		 	}	
	    		 } 		
	    }
	    
	    if (checkBox2.checked == true){
	    	if(document.getElementsByTagName) {
	    		 var lista_td = document.getElementsByTagName("article");
	    		 for(i=0; i < lista_td.length; i++)
	    		 {
	    		  	var c = lista_td.item(i);
	    		  	var p = lista_td.item(i).getElementsByTagName("p");
	    		  		for(j=0; j < p.length; j++)
	    		  		{
	    		  			var x = p.item(j);
	    		  			if(x.innerText == "Rap")
	    		  				$(c).show(250)
	    		  		}
	    		 	}	
	    		 } 			

	       
	    } else {
	    	if(document.getElementsByTagName) {
	    		 var lista_td = document.getElementsByTagName("article");
	    		 for(i=0; i < lista_td.length; i++)
	    		 {
	    		  	var c = lista_td.item(i);
	    		  	var p = lista_td.item(i).getElementsByTagName("p");
	    		  		for(j=0; j < p.length; j++)
	    		  		{
	    		  			var x = p.item(j);
	    		  			if(x.innerText == "Rap")
	    		  				$(c).hide(250)
	    		  		}
	    		 	}	
	    		 } 		
	    }
	    
	    if (checkBox3.checked == true){
	    	if(document.getElementsByTagName) {
	    		 var lista_td = document.getElementsByTagName("article");
	    		 for(i=0; i < lista_td.length; i++)
	    		 {
	    		  	var c = lista_td.item(i);
	    		  	var p = lista_td.item(i).getElementsByTagName("p");
	    		  		for(j=0; j < p.length; j++)
	    		  		{
	    		  			var x = p.item(j);
	    		  			if(x.innerText == "Pop")
	    		  				$(c).show(250)
	    		  		}
	    		 	}	
	    		 } 			

	       
	    } else {
	    	if(document.getElementsByTagName) {
	    		 var lista_td = document.getElementsByTagName("article");
	    		 for(i=0; i < lista_td.length; i++)
	    		 {
	    		  	var c = lista_td.item(i);
	    		  	var p = lista_td.item(i).getElementsByTagName("p");
	    		  		for(j=0; j < p.length; j++)
	    		  		{
	    		  			var x = p.item(j);
	    		  			if(x.innerText == "Pop")
	    		  				$(c).hide(250)
	    		  		}
	    		 	}	
	    		 } 		
	    }
	    
	    if (checkBox4.checked == true){
	    	if(document.getElementsByTagName) {
	    		 var lista_td = document.getElementsByTagName("article");
	    		 for(i=0; i < lista_td.length; i++)
	    		 {
	    		  	var c = lista_td.item(i);
	    		  	var p = lista_td.item(i).getElementsByTagName("p");
	    		  		for(j=0; j < p.length; j++)
	    		  		{
	    		  			var x = p.item(j);
	    		  			if(x.innerText == "Classic")
	    		  				$(c).show(250)
	    		  		}
	    		 	}	
	    		 } 			

	       
	    } else {
	    	if(document.getElementsByTagName) {
	    		 var lista_td = document.getElementsByTagName("article");
	    		 for(i=0; i < lista_td.length; i++)
	    		 {
	    		  	var c = lista_td.item(i);
	    		  	var p = lista_td.item(i).getElementsByTagName("p");
	    		  		for(j=0; j < p.length; j++)
	    		  		{
	    		  			var x = p.item(j);
	    		  			if(x.innerText == "Classic")
	    		  				$(c).hide(250)
	    		  		}
	    		 	}	
	    		 } 		
	    }
}	
</script>

<script>
function myFunction()
{	
	var input, filter, ul, a;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		ul = document.getElementsByTagName("article");
 		for(i=0; i < ul.length; i++)
		 {
		  	var c = ul.item(i);

 		  	var li = ul.item(i).getElementsByClassName("nomiband");
 		  	
 		  		for(j=0; j < li.length; j++)
 		  		{
 		  			var m = li.item(j).innerText.toUpperCase();
 		  			var n = li.item(j).innerText.toUpperCase();
		  					
 		  			for (f = 0; f < m.length; f++)
 		  			{
 		  				if (m.indexOf(filter) > -1 ) 
 		  				{ 
 		  						$(c).show(250)
 		  				}
		 				else {
								$(c).hide(250)
		 				}
 		  			}}}


}
</script>

<c:if test="${user.moderatore=='true'}">

		<div id="creaExperience">
			<p style="text-align: center">
			<a data-toggle="modal" data-target="#aggiungi_artista" class="btn btn-success" id="btnSave" style="margin-right:45%">Aggiungi artista</a>
			</p>
		</div>
</c:if>

<div class="modal fade aggiungi_artista" id="aggiungi_artista">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<a href="#" data-dismiss="modal" class="class pull-right"><span
						class="glyphicon glyphicon-remove"></span></a>

			<p id="scritta_messaggio" style="margin-left:190px">AGGIUNTA ARTISTA</p>
			<form id="Form" class="form-horizontal" role="form" method="post" action="AggiungiArtista" enctype="multipart/form-data">
            <div class="form-group">
              <label for="Viaggio" class="col-sm-3 control-label">Nome</label>
              <div class="col-sm-9">
    
                <input id="descrizione" type="text" name="nome"
                  placeholder="Nome artista"
                  class="form-experience" autofocus required="required">
               
      		  </div>
              <label for="Viaggio" class="col-sm-3 control-label">Sito Web</label>
              <div class="col-sm-9">
    
                <input id="descrizione" type="text" name="sito"
                  placeholder="Sito Web (formato http://indirizzo)"
                  class="form-experience" autofocus required="required">
                
              </div>
          		<label for="Viaggio" class="col-sm-3 control-label">Bio</label>
              <div class="col-sm-9">
    
                <input id="descrizione" type="text" name="bio"
                  placeholder="Bio artista"
                  class="form-experience" autofocus required="required">
                
              </div>
              <label for="Viaggio" class="col-sm-3 control-label">Genere</label>
              <div class="col-sm-9">
    
                <input id="descrizione" type="text" name="genere"
                  placeholder="genere artista"
                  class="form-experience" autofocus required="required">
                
              </div>
              
              <div class="cambia immagine">
				<div class="control-group">
				  <label class="control-label" for="editimmagine">Immagine artista</label>
						<div class="controls">
							<input type="file" id="main-input" name="img_artista" class="form-control form-input form-style-base">
							<h2 id="fake-btn" class="form-input fake-styled-btn text-center truncate"></h2>
						</div>
				</div>
			 </div>
			 
			 <div class="cambia copertina">
				<div class="control-group">
				  <label class="control-label" for="editimmagine">Copertina artista</label>
						<div class="controls">
							<input type="file" id="main-input" name="cop_artista" class="form-control form-input form-style-base">
							<h2 id="fake-btn" class="form-input fake-styled-btn text-center truncate"></h2>
						</div>
				</div>
			 </div>
			 </div>
			 
			 
            <div class="form-group">
              <div class="col-sm-9 col-sm-offset-3">
                <button id="button" type="submit"
                  class="btn btn-primary btn-block">Aggiungi artista</button>
              </div>
            </div>
          </form>
				</div>
			</div>
		</div>
</div>

<!-- Importante inserire JQuery qui (prima del bootstrap), altrimenti non funziona -->

<script src="js/artisti.js" ></script>

</body>
</html>
