<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Registrazione</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="style.css"> <!-- Inclusione stile -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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

<div class=text>
	<p>Utente già presente nel sistema</p>
	<p><a href="index.jsp">Clicca qui per essere reindirizzato alla home</a></p>
	<p><a href="register.jsp">Clicca qui per ritentare la registrazione</a></p>
</div>

<!-- Importante inserire JQuery qui (prima del bootstrap), altrimenti non funziona -->

</body>
</html>