$(document).ready(function(){
   //dati_homepage();
});

function dati_homepage()
{
	alert("STO CARICANDO DATI DAL DATABASE");
	$.ajax({
		type: "POST",
		url: "DatiHomepage",
		datatype: "json",

		success : function(dati)
		{
			alert("SONO NEL SUCCESS");
			//var obj=JSON(dati).toString();
			
			var experiences=dati[0];
			var eventi=dati[1];
			var utenti=dati[2];
			
			console.log("MORCA PADONNA");
			
			$("#n_experience").text(experiences);
			$("#n_utenti").text(utenti);
			$("#n_eventi").text(eventi);
		},
		error : function()
		{
			alert("ERROR");
		}

	});
}