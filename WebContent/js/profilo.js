var hashProd = {};


function dammiExperienceCreate()
{
	//alert("ajax experience Create ()");
	$.ajax({
		type: "POST",
		url: "experienceCreate",
		datatype: "json",
		data: {"utente":nickname},

		success : function(experiencesJson)
		{
			var objs = JSON.parse(experiencesJson);

			var expList = $('ul.experiencesCreate')
			expList.empty();
			for (var i = 0, len = objs.length; i < len; i++)
			{
				var li = $('<li/>')
				.addClass("experience")
				.appendTo(expList);

				var dateArr = objs[i].limite_adesione.split("-");
				var date = dateArr[2]+"/"+dateArr[1]+"/"+dateArr[0];

				var aaa = $('<a/>')
				.attr("data-toggle", "modal")
				.attr("data-target", "#mostra_experience")
				.html("<p id=titolista>Codice: </p>"+objs[i].ID+"<p id =titolista> | Posti: </p>"+objs[i].posti+"<p id=titolista> | Descrizione: </p>"+objs[i].descrizione +"<br></br> <p id=titolista>Limite adesione: </p>"+date)
				.attr("id",objs[i].ID)
				.appendTo(li);		
				var exp = 
				{
						id : objs[i].ID,
						limiteAdesione : objs[i].limite_adesione,
						posti : objs[i].posti,
						descrizione : objs[i].descrizione
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
function dammiProdottiCreati()
{
	//alert("AJAX PRODOTTI UTENTE");
	$.ajax({
		type: "POST",
		url: "ProdottiCreati",
		datatype: "json",
		data: {"utente":nickname},

		success : function(prodottiJson)
		{

			var prodottiCreati = JSON.parse(prodottiJson);


			var prodCreatiList = $('ul.prodottiCreati')
			prodCreatiList.empty();

			var prodottiCreatiPerTaglia = {};
			
			for(var i=0;i<prodottiCreati.length;i++)
			{
				var prod = 
				{
						nome : prodottiCreati[i].nome,
						prezzo : prodottiCreati[i].prezzo,
						tipo : prodottiCreati[i].tipo,
						descrizione : prodottiCreati[i].descrizione,
						immagine : prodottiCreati[i].immagine,
						colore : prodottiCreati[i].colore,
						quantita : [],
						taglie : []
				}

				if(prod.nome in prodottiCreatiPerTaglia)
				{	
					prodottiCreatiPerTaglia[prod.nome].taglie.push(prodottiCreati[i].taglia);
					prodottiCreatiPerTaglia[prod.nome].quantita.push(prodottiCreati[i].quantita);
				}
				else
				{
					prod.taglie.push(prodottiCreati[i].taglia);
					prod.quantita.push(prodottiCreati[i].quantita);
					prodottiCreatiPerTaglia[prodottiCreati[i].nome] = prod;
				}
			}

			for(var i in prodottiCreatiPerTaglia)
			{
				var li = $('<li/>')
				.addClass("prodottoCreato")
				.appendTo(prodCreatiList);

				var aaa = $('<a/>')
				.attr("data-toggle", "modal")
				.attr("data-target", "#mostraProdotto")
				.html("<p id=titolista>Nome:<p>"+prodottiCreatiPerTaglia[i].nome+"<p id=titolista>Prezzo:<p>"+prodottiCreatiPerTaglia[i].prezzo+ "<p id=titolista>Descrizione:<p>"+prodottiCreatiPerTaglia[i].descrizione)
				.attr("id",prodottiCreatiPerTaglia[i].nome)
				.appendTo(li);
				//alert("asdioads");
			}
			

			$("#myUL li.prodottoCreato a").on('click',function()
					{
				$("#myUL li.prodottoCreato a").not(this).removeClass("selected");
				$(this).addClass("selected");
				var id = $(this).attr("id");
				var dati = $("div#dati");
				dati.find("#id_p").text(prodottiCreatiPerTaglia[id].nome);
				dati.find("#nome_p").text(prodottiCreatiPerTaglia[id].prezzo);
				dati.find("#descrizione_p").text(prodottiCreatiPerTaglia[id].descrizione);
				dati.find("#immagine_p").attr("src",prodottiCreatiPerTaglia[id].immagine);				
					});

		},
		error : function(prodottiJson)
		{
			alert("ERROR");
		}
	});
	
}