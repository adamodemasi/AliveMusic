<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Shop</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<!-- Inclusione stile -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.payment/1.2.3/jquery.payment.min.js"></script>
<!-- If you're using Stripe for payments -->
<script type="text/javascript" src="https://js.stripe.com/v2/"></script>

<meta name="viewport" content="width=device-width, initial scale=1">
</head>
<body style="background: #f6f6f6">


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
				<div class="navbar-brand">
					<img src="img\alive.png">
				</div>
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
					<li class="active"><a href="shop.jsp">Shop</a></li>
					<li><a id="carrello" href="" data-toggle="modal"
						data-target="#cart_view"><span
							class="glyphicon glyphicon-shopping-cart"></span> Carrello </a></li>
					<li><a href="info.jsp">Info</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>


	<!---------------------------------------------------------------CAROSELLO-------------------------------------->
	<div class="fade-in">
		<div class="container">
			<div class='row'>
				<div class="carousel slide media-carousel" id="media">
					<div class="carousel-inner">

						<div class="item  active">
							<div class="row">

								<div class="col-xs-4 singoloProdotto" id="prodotto1">
									<div class="thumbnail" data-toggle="modal"
										data-target="#product_view">
										<img src="img\cc.jpg" alt="" class="image">
										<div class="dettagliprodotto">
											<p>Dettagli</p>
										</div>
									</div>
								</div>

								<div class="col-xs-4 singoloProdotto" id="prodotto2">
									<div class="thumbnail" data-toggle="modal"
										data-target="#product_view">
										<img src="img\cc.jpg" alt="" class="image">
										<div class="dettagliprodotto">
											<p>Dettagli</p>
										</div>
									</div>
								</div>

								<div class="col-xs-4 singoloProdotto" id="prodotto3">
									<div class="thumbnail" data-toggle="modal"
										data-target="#product_view">
										<img src="img\cc.jpg" alt="" class="image">
										<div class="dettagliprodotto">
											<p>Dettagli</p>
										</div>
									</div>
								</div>

							</div>
						</div>
						<div class="item">
							<div class="row">

								<div class="col-xs-4 singoloProdotto" id="prodotto4">
									<div class="thumbnail" data-toggle="modal"
										data-target="#product_view">
										<img src="img\cc.jpg" alt="" class="image">
										<div class="dettagliprodotto">
											<p>Dettagli</p>
										</div>
									</div>
								</div>

								<div class="col-xs-4 singoloProdotto" id="prodotto5">
									<div class="thumbnail" data-toggle="modal"
										data-target="#product_view">
										<img src="img\cc.jpg" alt="" class="image">
										<div class="dettagliprodotto">
											<p>Dettagli</p>
										</div>
									</div>
								</div>

								<div class="col-xs-4 singoloProdotto" id="prodotto6">
									<div class="thumbnail" data-toggle="modal"
										data-target="#product_view">
										<img src="img\cc.jpg" alt="" class="image">
										<div class="dettagliprodotto">
											<p>Dettagli</p>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
					<a data-slide="prev" href="#media" class="left carousel-control">‹</a>
					<a data-slide="next" href="#media" class="right carousel-control">›</a>
				</div>
			</div>
		</div>
	</div>

	<!---------------------------------------------------------------------- TABS ------------------------------------------------->
	<div class="container">
		<ul id="tabs" class="navprod nav-pills">
			<li class="active"><a data-toggle="tab" href="#menutshirt">T-Shirt</a></li>
			<li><a data-toggle="tab" href="#menufelpe">Felpe</a></li>
			<c:if test="${not empty user.nickname}">
				<li><a data-toggle="tab" href="#menucreazione">Invia il tuo
						design</a></li>
			</c:if>
		</ul>
	</div>

	<div id="prodotti" class="tab-content"
		style="margin-top: 5px; background: #660000;">

		<div class="tab-content" style="margin-top: 5px; background: #660000;">


			<div id="menutshirt" class="tab-pane fade in active">

				<div class="tab-content"
					style="margin-top: 5px; background: #660000;">
					<div id="home" class="tab-pane fade in active">

						<center>
							<button id="orderPriceTshirt" class="btn btn-success" style="margin-top: 15px;
								type="button">Ordina per prezzo: Crescente</button>
						</center>

						<div class="container">

							<div class="row" id="tshirt" style="margin-top: 10px;"></div>
						</div>
					</div>
				</div>
			</div>

			<div id="menufelpe" class="tab-pane fade">
				<div class="tab-content"
					style="margin-top: 5px; background: #660000;">
					<div id="home" class="tab-pane fade in active">

						<center>
							<button id="orderPriceFelpe" class="btn btn-success" style="margin-top: 15px"
								type="button">Ordina per prezzo: Descrescente</button>
						</center>

						<div class="container">
							<div class="row" id="felpe" style="margin-top: 10px;"></div>
						</div>
					</div>
				</div>
			</div>

	
		<div id="menucreazione" class="tab-pane fade">
			<form method="post" action="InserisciProdotto" enctype="multipart/form-data">
			<div class="tab-content"
				style="margin-top: 5px; background: #660000;">
				<div id="home" class="tab-pane fade in active">
			
					<div class="container">
						<center><p style="color:white; font-weight:bold; margin-top:20px; font-size:18px;">CARICA QUI IL TUO DESIGN!</p></center>
						<div class="row" style="margin-top: 35px;">						
							<label for="email" class="control-label" style="color:white" >Nome Prodotto</label>
							<div class="form-group">
									<input name="nome_p" type="text" id="email" placeholder="Inserisci il nome del prodotto che vuoi caricare" class="form-control" autofocus>
							</div>
						</div>
						<div class="row" style="margin-top: 35px;">
							<label for="email" class="control-label" style="color:white" >Descrizione</label>
							<div class="form-group">
									<input name="descrizione_p" type="text" id="email" placeholder="Inserisci una breve descrizione (max 160 caratteri)" class="form-control" autofocus>
							</div>
						</div>
						<div class="row" style="margin-top: 35px;">
							<label for="email" class="control-label" style="color:white" >Prezzo</label>
							<div class="form-group">
									<input name="prezzo_p" type="text" id="email" placeholder="Inserisci il prezzo dell'articolo (max 15 euro per le t-shirt e 25 per le felpe)" class="form-control" autofocus>
							</div>
						</div>
						
							<div class="form-group">
							<label for="email" class="control-label" style="color:white; margin-left:-15px" >Tipologia</label>
			              		<select name="tipo_p">
			              		<option value="felpa">felpa</option>
  								<option value="t-shirt">t-shirt</option>
							    </select>
              				</div>
					    
					    
					    	
							<div class="form-group">
							<label for="email" class="control-label" style="color:white; margin-left:-15px" >Colore</label>
			              		<select name="colore_p">
			              		<option value="nero">nero</option>
  								<option value="bianco">bianco</option>
  								<option value="blu">blu</option>
  								<option value="giallo">giallo</option>
  								<option value="rosso">rosso</option>
  								<option value="verde">verde</option>
							    </select>
              				
					    </div>
						<div class="row" style="margin-top: 10px;">
							<div class="control-group">
									<label class="control-label" for="editimmagine" style="color:white">Carica Immagine</label>
									<div class="controls">
										<input type="file" id="main-input"  name="immagine_p" accept="image/x-png,image/x-png,image/x-jpeg"
											class="form-control form-input form-style-base">
										<h2 id="fake-btn"
											class="form-input fake-styled-btn text-center truncate"></h2>
									</div>
									<button type="submit" id="btnSave" name="btnSave" class="btn btn-success">Invia</button>
							</div>
		</div>
					</div>
				</div>
			</div>
			</form>
					</div>
				</div>

			</div>

		</div>

		<!--------------------------------------- PRODOTTO ----------------------------------->
		<div class="modal fade product_view" id="product_view">
			<div class="modal-dialog">
				<div class="modal-content">
					<!--<div class="modal-header">
                <a href="#" data-dismiss="modal" class="class pull-right"><span class="glyphicon glyphicon-remove"></span></a>
                <!--<h3 class="modal-title">WE</h3>
            </div>-->
					<div class="modal-body">
						<a href="" data-dismiss="modal" class="class pull-right"><span
							class="glyphicon glyphicon-remove"></span></a>
						<div class="row">
							<div class="col-xs-6 product_img">
								<img id="immagineProdotto" src="" class="img-responsive">
							</div>
							<div class="col-xs-6 product_content">
								<h4 id="prodottodaaggiungere">
									Product Name: <span id="nomeProdotto">Default</span>
								</h4>

								<p id="descrProdotto">Default</p>
								<h3 id="coloreProdotto">Colore</h3>
								<h3 id="prezzoProdotto">10</h3>

								<div class="row">


									<div class="col-md-4 col-sm-6 col-xs-12">
										<select id="taglieProdotto" class="form-control" name="select">

										</select>
									</div>
									<div class="col-md-4 col-sm-12">
										<select id="quantitaProdotto" class="form-control"
											name="select">
											<option value="" selected>QTY</option>

										</select>
									</div>
									<!-- end col -->
								</div>
								<div class="space-ten"></div>
								<div class="btn-ground">
									<button id="buttonCarrello" type="button"
										class="btn btn-primary">

										<span class="glyphicon glyphicon-shopping-c+art"></span>
										Carrello
									</button>
									<c:if test="${not empty user.nickname }">
										<button id="buttonCompra" type="button"
											class="btn btn-primary" data-toggle="modal"
											data-target="#payment_view,#product_view">
											<span class="glyphicon glyphicon-ok"></span>Compra
										</button>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--------------------------------------------------------------------------CARRELLO --------------------------------------------------------------------------------->
		<div class="modal fade cart_view" id="cart_view">
			<div class="modal-dialog">
				<div class="modal-header">
					<a href="" data-dismiss="modal" class="class pull-right"><span
						class="glyphicon glyphicon-remove"></span></a>
					<h3 class="modal-title" style="color: white;">Carrello</h3>
				</div>
				<div class="modal-body">
					<ul id="contenutocarrello">
						<c:set var="total" value="${0}" />
						<c:forEach items="${carrello}" var="prodotto">
							<c:set var="totalProduct"
								value="${prodotto.quantita * prodotto.prezzo}" />
							<li>${prodotto.nome},taglia ${prodotto.taglia} , qnt:
								${prodotto.quantita} x ${prodotto.prezzo}€ = <c:out
									value="${totalProduct}" />€
							</li>
							<c:set var="total" value="${totalProduct + total}" />

						</c:forEach>
					</ul>
				</div>

				<div class="modal-totale">
					<p style="font-weight:bold; margin-left:6px;">TOTALE :</p>
					<p id="prezzoTotale" style="font-weight:bold; margin-left:6px; color:#660000">
						<c:out value="${total}" />€
					</p>
				</div>

				<div class="modal-endler">
					<c:if test="${not empty user.nickname}">
						<button id="buttonCompraCarrello" type="button"
							class="btn btn-primary" data-toggle="modal"
							data-target="#payment_view,#cart_view"
							style="float: right; margin-top: 5px;">Acquista</button>
					</c:if>
				</div>

			</div>
		</div>


		<!-- SCRIPT CARRELLO -->
