$(document).ready(function() {

$("#myUL li.experience a").on('click',function(){
	$("#myUL li.experience a").not(this).removeClass("selected");
	$(this).addClass("selected");
	var id = $(this).attr("id");
	dammiDettagliExperience(hashExp[id]);
});

	if ($(".profile-userpic img").attr('src')==='')
	{
		$(".profile-userpic img").attr('src',"img/avatar.png");	
	}


	$('ul#profile li').on('click', function() {
		$('ul#profile li.active').removeClass('active');
		$(this).addClass('active');
	});

$("#FormEmail").submit(function(e) {
        
        $.ajax({
            type: "POST",
            url: "AggiornaMail",
            data: $("#FormEmail").serialize(), 
            success: function(data)
            {
                var email = $("#FormEmail").find("input");
                email.attr("placeholder",data);
                email.val("");
            },
            error: function(data)
            {
                alert("ERRORE NEL CAMBIO MAIL");
            }
        });
        e.preventDefault();
    });


    $("#FormPassword").submit(function(e) {
        
        $.ajax({
            type: "POST",
            url: "AggiornaPass",
            data: $("#FormPassword").serialize(), 
            success: function(data)
            { 
                var email = $("#FormPassword").find("input");
                email.attr("placeholder",data);
                email.val("");                
            },
            error: function(data)
            {
                alert("ERRORE NEL CAMBIO PASSWORD");
            }
        });
        e.preventDefault();
    });


    $("#FormResidenza").submit(function(e) {
        $.ajax({
            type: "POST",
            url: "AggiornaResidenza",
            data: $("#FormResidenza").serialize(), 
            success: function(data)
            {
                var email = $("#FormResidenza").find("input");
                email.attr("placeholder",data);
                email.val("");
            },
            error: function(data)
            {
                alert("ERRORE NEL CAMBIO RESIDENZA");
            }
        });
        e.preventDefault();
    });


    $("#FormBio").submit(function(e) {
        $.ajax({
            type: "POST",
            url: "AggiornaBio",
            data: $("#FormBio").serialize(), 
            success: function(data)
            { 
                var email = $("#FormBio").find("input");
                email.attr("placeholder",data);
                email.val("");                
            },
            error: function(data)
            {
                alert("ERRORE NEL CAMBIO BIO");
            }
        });
        e.preventDefault();
    });



	$(".boxinfo--experience").hide();
	$(".boxinfo--prodotti").hide();
	$(".boxinfo--posta").hide();
	$(".boxinfo--info").hide();
	$(".boxinfo--impostazioni").hide();


	$("#buttonbio").click(function(){
		$(".boxinfo--experience").hide();
		$(".boxinfo--prodotti").hide();
		$(".boxinfo--posta").hide();
		$(".boxinfo--info").hide();
		$(".boxinfo--bio").slideDown(800);
		$(".boxinfo--impostazioni").hide();
	});
	$("#buttonaccount").click(function(){
		$(".boxinfo--experience").hide();
		$(".boxinfo--prodotti").hide();
		$(".boxinfo--posta").hide();
		$(".boxinfo--info").hide();
		$(".boxinfo--bio").hide();
		$(".boxinfo--impostazioni").slideDown(800);
	});
	$("#buttonexperience").click(function(){
		$(".boxinfo--experience").slideDown(800);
		$(".boxinfo--prodotti").hide();
		$(".boxinfo--posta").hide();
		$(".boxinfo--info").hide();
		$(".boxinfo--bio").hide();
		$(".boxinfo--impostazioni").hide();
	});
	$("#buttonposta").click(function(){
		$(".boxinfo--experience").hide();
		$(".boxinfo--prodotti").hide();
		$(".boxinfo--posta").slideDown(800);
		$(".boxinfo--info").hide();
		$(".boxinfo--bio").hide();
		$(".boxinfo--impostazioni").hide();
	});
	$("#buttoninfo").click(function(){
		$(".boxinfo--experience").hide();
		$(".boxinfo--prodotti").hide();
		$(".boxinfo--posta").hide();
		$(".boxinfo--info").slideDown(800);
		$(".boxinfo--bio").hide();
		$(".boxinfo--impostazioni").hide();
	});
	$("#buttonprodotti").click(function(){
		$(".boxinfo--experience").hide();
		$(".boxinfo--prodotti").slideDown(800);
		$(".boxinfo--posta").hide();
		$(".boxinfo--info").hide();
		$(".boxinfo--bio").hide();
		$(".boxinfo--impostazioni").hide();
	});
});


