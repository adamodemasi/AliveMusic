$(document).ready(function() {

	
	$("#creaExperience").hide();
	$("#expRimaste").hide();
	$("#expPartecipate").hide();
	$("#expCreate").hide();
	
	
	dammiTuttiEventi();

	$("#myUL li.event a").on('click',function(e){
		$("#myUL li.event a").not(this).removeClass("selected");
		$(this).addClass("selected");
		$("#creaExperience").show();
		$("#ex").show();
		$("#expPartecipate").show();
		$("#expCreate").show();
		$("#expRimaste").show();
		$("#idEvento").val($(this).attr("id"));
		dammiExperienceAssociate($(this).attr("id"));
		e.preventDefault();
	});

});


function dammiTuttiEventi()
{
//	alert("ajax tutti eventi");
	$.ajax({
		type: "GET",
		url: "eventi",
		datatype: "json",
		async: false,

		success : function(eventiJson)
		{

			var objs = JSON.parse(eventiJson);

			var eventList = $("ul.eventi");
			eventList.empty();
			for(var i=0; i< objs.length; i+=3)
			{
				var li = $('<li/>')
				.addClass("event")
				.appendTo(eventList);

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
//				.text("data="+date+" location="+objs[i+1].nome+" artisti="+artisti)
				.html("<p id=titolista> Data: </p> "+date+"<p id=titolista> | Citta: </p>"+objs[i+1].citta+"<br></br> <p id=titolista> Artisti: </p>"+artisti +"<p id=titolista> | Location: </p>"+objs[i+1].nome)
				.attr("id",objs[i].ID)
				.appendTo(li);
			}
		},
		error : function(eventiJson)
		{
			alert("ERROR");
		}

	});

}

var modalMostraExp = $("#mostra_experience");
var modalInserisciExp = $("#crea_experience");

$("#FormCreazione").submit(function(e) {
	$.ajax({
		type: "POST",
		url: "InserisciExperience",
		datatype: "json",
		data: $("#FormCreazione").serialize()+"&"+"eventoID="+idEventoCliccato , 
		async:false,
		success: function(data)
		{
			var expObj = JSON.parse(data);
			var exp = 
			{
					id : expObj.ID,
					limiteAdesione : expObj.limite_adesione,
					posti : expObj.posti,
					descrizione : expObj.descrizione,
			};
			modalInserisciExp.modal("hide");
			dammiExperienceAssociate(idEventoCliccato);
			$('#FormCreazione').trigger("reset");
			dammiDettagliExperience(exp);			
			modalMostraExp.modal("show");
		},
		error: function(data)
		{
			alert("ERROR");
		}
	});
	e.preventDefault();
});