<!-- 		<script type="text/javascript"> 
// 			var x = 0;
// 			var temp = 0;
// 			function alertcarrello() {
				// 				window.alert("Prodotto aggiunto al carrello");
				// 				$("#prodottodaaggiungere").clone().appendTo(
				// 						$("#contenutocarrello"));
				// 				$("#cost").clone().appendTo($("#contenutocarrello"));
				// 				temp = $("#cost").clone();
				// 				x += temp;
				// 				$("#prezzototale").append((x));

// 			}
<!-- 		</script> -->

		<!-- <div style="color:#660000; background-color:white; margin-top:20px; font-weight:bold; padding:10px;"><center>Vorresti creare un prodotto o eliminarne / modificarne uno da te inserito? Visita la pagina delle info per ulteriori informazioni!</center></div>-->

		<!-- P A G A M E N T O ----------------------------------------------------------------------------------------------->
		<div class="modal fade payment_view" id="payment_view">
			<div class="modal-dialog">
				<div class="modal-header">
					<a href="#" data-dismiss="modal" class="class pull-right"><span
						class="glyphicon glyphicon-remove"></span></a>
					<!-- CREDIT CARD FORM STARTS HERE -->
					<div class="panel panel-default credit-card-box">
						<div class="panel-heading display-table">
							<div class="row display-tr">
								<h3 class="panel-title display-td">Payment Details</h3>
								<div class="display-td">
									<img class="img-responsive pull-right"
										src="http://i76.imgup.net/accepted_c22e0.png">
								</div>
							</div>
						</div>
						<div class="panel-body">
							<form role="form" id="payment-form" method="POST"
								action="javascript:void(0);">
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label for="cardNumber">CARD NUMBER</label>
											<div class="input-group">
												<input type="tel" class="form-control" name="cardNumber"
													placeholder="Valid Card Number" autocomplete="cc-number"
													required autofocus /> <span class="input-group-addon"><i
													class="fa fa-credit-card"></i></span>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-7 col-md-7">
										<div class="form-group">
											<label for="cardExpiry"><span class="hidden-xs">EXPIRATION</span><span
												class="visible-xs-inline">EXP</span> DATE</label> <input type="tel"
												class="form-control" name="cardExpiry" placeholder="MM / YY"
												autocomplete="cc-exp" required />
										</div>
									</div>
									<div class="col-xs-5 col-md-5 pull-right">
										<div class="form-group">
											<label for="cardCVC">CV CODE</label> <input type="tel"
												class="form-control" name="cardCVC" placeholder="CVC"
												autocomplete="cc-csc" required />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label for="couponCode">Indirizzo di spedizione</label> <input
												type="text" class="form-control" name="couponCode" />
										</div>
										<div class="form-group">
											<label for="couponCode">Città</label> <input type="text"
												class="form-control" name="couponCode" />
										</div>
										<div class="form-group">
											<label for="couponCode">Provincia</label> <input type="text"
												class="form-control" name="couponCode" />
										</div>
										<div class="form-group">
											<label for="couponCode">Cap</label> <input type="text"
												class="form-control" name="couponCode" />
										</div>
										<div class="form-group">
											<label for="couponCode">Numero di Telefono</label> <input
												type="text" class="form-control" name="couponCode" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<button class="subscribe btn btn-success btn-lg btn-block"
											type="button">Start Subscription</button>
									</div>
								</div>
								<div class="row" style="display: none;">
									<div class="col-xs-12">
										<p class="payment-errors"></p>
									</div>
								</div>
							</form>

							<!-- CREDIT CARD FORM ENDS HERE -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

		<script type="text/javascript">
			var $form = $('#payment-form');
			$form.find('.subscribe').on('click', payWithStripe);

			/* If you're using Stripe for payments */
			function payWithStripe(e) {
				e.preventDefault();

				/* Abort if invalid form data */
				if (!validator.form()) {
					return;
				}

				/* Visual feedback */
				$form.find('.subscribe').html(
						'Validating <i class="fa fa-spinner fa-pulse"></i>')
						.prop('disabled', true);

				var PublishableKey = 'pk_test_6pRNASCoBOKtIshFeQd4XMUh'; // Replace with your API publishable key
				Stripe.setPublishableKey(PublishableKey);

				/* Create token */
				var expiry = $form.find('[name=cardExpiry]').payment(
						'cardExpiryVal');
				var ccData = {
					number : $form.find('[name=cardNumber]').val().replace(
							/\s/g, ''),
					cvc : $form.find('[name=cardCVC]').val(),
					exp_month : expiry.month,
					exp_year : expiry.year
				};

				Stripe.card
						.createToken(
								ccData,
								function stripeResponseHandler(status, response) {
									if (response.error) {
										/* Visual feedback */
										$form.find('.subscribe').html(
												'Try again').prop('disabled',
												false);
										/* Show Stripe errors on the form */
										$form.find('.payment-errors').text(
												response.error.message);
										$form.find('.payment-errors').closest(
												'.row').show();
									} else {
										/* Visual feedback */
										$form
												.find('.subscribe')
												.html(
														'Processing <i class="fa fa-spinner fa-pulse"></i>');
										/* Hide Stripe errors on the form */
										$form.find('.payment-errors').closest(
												'.row').hide();
										$form.find('.payment-errors').text("");
										// response contains id and card, which contains additional card details
										console.log(response.id);
										console.log(response.card);
										var token = response.id;
										// AJAX - you would send 'token' to your server here.
										$
												.post(
														'/account/stripe_card_token',
														{
															token : token
														})
												// Assign handlers immediately after making the request,
												.done(
														function(data,
																textStatus,
																jqXHR) {
															$form
																	.find(
																			'.subscribe')
																	.html(
																			'Payment successful <i class="fa fa-check"></i>');
														})
												.fail(
														function(jqXHR,
																textStatus,
																errorThrown) {
															$form
																	.find(
																			'.subscribe')
																	.html(
																			'There was a problem')
																	.removeClass(
																			'success')
																	.addClass(
																			'error');
															/* Show Stripe errors on the form */
															$form
																	.find(
																			'.payment-errors')
																	.text(
																			'Try refreshing the page and trying again.');
															$form
																	.find(
																			'.payment-errors')
																	.closest(
																			'.row')
																	.show();
														});
									}
								});
			}
			/* Fancy restrictive input formatting via jQuery.payment library*/
			$('input[name=cardNumber]').payment('formatCardNumber');
			$('input[name=cardCVC]').payment('formatCardCVC');
			$('input[name=cardExpiry').payment('formatCardExpiry');

			/* Form validation using Stripe client-side validation helpers */
			jQuery.validator.addMethod("cardNumber", function(value, element) {
				return this.optional(element)
						|| Stripe.card.validateCardNumber(value);
			}, "Please specify a valid credit card number.");

			jQuery.validator.addMethod("cardExpiry", function(value, element) {
				/* Parsing month/year uses jQuery.payment library */
				value = $.payment.cardExpiryVal(value);
				return this.optional(element)
						|| Stripe.card.validateExpiry(value.month, value.year);
			}, "Invalid expiration date.");

			jQuery.validator.addMethod("cardCVC",
					function(value, element) {
						return this.optional(element)
								|| Stripe.card.validateCVC(value);
					}, "Invalid CVC.");

			validator = $form.validate({
				rules : {
					cardNumber : {
						required : true,
						cardNumber : true
					},
					cardExpiry : {
						required : true,
						cardExpiry : true
					},
					cardCVC : {
						required : true,
						cardCVC : true
					}
				},
				highlight : function(element) {
					$(element).closest('.form-control').removeClass('success')
							.addClass('error');
				},
				unhighlight : function(element) {
					$(element).closest('.form-control').removeClass('error')
							.addClass('success');
				},
				errorPlacement : function(error, element) {
					$(element).closest('.form-group').append(error);
				}
			});

			paymentFormReady = function() {
				if ($form.find('[name=cardNumber]').hasClass("success")
						&& $form.find('[name=cardExpiry]').hasClass("success")
						&& $form.find('[name=cardCVC]').val().length > 1) {
					return true;
				} else {
					return false;
				}
			}

			$form.find('.subscribe').prop('disabled', true);
			var readyInterval = setInterval(function() {
				if (paymentFormReady()) {
					$form.find('.subscribe').prop('disabled', false);
					clearInterval(readyInterval);
				}
			}, 250);
		</script>




	<script type="text/javascript" src="js/shop.js"></script>
</body>
</html>