$(document).ready(function() {

	dammiArtisti();

});

var hashArtist = {};
var artistaCliccato;

function Artista(nome, sito_web, genere, immagine_band, copertina_band,bio)
{
	this.nome = nome;
	this.sito_web = sito_web;
	this.eventi = [];
	this.genere = genere;
	this.immagine_band = immagine_band;
	this.copertina_band = copertina_band;
	this.bio=bio;
}
function Evento(id,data)
{
	this.id = id;
//	this.data = data;
}

function dammiArtisti()
{
//	alert("ajax dammi artisti ()");
	$.ajax({
		type: "GET",
		url: "artisti",
		datatype: "json",
		async : false,

		success : function(artistiJson)
		{

			var artisti = JSON.parse(artistiJson);

			var dati = $("div#artisti");

			for(var i=0;i<artisti.length;i++)
			{
				var a = $('<a/>')
				.addClass("list--item")
				.attr("href","artistaSingolo.jsp")
				.attr("id",artisti[i].nome)
				.appendTo(dati);

				var article = $('<article/>')
				.appendTo(a);

				var img = $('<img/>')
				.attr("src",artisti[i].immagine_band)
				.appendTo(article);

				var br = $('<br>')
				.appendTo(a);

				var p = $("<p/>")
				.html(artisti[i].nome)
				.addClass("nomiband")
				.appendTo(article);
				
				var br2 = $('<br/>')
				.appendTo(a);
				
				var gen = $('<p/>')
				.html(artisti[i].genere)
				.addClass("genere")
				.appendTo(article);

				var art = 
				{
						nome : artisti[i].nome,
						sito_web  : artisti[i].sito_web,
						genere  : artisti[i].genere,
						immagine_band  : artisti[i].immagine_band,
						copertina_band : artisti[i].copertina_band,
						bio: artisti[i].bio,
						eventi : []
				};		
				for(var x=0;x<artisti[i].eventi.length;x++)
				{	
					var ev = 
					{
						id : artisti[i].eventi[x].ID,
						data : artisti[i].eventi[x].data // null perchè il proxy non lo setta
					}
					art.eventi.push(ev);
				}
				hashArtist[artisti[i].nome] = art;

			}//parentesi for

			$(dati).find("a").on('click',function(){

//				$(dati).find("a").not(this).removeClass("selected");
//				$(this).addClass("selected");
				var artistaCliccato = $(this).attr("id");

				setArtista(hashArtist[artistaCliccato]);

//				for(var idEv in hashArtist[id].eventiId)
//				{
//				dammiEventi();  la funzione che manca con ajax 
//				con location artista evento boh
//				}
			});
		},
		error : function(artistiJson)
		{
			alert("ERROR");
		}

	});
}


function setArtista(artista)
{
//	alert("ajax artisti inutile ()");
	$.ajax({
		type: "POST",
		url: "PassaArtista",
		data: JSON.stringify(artista),
		

		success : function(artistaJson)
		{

			
		},
		error : function(artistiJson)
		{
			alert("ERROR");
		}

	});
}
