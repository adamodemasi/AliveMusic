$(document).ready(function() {

	dammiTuttiProdotti();

	$("#buttonCompraCarrello").on('click',function(){
		compraCarrello();
	});

	$("#carrello").on('click',function(){
		mostraCarrello();
	});


	$("#orderPriceTshirt").on('click',function(){
		var ordinamento = $("#orderPriceTshirt").text();
		if(ordinamento == "Ordina per prezzo: Crescente")
		{
			$("#orderPriceTshirt").text("Ordina per prezzo: Decrescente");
			ordinamento = "ASC";
		}
		else if(ordinamento=="Ordina per prezzo: Decrescente")
		{
			$("#orderPriceTshirt").text("Ordina per prezzo: Crescente");
			ordinamento = "DESC";
		}
		ordinaPrezzo("tshirt",ordinamento);
	});

	$("#orderPriceFelpe").on('click',function(){
		var ordinamento = $("#orderPriceFelpe").text();
		if(ordinamento == "Ordina per prezzo: Crescente")
		{
			$("#orderPriceFelpe").text("Ordina per prezzo: Decrescente");
			ordinamento = "ASC";
		}
		else if(ordinamento=="Ordina per prezzo: Decrescente")
		{
			$("#orderPriceFelpe").text("Ordina per prezzo: Crescente");
			ordinamento = "DESC";
		}
		ordinaPrezzo("felpa",ordinamento);
	});


});

var hashProdotti = {};


function riempiCarosello()
{

//	prende l'array e lo ordina e prende i primi elementi


	// inserisce i sei elementi nel carosello

	var sortable = [];

//	for(var x in hashProdotti)

	for (var key  in hashProdotti) 
		sortable.push({name:key,value: hashProdotti[key].quantita});

	var sorted = sortable.sort(function(a, b) {
		return (a.value > b.value) ? 1 : ((b.value > a.value) ? -1 : 0)
	});

//	for(var i in sorted)


	for(var i=0;i<=5;i++)
	{
		var ind = i+1;
		var prod = $("#prodotto"+ind);
		var img =  prod.find("img");
		img.attr("src",hashProdotti[sorted[i].name].immagine);


		var p = prod.find("p");
		p.attr("id",hashProdotti[sorted[i].name].nome);
	}

}

function riempiTab()
{
	var divProdotti = $("#prodotti");
	var divRowFelpe = $("#menufelpe").find("#felpe");
	var divRowTshirt = $("#menutshirt").find("#tshirt");
	var divCol;
	var divthumbnail;

	divRowFelpe.empty();
	divRowTshirt.empty();
	for(var p in hashProdotti)
	{
		if(hashProdotti[p].tipo == "felpa")
		{
			divCol = $('<div/>')
			.addClass("col-xs-2")
			.addClass("singoloProdotto")
			.appendTo(divRowFelpe);

			divthumbnail = $('<div/>')
			.addClass("thumbnail")
			.attr("data-toggle", "modal")
			.attr("data-target", "#product_view")
			.attr("id","felpa")
			.appendTo(divCol);
		}
		else if (hashProdotti[p].tipo == "tshirt")
		{
			divCol = $('<div/>')
			.addClass("col-xs-2")
			.addClass("singoloProdotto")
			.appendTo(divRowTshirt);

			divthumbnail = $('<div/>')
			.addClass("thumbnail")
			.attr("data-toggle", "modal")
			.attr("data-target", "#product_view")
			.attr("id","tshirt")
			.appendTo(divCol);
		}

		var immagine = $('<img>')
		.attr("src", hashProdotti[p].immagine)
		.attr("alt", "immagine prodotto")
		.addClass("image")
		.appendTo(divthumbnail);

		var divDettagli = $('<div/>')
		.addClass("dettagliprodotto")
		.appendTo(divthumbnail);

		var p = $('<p/>')
		.attr("id",hashProdotti[p].nome)
		.text(""+hashProdotti[p].prezzo+" €")
		.appendTo(divDettagli);


//		var pPrezzo = $('<span/>')
//		.val(" adslkdlsalkdsak ")
//		.appendTo(p);

	}
}

