var hashExp = {};

var username = $("#user_nickname").text();

var bottonePartecipa = $("button#partecipa");
var bottoneAnnullaPartecipazione = $("button#annulla_partecipa");
var bottoneElimina = $("button#elimina");
var bottoneModifica = $("button#modifica");

var modalMostraExp = $("#mostra_experience");

function Experience(id, limiteAdesione, posti, descrizione, organizzatore,pernottamento,viaggio,concerto)
{
	this.id = id;
	this.limiteAdesione = limiteAdesione;
	this.posti = posti;
	this.descrizione = descrizione;
	this.nicknamePartecipanti = [];
	this.organizzatore = organizzatore;
	this.pernottamento=pernottamento;
	this.viaggio=viaggio;
	this.concerto=concerto;
}

var path = $(location).attr('pathname');

var url = path.split( '/' );

var last =  url[ url.length - 1 ];

var experienceRichiesta;

function dammiDettagliExperience(exp)
{
	
	experienceRichiesta = exp;
	//alert("ajax dettagli Experience (experience)")
	$.ajax({
		type: "POST",
		url: "MostraExperience",
		datatype: "json",
		data: JSON.stringify(exp),


		success : function(experienceOrganizzatore)
		{
			
			bottonePartecipa.hide();
			bottoneAnnullaPartecipazione.hide();
			bottoneElimina.hide();
			bottoneModifica.hide();

			var objs = JSON.parse(experienceOrganizzatore);
			var experience = objs[0];

			var partecipanti = experience.partecipanti;

			var organizzatore = objs[1].nickname;

			var divDati = $("div#dati");

			var posti = divDati.find("#posti");
			posti.val(experience.posti-partecipanti.length);
			posti.prop("readonly", true);
			posti.removeClass("modificabile");
			posti.unbind("dblclick");

			var pernottamento=divDati.find("#pernottamento");
			alert(pernottamento.val(experienceRichiesta.pernottamento));
			
			var viaggio=divDati.find("#viaggio");
			alert(viaggio.val(experienceRichiesta.viaggio));
			
			var concerto=divDati.find("#concerto");
			alert(concerto.val(experienceRichiesta.concerto));
			
			var dataLimiteAdesione = divDati.find("#dataLimiteAdesione");
			dataLimiteAdesione.val (experienceRichiesta.limiteAdesione);
			dataLimiteAdesione.prop("readonly", true);
			dataLimiteAdesione.removeClass("modificabile");
			dataLimiteAdesione.unbind("dblclick");

			var descrizione = divDati.find("#descrizione");
			descrizione.val(experience.descrizione);
			descrizione.prop("readonly", true);
			descrizione.removeClass("modificabile");
			descrizione.unbind("dblclick");

			divDati.find("p#organizzatore").text(organizzatore);
			var isPartecipante = false;
			var ulpartecipanti = divDati.find("ul#partecipanti");
			ulpartecipanti.empty();
			for(var i=0;i<partecipanti.length;i++)
			{
				var li = $('<li/>')
				.text(partecipanti[i].nickname)
				.appendTo(ulpartecipanti);
				if(partecipanti[i].nickname == username)
				{
					isPartecipante = true;
//					alert("sei un partecipante");
					bottoneAnnullaPartecipazione.show();
					bottoneAnnullaPartecipazione.on('click',function(){
						bottoneAnnullaPartecipazione.unbind('click');
						annullaPartecipazione(experience,organizzatore,username);
					});
				}
			}

			if(username == organizzatore)
			{

				bottoneElimina.show();
				bottoneElimina.on('click',function(){
					bottoneElimina.unbind('click');
					eliminaExp(experience,organizzatore);
				});
				
				var modificaPosti = false;
				var modificaDataLimite = false;
				var modificaDescrizione = false;

				
				var length = partecipanti.length;

				
				bottoneModifica.on('click',function(e) {
//					alert("click");
					bottoneModifica.unbind('click');
//					e.preventDefault();
					e.stopPropagation();
					
//					var length = partecipanti.length;
//					var postiInt = posti.val();

					if(modificaPosti)
						experience.posti = Number(Number(posti.val())+Number(length));
					if(modificaDataLimite)
						experience.limite_adesione = dataLimiteAdesione.val();
					if(modificaDescrizione)
						experience.descrizione = descrizione.val();

					modificaExp(experience,organizzatore);
//					}
//					bottoneModifica.bind('click');

				});
				

				posti.dblclick(function() {
					posti.prop("readonly", false);
					posti.addClass("modificabile");

					posti.change(function() {
						var postiInt = posti.val();
//						alert(postiInt+"  "+experience.posti);
						
							if ( (postiInt >= 0) && 
								(experience.posti != Number(Number(postiInt)+Number(length))) &&
								(Number(Number(postiInt)+Number(length)) >=1) )
						{
							modificaPosti=true;
							bottoneModifica.show();
//							alert("change posti "+modificaPosti);
						}
						else
						{
							alert("cambio ma non soddisfa i parametri richiesti");
							modificaPosti=false;
							if(!(modificaDescrizione || modificaDataLimite))
								bottoneModifica.hide();
						}
					});
				});
				dataLimiteAdesione.dblclick(function(){
					dataLimiteAdesione.prop("readonly", false);
					dataLimiteAdesione.addClass("modificabile");

					dataLimiteAdesione.change(function() {
						if( experience.limite_adesione != dataLimiteAdesione.val() )
						{
							modificaDataLimite=true;
							bottoneModifica.show();
						}
						else
						{
							modificaDataLimite=false;
							if(!(modificaPosti || modificaDescrizione))
								bottoneModifica.hide();
						}
					});
				});
				descrizione.dblclick(function(){
					descrizione.prop("readonly", false);
					descrizione.addClass("modificabile");

					descrizione.change(function() {
//						alert(experience.descrizione+"  "+descrizione.val());
						if( experience.descrizione != descrizione.val() )
						{
							modificaDescrizione = true;
							bottoneModifica.show();
						}
						else
						{
							modificaDescrizione=false;
							if(!(modificaPosti || modificaDataLimite))
								bottoneModifica.hide();
						}
					});
				});


			}
			else if(!isPartecipante && (experience.posti-partecipanti.length > 0) )
			{
				bottonePartecipa.show();
//				alert("LOGGATO = "+username);
				bottonePartecipa.on('click',function(){
					bottonePartecipa.unbind('click');
					partecipa(experience,organizzatore,username);
				});			
			}

		},
		error : function(experienceOrganizzatore)
		{
			alert("ERROR");
		}

	});
}