$("#inviamess").click(function(){
	alert("Messaggio inviato!");
});

//function Messaggio(id, mittente, destinatario, dataInvio, oggetto, testo)
//{
//	this.id = id;
//	this.mittente = mittente;
//	this.destinatario = destinatario;
//	this.dataInvio = dataInvio;
//	this.oggetto = oggetto;
//	this.testo = testo;
//}
//
//function Prodotto(id,nome,prezzo,colore,tipo,descrizione,quantita,realizzatore,immagine){
//	
//	this.id=id;
//	this.nome=nome;
//	this.prezzo=prezzo;
//	this.colore=colore;
//	this.tipo=tipo;
//	this.descrizione=descrizione;
//	this.quantita=quantita;
//	this.realizzatore=realizzatore;
//	this.immagine=immagine;
//	
//}

var hashMex = {};


var nickname = $("#user_nick").text();

var experienceShow = $("#buttonexperience");

experienceShow.on('click',function(){
	dammiExperience();
});

var prodottiShow = $("#buttonprodotti");

prodottiShow.on('click',function(){
	//alert("dammi porododoti click");
	//alert("dammi porododoti click");
	dammiProdotti();
})

var postaShow = $("#buttonposta");

postaShow.on('click',function(){
	dammiMessaggi();
});

function dammiExperience()
{
//	//alert("dammi EXP profilo")
	dammiExperienceCreate();
	dammiExperiencePartecipa();
}