function dammiTuttiProdotti() {
	hashProdotti = {};

	$.ajax({
		type : "POST",
		url : "DammiProdotti",
		datatype : "json",

		success : function(prodottiJson) {
			var prodotti = JSON.parse(prodottiJson);


			for (var i = 0; i < prodotti.length; i++) 
			{
				var prod = 
				{
						nome : prodotti[i].nome,
						prezzo : prodotti[i].prezzo,
						tipo : prodotti[i].tipo,
						descrizione : prodotti[i].descrizione,
						immagine : prodotti[i].immagine,
						colore : prodotti[i].colore,
						quantita : [],
						taglie : []
				}

				if(prod.nome in hashProdotti)
				{	
					hashProdotti[prod.nome].taglie.push(prodotti[i].taglia);
					hashProdotti[prod.nome].quantita.push(prodotti[i].quantita);
				}
				else
				{
					prod.taglie.push(prodotti[i].taglia);
					prod.quantita.push(prodotti[i].quantita);
					hashProdotti[prodotti[i].nome] = prod;
				}
			}

			riempiCarosello();
			riempiTab();

			$(".singoloProdotto").on("click",function(){
				var nameProduct = $(this).find("p").attr("id");
				mostraProdotto(hashProdotti[nameProduct]);
			});

		},
		error : function(prodottiJson) {
			alert("ERROR");
		}

	});

}


function mostraProdotto(prodotto)
{
	$("#product_view #buttonCompra").prop("disabled",true);
	$("#product_view #buttonCarrello").prop("disabled",true);


	$("#product_view").find("#nomeProdotto").text(prodotto.nome);
	$("#product_view").find("#descrProdotto").text(prodotto.descrizione);
	$("#product_view").find("#coloreProdotto").text(prodotto.colore);
	$("#product_view").find("#prezzoProdotto").text(prodotto.prezzo+" €");
	$("#product_view").find("#immagineProdotto").attr("src",prodotto.immagine);
	var select = $("#product_view").find("#taglieProdotto");

	select.empty();

	var optionTmp = $("<option/>")
	.text("SIZE")
	.appendTo(select);


	for(var i=0;i<prodotto.taglie.length;i++)
	{
		var option = $("<option/>")
		.attr("value",i)
		.text(prodotto.taglie[i])
		.appendTo(select);
	}

	var selectQnt = $("#product_view").find("#quantitaProdotto");
	selectQnt.empty();

	optionTmp = $("<option/>")
	.text("QNT")
	.appendTo(selectQnt);	

	var prodottoSelezionato ;

	select.change(function(){
		var indice = select.find("option:selected").val();
		selectQnt.empty();

		optionTmp = $("<option/>")
		.text("QNT")
		.appendTo(selectQnt);	


		for(var i=1;i<=prodotto.quantita[indice];i++)
		{
			var option = $("<option/>")
			.text(i)
			.appendTo(selectQnt);
		}

		prodottoSelezionato = 
		{
				nome : prodotto.nome,
				prezzo : prodotto.prezzo,
				tipo : prodotto.tipo,
				descrizione : prodotto.descrizione,
				immagine : prodotto.immagine,
				colore : prodotto.colore,
				quantita : selectQnt.find("option:selected").text(),
				taglia : select.find("option:selected").text()
		}

		if(prodottoSelezionato.quantita =="QNT" || 
				prodottoSelezionato.taglia == "SIZE" )
		{
			$("#product_view #buttonCompra").prop("disabled",true);
			$("#product_view #buttonCarrello").prop("disabled",true);
		}
		else 
		{
			$("#product_view #buttonCompra").prop("disabled",false);
			$("#product_view #buttonCarrello").prop("disabled",false);			
		}	

	});

	selectQnt.change(function(){
		prodottoSelezionato = 
		{
				nome : prodotto.nome,
				prezzo : prodotto.prezzo,
				tipo : prodotto.tipo,
				descrizione : prodotto.descrizione,
				immagine : prodotto.immagine,
				colore : prodotto.colore,
				quantita : selectQnt.find("option:selected").text(),
				taglia : select.find("option:selected").text()
		}

		if(prodottoSelezionato.quantita =="QNT" || 
				prodottoSelezionato.taglia == "SIZE" )
		{
			$("#product_view #buttonCompra").prop("disabled",true);
			$("#product_view #buttonCarrello").prop("disabled",true);
		}
		else if(prodottoSelezionato.quantita > 0)
		{
			$("#product_view #buttonCompra").prop("disabled",false);
			$("#product_view #buttonCarrello").prop("disabled",false);			
		}	


	});

	$("#product_view #buttonCompra").on("click",function(){
		$("#product_view #buttonCompra").unbind('click');
		compra(prodottoSelezionato);
	});

	$("#product_view #buttonCarrello").on("click",function(){
		$("#product_view #buttonCarrello").unbind('click');
		aggiungiAlCarrello(prodottoSelezionato);
	});

}

