$(document).ready(function() {

//	dammiArtisti();

	takeArtista();
});


function takeArtista()
{
	alert("ajax take artista ()");
	$.ajax({
		type: "GET",
		url: "PassaArtista",
		datatype: "json",

		success : function(artistaJson)
		{
			var artista = JSON.parse(artistaJson);

			$("#nome").text(artista.nome);
			$("#genere").text(artista.genere);
			$("#sitoWeb").text(artista.sito_web);
			$("#bio").text(artista.bio);
			$("#immagineBand").attr("src",artista.immagine_band);
			$("#immagineCopertina").attr("src",artista.copertina_band);

			dammiEventiArtista(artista);
		},
		error : function(artistiJson)
		{
			alert("ERROR");
		}

	});
}


function dammiEventiArtista(artista)
{
	alert("ajax tutti eventi dell artista");
	$.ajax({
		type: "POST",
		url: "DammiEventiArtista",
		datatype: "json",
		data: JSON.stringify(artista),
		async: false,

		success : function(eventiJson)
		{

			var objs = JSON.parse(eventiJson);

			var listaEv = $("ul.eventi");
			listaEv.empty();
			for(var i=0; i< objs.length; i+=3)
			{
				var li = $('<li/>')
				.addClass("event")
				.appendTo(listaEv);

				var artisti = "";
				for(var ai= 0;ai<objs[i+2].length;ai++)
				{
					if(! artisti=="")
						artisti += ", ";
					artisti += (objs[i+2][ai].nome);
				}
				
				var dateJAVA = objs[i].data;
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

				var aaa = $('<a/>')

				//.text("data="+date+" location="+objs[i+1].nome+" artisti="+artisti)
				.html("<p id=titolista> Data: </p> "+date+"<p id=titolista> | Citta: </p>"+objs[i+1].citta+"<br></br> <p id=titolista> Artisti: </p>"+artisti +"<p id=titolista> | Location: </p>"+objs[i+1].nome)

				.attr("id",objs[i].ID)
				.appendTo(li);
			}

			$("#myUL li.event a").on('click',function(){
				$("#myUL li.event a").not(this).removeClass("selected");
				$(this).addClass("selected");
//				$("#creaExperience").show();
//				$("#idEvento").val($(this).attr("id"));
				dammiExperienceAssociate($(this).attr("id"));
			});


		},
		error : function(eventiJson)
		{
			alert("ERROR");
		}

	});
}