function dammiMessaggi()
{
//	alert("ajax messaggi ()")
	$.ajax({
		type: "POST",
		url: "DammiMessaggi",
		datatype: "json",
		
		success : function(messaggiJson)
		{
			var messaggi = JSON.parse(messaggiJson);
			
			var messaggiInviati = messaggi[0];
			var messaggiRicevuti = messaggi[1];
			
			var mexInvList = $('ul.messaggiInviati')
			mexInvList.empty();
			for(var i=0;i<messaggiInviati.length;i++)
			{
				var li = $('<li/>')
				.addClass("messaggioInviato")
				.appendTo(mexInvList);

				var dateArr = messaggiInviati[i].data_invio.split("-");
				var date = dateArr[2]+"/"+dateArr[1]+"/"+dateArr[0];
				
				var aaa = $('<a/>')
				.attr("data-toggle", "modal")
				.attr("data-target", "#mostraMessaggio")
				.html("<p id=\"titolista\" style=\"display:inline-block\">Destinatario:</p>"+messaggiInviati[i].destinatario.nickname+"<p id=\"titolista\">| Data:<p style=\"display:inline-block\">"+messaggiInviati[i].data_invio+"</p><p id=\"titolista\" style=\"display:inline-block\">|Oggetto:<p style=\"display:inline-block\">"+messaggiInviati[i].oggetto +"</p><br><p id=\"titolista\" style=\"font-weight:bold; display:inline-block;\">Testo: </p> <p style=\"display:inline-block\">"+messaggiInviati[i].testo+"</p>")
				.attr("id",messaggiInviati[i].ID)
				.appendTo(li);
				var mex = 
				{
						id : messaggiInviati[i].ID,
						mittente : messaggiInviati[i].mittente.nickname,
						destinatario : messaggiInviati[i].destinatario.nickname,
						dataInvio : date,
						oggetto : messaggiInviati[i].oggetto,
						testo : messaggiInviati[i].testo			
				};
				hashMex[messaggiInviati[i].ID] = mex;
			}
	
			var mexRicList = $('ul.messaggiRicevuti')
			mexRicList.empty();
			for(var i=0;i<messaggiRicevuti.length;i++)
			{
				var li = $('<li/>')
				.addClass("messaggioRicevuto")
				.appendTo(mexRicList);

				var dateArr = messaggiRicevuti[i].data_invio.split("-");
				var date = dateArr[2]+"/"+dateArr[1]+"/"+dateArr[0];

				var aaa = $('<a/>')
				.attr("data-toggle", "modal")
				.attr("data-target", "#mostraMessaggio")
				.html("<p id=\"titolista\" style=\"display:inline-block; font-weight:bold;\">Mittente:</p><p style=\"display:inline-block;\" >"+messaggiRicevuti[i].mittente.nickname+"</p><p id=\"titolista\" style=\"display:inline-block;\">|Data:</p><p style=\"display:inline-block;\">"+messaggiRicevuti[i].data_invio+"</p><p id=\"titolista\"> |Oggetto:</p>"+messaggiRicevuti[i].oggetto+"<br><p id=\"titolista\" style=\"font-weight:bold; display:inline-block;\">Testo: </p> <p style=\"display:inline-block\">"+messaggiRicevuti[i].testo+"</p>")
				.attr("id",messaggiRicevuti[i].ID)
				.appendTo(li);

				var mex = 
				{
						id : messaggiRicevuti[i].ID,
						mittente : messaggiRicevuti[i].mittente.nickname,
						destinatario : messaggiRicevuti[i].destinatario.nickname,
						dataInvio : date,
						oggetto : messaggiRicevuti[i].oggetto,
						testo : messaggiRicevuti[i].testo			
				};
				hashMex[messaggiRicevuti[i].ID] = mex;
			}
			$("#myUL li.messaggioRicevuto a").on('click',function(){
				$("#myUL li.messaggioRicevuto a").not(this).removeClass("selected");
				$(this).addClass("selected");
				var id = $(this).attr("id");
				var dati = $("div#dati");
				dati.find("#utenteTipo").text("MITTENTE");
				dati.find("#utente").text(hashMex[id].mittente);
				dati.find("#data").text(hashMex[id].dataInvio);
				dati.find("#oggetto").text(hashMex[id].oggetto);
				dati.find("#testo").text(hashMex[id].testo);
			});
		
			$("#myUL li.messaggioInviato a").on('click',function(){
				$("#myUL li.messaggioInviato a").not(this).removeClass("selected");
				$(this).addClass("selected");
				var id = $(this).attr("id");
				var dati = $("div#dati");
				dati.find("#utenteTipo").text("DESTINATARIO");
				dati.find("#utente").text(hashMex[id].destinatario);
				dati.find("#data").text(hashMex[id].dataInvio);
				dati.find("#oggetto").text(hashMex[id].oggetto);
				dati.find("#testo").text(hashMex[id].testo);
			});
		},
		error : function(messaggiJson)
		{
			alert("ERROR");
		}

	});
}

function dammiProdotti()
{
	dammiProdottiCreati();
	dammiProdottiAcquistati();
}