function partecipa(experience,organizzatore,userLoggato)
{
	
	$.ajax({
		type: "POST",
		url: "PartecipaExp",
		datatype: "text",
		data: JSON.stringify([experience,{organizzatore},{userLoggato}]),


		success : function(data)
		{
			if(data.valueOf() == "loggati")
			{
				$(location).attr('href', "/AliveMusic/login.jsp");
			}
			else
			{
//				modalMostraExp.modal("hide");
//				alert("partecipazione avvenuta con successo");

//				else
//				{

				dammiDettagliExperience(experienceRichiesta);					
//				modalMostraExp.modal("show");
//				}

				if(last == "LoginCheck" || last=="InserisciExperience" || last=="profilo.jsp")
				{	
					dammiExperience();
				}

			}
		},
		error : function(data)
		{
			alert("ERROR");
		}

	});
}


function annullaPartecipazione(experience,organizzatore,userLoggato)
{
	$.ajax({
		type: "POST",
		url: "AnnullaPartecipazione",
		datatype: "text",//non gli ritorna niente
		data: JSON.stringify([experience,{organizzatore},{userLoggato}]),


		success : function(data)
		{
//			alert("sei stato rimosso dalla partecipazione");

//			modalMostraExp.modal("hide");
//			else
//			{
			//non fa niente perche non riescen ad aprire il modal fade 
			// quindi vedere come fare ad aprire il modal , anche per partecipa
			dammiDettagliExperience(experienceRichiesta);
//			modalMostraExp.modal("show");

//			}



			if(last == "LoginCheck" || last=="InserisciExperience" || last=="profilo.jsp")
			{	
				dammiExperience();
			}	

		},
		error : function(data)
		{
			alert("ERROR");
		}

	});
}


function eliminaExp(experience,organizzatore)
{
	$.ajax({
		type: "POST",
		url: "EliminaExp",
//		datatype: "text",
		data: JSON.stringify([experience,{organizzatore}]),

		success : function()
		{
//			alert("rimozione dell'experience avvenuta con successo");

			modalMostraExp.modal("hide");
			if(last == "LoginCheck" || last=="InserisciExperience" || last=="profilo.jsp")
			{	
				dammiExperience();
			}	
			else
			{
				dammiExperienceAssociate(idEventoCliccato);
			}

		},
		error : function()
		{
			alert("ERROR");
		}

	});
}


function modificaExp(experience,organizzatore)
{
	$.ajax({
		type: "POST",
		url: "ModificaExp",
//		datatype: "text",
		data: JSON.stringify([experience,{organizzatore}]),

		success : function()
		{
//			alert("modifica dell'experience avvenuta con successo");

			modalMostraExp.modal("hide");
			if(last == "LoginCheck" || last=="InserisciExperience" || last=="profilo.jsp")
			{	
				dammiExperience();
			}
			else
			{
				dammiExperienceAssociate(idEventoCliccato);
			}

		},
		error : function()
		{
			alert("ERROR");
		}

	});

}