function compra(prodotto)
{
	$.ajax({
		type: "POST",
		url: "AcquistaProdotto",
		data: JSON.stringify(prodotto),

		success : function(data)
		{
			alert("Acquisto avvenuto con successo");
			dammiTuttiProdotti();
			$("#product_view").modal("hide");
		},
		error: function(data)
		{
			alert("error");
		}
	});

}

function aggiungiAlCarrello(prodotto)
{

	$.ajax({
		type: "POST",
		url: "AggiungiAlCarrello",
		data: JSON.stringify(prodotto),

		success : function(data)
		{
			$("#product_view").modal("hide");
		},
		error: function(data)
		{
			alert("error");
		}
	});
}

function mostraCarrello()
{

	$.ajax({
		type: "POST",
		url: "MostraCarrello",
		datatype : "json",

		success : function(data)
		{
			var prodotti = JSON.parse(data);

			var ul = $("#contenutocarrello");
			ul.empty();

			var prezzo  = $("#prezzoTotale");

			var p = 0;

			for(var i = 0;i< prodotti.length; i++)
			{
				var li = $('<li/>')
				.addClass("prodottoCarrello")
				.appendTo(ul);

				var p1 = $('<p/>')
				.css("font-weight","bold")
				.css("display","inline-block")
				.css("color","#660000")
				.text(prodotti[i].nome)
				.appendTo(li);

				var p2 = $('<p/>')
				.css("font-weight","bold")
				.css("display","inline-block")
				.css("color","#000000")
				.text(" | Taglia: ")
				.appendTo(li);

				var p3 = $('<p/>')
				.css("font-weight","bold")
				.css("display","inline-block")
				.css("color","#660000")
				.text(prodotti[i].taglia)
				.appendTo(li);

				var p4 = $('<p/>')
				.css("font-weight","bold")
				.css("display","inline-block")
				.css("color","#000000")
				.text(" | Quantita: ")
				.appendTo(li);

				var p5 = $('<p/>')
				.css("font-weight","bold")
				.css("display","inline-block")
				.css("color","#660000")
				.text(prodotti[i].quantita)
				.appendTo(li);

				var p6 = $('<p/>')
				.css("font-weight","bold")
				.css("display","inline-block")
				.css("color","#000000")
				.text(" | Prezzo: ")
				.appendTo(li);

				var p7 = $('<p/>')
				.css("font-weight","bold")
				.css("display","inline-block")
				.css("color","#660000")
				.text(prodotti[i].prezzo+"€")
				.appendTo(li);

				var p8 = $('<p/>')
				.css("font-weight","bold")
				.css("display","inline-block")
				.css("color","#000000")
				.text(" | Tot. ")
				.appendTo(li);

				var p9 = $('<p/>')
				.css("font-weight","bold")
				.css("display","inline-block")
				.css("color","#660000")
				.text(prodotti[i].quantita*prodotti[i].prezzo)
				.appendTo(li);

				var p10 = $('<p/>')
				.css("font-weight","bold")
				.css("display","inline-block")
				.css("color","#000000")
				.text("€ ")
				.appendTo(li);

				var a = $('<a/>')
				.css("font-weight","bold")
				.addClass("deleteMe")
				.attr("id",i)
				.text("  X")
				.appendTo(li);

				p += (prodotti[i].prezzo * prodotti[i].quantita);
			}

			ul.find(".prodottoCarrello a").on('click',function(){
				var elementId = $(this).attr("id");
				rimuoviDalCarrello(prodotti[elementId]);
			});

			prezzo.text(p+"€");

			if(prodotti.length==0)
				$("#buttonCompraCarrello").prop("disabled",true);
			else
				$("#buttonCompraCarrello").prop("disabled",false);


		},
		error : function(data)
		{
			alert("errore");
		}

	});
}