function dammiProdottiAcquistati()
{
	//alert("AJAX PRODOTTI UTENTE ACquistati");
	$.ajax({
		type: "POST",
		url: "ProdottiAcquistati",
		datatype: "json",
		data: {"utente":nickname},
		
		success : function(prodottiJson)
		{
			
			var prodottiAcquistati = JSON.parse(prodottiJson);
			
			var prodAcquistatiList = $('ul.prodottiAcquistati')
			prodAcquistatiList.empty();
			for(var i=0;i<prodottiAcquistati.length;i++)
			{
				var li = $('<li/>')
				.addClass("prodottoAcquistato")
				.appendTo(prodAcquistatiList);

				var aaa = $('<a/>')
				.attr("data-toggle", "modal")
				.attr("data-target", "#mostraProdottoAcquistato")
				.html("<p id=titolista>Nome:<p>"+prodottiAcquistati[i].nome+"<p id=titolista>Prezzo:<p>"+prodottiAcquistati[i].prezzo+ "<p id=titolista>Descrizione:<p>"+prodottiAcquistati[i].descrizione +"<p id=titolista>Quantita:<p>"+prodottiAcquistati[i].quantita+"<p id=titolista>Taglia:<p>"+prodottiAcquistati[i].taglia)
				.attr("id",prodottiAcquistati[i].ID)
				.appendTo(li);

				var prod = 
				{
						id : prodottiAcquistati[i].ID,
						nome : prodottiAcquistati[i].nome,
						prezzo: prodottiAcquistati[i].prezzo,
						colore: prodottiAcquistati[i].colore,
						tipo: prodottiAcquistati[i].tipo,
						descrizione: prodottiAcquistati[i].descrizione,
						quantita: prodottiAcquistati[i].quantita,
						immagine: prodottiAcquistati[i].immagine,
						taglia : prodottiAcquistati[i].taglia
				};
				
				
				hashProd[prodottiAcquistati[i].ID] = prod;
			}
			
			$("#myUL li.prodottoCreato a").on('click',function()
			{
				$("#myUL li.prodottoCreato a").not(this).removeClass("selected");
				$(this).addClass("selected");
				var id = $(this).attr("id");
				var dati = $("div#dati");
				dati.find("#id_p").text(hashProd[id].id);
				dati.find("#nome_p").text(hashProd[id].nome);
				dati.find("#descrizione_p").text(hashProd[id].descrizione);
				dati.find("#immagine_p").attr("src",hashProd[id].immagine);
			});
		
			$("#myUL li.prodottoAcquistato a").on('click',function()
			{
				$("#myUL li.prodottoAcquistato a").not(this).removeClass("selected");
				$(this).addClass("selected");
				var id = $(this).attr("id");
				var dati = $("div#datiAcquisto");
				dati.find("#id_p").text(hashProd[id].nome);
				dati.find("#nome_p").text(hashProd[id].prezzo);
				dati.find("#descrizione_p").text(hashProd[id].descrizione);
				dati.find("#immagine_p").text(hashProd[id].immagine);
			});
		},
		error : function(prodottiJson)
		{
			alert("ERROR");
		}
	});
}


function dammiExperiencePartecipa()
{
	$.ajax({
		type: "POST",
		url: "experiencePartecipate",
		datatype: "json",
		data: {"utente":nickname},

		success : function(experiencesJson)
		{
			var objs = JSON.parse(experiencesJson);

			var expList = $('ul.experiencesPartecipate')
			expList.empty();
			for (var i = 0, len = objs.length; i < len; i++)
			{
				var li = $('<li/>')
				.addClass("experience")
				.appendTo(expList);

				var dateJAVA = objs[i].limite_adesione;
				var dateArr = dateJAVA.split(" ");
				var meseChar = dateArr[1];
				var meseNum;
				switch (meseChar) {
				case "Jan":
					meseNum = "01";
					break;
				case "Feb":
					meseNum = "02";
					break;
				case "Mar":
					meseNum = "03";
					break;
				case "Apr":
					meseNum = "04";
					break;
				case "May":
					meseNum = "05";
					break;
				case "Jun":
					meseNum = "06";
					break;
				case "Jul":
					meseNum = "07";
					break;
				case "Aug":
					meseNum = "08";
					break;
				case "Sep":
					meseNum = "09";
					break;
				case "Oct":
					meseNum = "10";
					break;
				case "Nov":
					meseNum = "11";
					break;
				case "Dec":
					meseNum = "12";
					break;
				default:
					break;
				}
				
				var date = dateArr[2]+"/"+meseNum+"/"+dateArr[5];

				var dateForExp = dateArr[5]+"-"+meseNum+"-"+dateArr[2];

				
				var aaa = $('<a/>')
				.attr("data-toggle", "modal")
				.attr("data-target", "#mostra_experience")
				.html("<p id=titolista>Codice: </p>"+objs[i].ID+"<p id =titolista> | Posti: </p>"+objs[i].posti+"<p id=titolista> | Descrizione: </p>"+objs[i].descrizione +"<br></br> <p id=titolista>Limite adesione: </p>"+date)
				.attr("id",objs[i].ID)
				.appendTo(li);		
				var exp = 
				{
						id : objs[i].ID,
						limiteAdesione : dateForExp,
						posti : objs[i].posti,
						descrizione : objs[i].descrizione,				
				};		
				hashExp[objs[i].ID] = exp;
			}
			$("#myUL li.experience a").on('click',function(){
				$("#myUL li.experience a").not(this).removeClass("selected");
				$(this).addClass("selected");
				var id = $(this).attr("id");
				dammiDettagliExperience(hashExp[id]);
			});

		},
		error : function(experiencesJson)
		{
			alert("ERROR");
		}

	});
}