function compraCarrello()
{
	$.ajax({
		type: "POST",
		url: "AcquistaCarrello",
		datatype: "json",

		success : function(data)
		{
			var prodottiNonAcquistati = JSON.parse(data);

			if(prodottiNonAcquistati.length==0)
				alert("Acquisti effettuati con successo");
			for(var i=0;i<prodottiNonAcquistati.length;i++)
			{
				alert("Non è stato possibile comprare "+prodottiNonAcquistati[i].nome+" in quantita di "+prodottiNonAcquistati[i].quantita);
			}

			dammiTuttiProdotti();
		},
		error : function(data)
		{
			alert("error");
		}
	});
}


function rimuoviDalCarrello(prodotto)
{

	$.ajax({
		type: "POST",
		url: "RimuoviDalCarrello",
		data : JSON.stringify(prodotto),

		success : function(data)
		{
//			alert("prodotto rimosso con successo");
			mostraCarrello();
		},
		error : function(data)
		{
			alert("error");
		}
	});


}


function ordinaPrezzo(tipo,ordinamento)
{

	var sortable = [];

	for (var key  in hashProdotti) 
	{
		if(tipo == hashProdotti[key].tipo)
			sortable.push({name:key,value: hashProdotti[key].prezzo});
	}

	var sorted = sortable.sort(function(a, b) {
		if(ordinamento == "ASC")
			return (a.value > b.value) ? 1 : ((b.value > a.value) ? -1 : 0)
					else
						return (a.value > b.value) ? -1 : ((b.value > a.value) ? 1 : 0)

	});

	var divProdotti = $("#prodotti");

	var divRowFelpe = $("#menufelpe").find("#felpe");
	var divRowTshirt = $("#menutshirt").find("#tshirt");
	var divCol;
	var divthumbnail;

	if(tipo=="felpa")
		divRowFelpe.empty();
	else 
		divRowTshirt.empty();

	for( var x in sorted )
	{
		if(hashProdotti[sorted[x].name].tipo == "felpa")
		{
			divCol = $('<div/>')
			.addClass("col-xs-2")
			.addClass("singoloProdotto")
			.appendTo(divRowFelpe);

			divthumbnail = $('<div/>')
			.addClass("thumbnail")
			.attr("data-toggle", "modal")
			.attr("data-target", "#product_view")
			.attr("id","felpa")
			.appendTo(divCol);
		}
		else if (hashProdotti[sorted[x].name].tipo == "tshirt")
		{
			divCol = $('<div/>')
			.addClass("col-xs-2")
			.addClass("singoloProdotto")
			.appendTo(divRowTshirt);

			divthumbnail = $('<div/>')
			.addClass("thumbnail")
			.attr("data-toggle", "modal")
			.attr("data-target", "#product_view")
			.attr("id","tshirt")
			.appendTo(divCol);
		}

		var immagine = $('<img>')
		.attr("src", hashProdotti[sorted[x].name].immagine)
		.attr("alt", "immagine prodotto")
		.addClass("image")
		.appendTo(divthumbnail);

		var divDettagli = $('<div/>')
		.addClass("dettagliprodotto")
		.appendTo(divthumbnail);

		var p = $('<p/>')
		.attr("id", hashProdotti[sorted[x].name].nome)
		.text(""+ hashProdotti[sorted[x].name].prezzo+" €")
		.appendTo(divDettagli);


//		var pPrezzo = $('<span/>')
//		.val(" adslkdlsalkdsak ")
//		.appendTo(p);

	}


}

