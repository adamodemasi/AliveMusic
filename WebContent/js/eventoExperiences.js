var idEventoCliccato;
var nickLoggato = $("#user_nickname").text();

function dammiExperienceAssociate(idEvento)
{
//	for(var i=0;i<hashExp.lenght;i++)
//	{
//		alert(hashExp[i].id+" "+hashExp[i].organizzatore);
//	}

	hashExp = [];
	idEventoCliccato = idEvento;
	//alert("ajax experienceAssociate (idevento) ");
	$.ajax({
		type: "POST",
		url: "DammiExperience",
		datatype: "json",
		data: {"eventoID" : idEvento},

		success : function(experiencesJson)
		{
			var objs = JSON.parse(experiencesJson);

			var expList = $('ul.experiences');
			var expListCreate = $('ul.experiencesCreate');
			var expListPartecipate = $('ul.experiencesPartecipate');
			expListCreate.empty();
			expListPartecipate.empty();
			expList.empty();
			for (var i = 0, len = objs.length; i < len; i+=2)
			{
//				alert(objs[i+1].nickname);
				
				var exp = 
				{
						id : objs[i].ID,
						limiteAdesione : objs[i].limite_adesione,
						posti : objs[i].posti,
						descrizione : objs[i].descrizione,
						eventoId : idEvento,
						organizzatore : objs[i+1].nickname,
						pernottamento : objs[i].pernottamento,
						viaggio : objs[i].viaggio,
						concerto : objs[i].concerto,						
						nicknamePartecipanti : []
				};

				for(var x = 0;x<objs[i].partecipanti.length;x++)
				{
					exp.nicknamePartecipanti.push(objs[i].partecipanti[x].nickname);
				}
				hashExp[objs[i].ID] = exp;
				
				if(nickLoggato == exp.organizzatore)
				{
					var li = $('<li/>')
					.addClass("experience")
					.appendTo(expListCreate);

					var dateArr = objs[i].limite_adesione.split("-");
					var date = dateArr[2]+"/"+dateArr[1]+"/"+dateArr[0];

					
					var aaa = $('<a/>')
					.attr("data-toggle", "modal")
					.attr("data-target", "#mostra_experience")
//					.text("experience : ID="+objs[i].ID+" posti TOTALI="+objs[i].posti+" limite adesione="+objs[i].limite_adesione+" descrizione="+objs[i].descrizione )
					.html("<p id=\"titolista\">Codice: </p>"+objs[i].ID+"<p id =\"titolista\"> | Posti: </p>"+objs[i].posti+"<p id=\"titolista\"> | Descrizione: </p>"+objs[i].descrizione +"<br></br> <p id=\"titolista\">Limite adesione: </p>"+date+"<p id=\"titolista\"> | Organizzatore: </p>"+objs[i+1].nickname+"<p id=\"titolista\"> | Riguarda: </p>")					.attr("id",objs[i].ID)
					.appendTo(li);							
					
					if(objs[i].pernottamento)
						aaa.append("<p style=\"display:inline-block; color:red; font-weight:bold\" id=\"pernottamento\">\ &nbsp; Pernottamento \ </p>");
					if(objs[i].viaggio)
						aaa.append("<p style=\"display:inline-block;  color:green; font-weight:bold\" id=\"viaggio\">\ &nbsp; Viaggio \ </p>");
					if(objs[i].concerto)
						aaa.append("<p style=\"display:inline-block;  color:blue; font-weight:bold\" id=\"concerto\">\ &nbsp; Concerto \ </p>");
					
					
					if(objs[i].pernottamento && !objs[i].viaggio && objs[i].concerto)
						li.css("border","3px solid #00cccc") //azzurro
					else if(objs[i].pernottamento && objs[i].viaggio && objs[i].concerto)
						li.css("border","3px solid #cc9900") //oro
					else if(!objs[i].pernottamento && objs[i].viaggio && objs[i].concerto)
						li.css("border","3px solid #4d2600") //marrone
					else if(!objs[i].pernottamento && !objs[i].viaggio && objs[i].concerto)
						li.css("border","3px solid blue") //blue
					else if(!objs[i].pernottamento && objs[i].viaggio && !objs[i].concerto)
						li.css("border","3px solid green") // verde
					else if(objs[i].pernottamento && !objs[i].viaggio && !objs[i].concerto)
						li.css("border","3px solid red")//Rosso
					else if(objs[i].pernottamento && objs[i].viaggio && !objs[i].concerto)
						li.css("border","3px solid #ff6600") // arancione
				
					
					
				}
				else
				{
					var partecipa = 0;
					for(var x = 0;x<exp.nicknamePartecipanti.length;x++)
					{
						if(nickLoggato == exp.nicknamePartecipanti[x])
						{
							partecipa = 1;
							break;
						}	
					}
					if(partecipa==1)
					{
						var li = $('<li/>')
						.addClass("experience")
						.appendTo(expListPartecipate);

						var dateArr = objs[i].limite_adesione.split("-");
						var date = dateArr[2]+"/"+dateArr[1]+"/"+dateArr[0];

						var aaa = $('<a/>')
						.attr("data-toggle", "modal")
						.attr("data-target", "#mostra_experience")
//						.text("experience : ID="+objs[i].ID+" posti TOTALI="+objs[i].posti+" limite adesione="+objs[i].limite_adesione+" descrizione="+objs[i].descrizione )
						.html("<p id=\"titolista\">Codice: </p>"+objs[i].ID+"<p id =\"titolista\"> | Posti: </p>"+objs[i].posti+"<p id=\"titolista\"> | Descrizione: </p>"+objs[i].descrizione +"<br></br> <p id=\"titolista\">Limite adesione: </p>"+date+"<p id=\"titolista\"> | Organizzatore: </p>"+objs[i+1].nickname+"<p id=\"titolista\"> | Riguarda: </p>")					.attr("id",objs[i].ID)
						.attr("id",objs[i].ID)
						.appendTo(li);	
				
						if(objs[i].pernottamento)
							aaa.append("<p style=\"display:inline-block; color:red; font-weight:bold\" id=\"pernottamento\">\ &nbsp; Pernottamento \ </p>");
						if(objs[i].viaggio)
							aaa.append("<p style=\"display:inline-block;  color:green; font-weight:bold\" id=\"viaggio\">\ &nbsp; Viaggio \ </p>");
						if(objs[i].concerto)
							aaa.append("<p style=\"display:inline-block;  color:blue; font-weight:bold\" id=\"concerto\">\ &nbsp; Concerto \ </p>");
							
						
					if(objs[i].pernottamento && !objs[i].viaggio && objs[i].concerto)
						li.css("border","3px solid #00cccc") //azzurro
					else if(objs[i].pernottamento && objs[i].viaggio && objs[i].concerto)
						li.css("border","3px solid #cc9900") //oro
					else if(!objs[i].pernottamento && objs[i].viaggio && objs[i].concerto)
						li.css("border","3px solid #4d2600") //marrone
					else if(!objs[i].pernottamento && !objs[i].viaggio && objs[i].concerto)
						li.css("border","3px solid blue") //blue
					else if(!objs[i].pernottamento && objs[i].viaggio && !objs[i].concerto)
						li.css("border","3px solid green") // verde
					else if(objs[i].pernottamento && !objs[i].viaggio && !objs[i].concerto)
						li.css("border","3px solid red")//Rosso
					else if(objs[i].pernottamento && objs[i].viaggio && !objs[i].concerto)
						li.css("border","3px solid #ff6600") // arancione
						
					}
					else
					{
						var li = $('<li/>')
						.addClass("experience")
						.appendTo(expList);

						var dateArr = objs[i].limite_adesione.split("-");
						var date = dateArr[2]+"/"+dateArr[1]+"/"+dateArr[0];

						var aaa = $('<a/>')
						.attr("data-toggle", "modal")
						.attr("data-target", "#mostra_experience")
//						.text("experience : ID="+objs[i].ID+" posti TOTALI="+objs[i].posti+" limite adesione="+objs[i].limite_adesione+" descrizione="+objs[i].descrizione )
						.html("<p id=\"titolista\">Codice: </p>"+objs[i].ID+"<p id =\"titolista\"> | Posti: </p>"+objs[i].posti+"<p id=\"titolista\"> | Descrizione: </p>"+objs[i].descrizione +"<br></br> <p id=\"titolista\">Limite adesione: </p>"+date+"<p id=\"titolista\"> | Organizzatore: </p>"+objs[i+1].nickname+"<p id=\"titolista\"> | Riguarda: </p>")					.attr("id",objs[i].ID)
						.attr("id",objs[i].ID)
						.appendTo(li);						
						
						if(objs[i].pernottamento)
							aaa.append("<p style=\"display:inline-block; color:red; font-weight:bold\" id=\"pernottamento\">\ &nbsp; Pernottamento \ </p>");
						if(objs[i].viaggio)
							aaa.append("<p style=\"display:inline-block;  color:green; font-weight:bold\" id=\"viaggio\">\ &nbsp; Viaggio \ </p>");
						if(objs[i].concerto)
							aaa.append("<p style=\"display:inline-block;  color:blue; font-weight:bold\" id=\"concerto\">\ &nbsp; Concerto \ </p>");
						
							
					if(objs[i].pernottamento && !objs[i].viaggio && objs[i].concerto)
						li.css("border","3px solid #00cccc") //azzurro
					else if(objs[i].pernottamento && objs[i].viaggio && objs[i].concerto)
						li.css("border","3px solid #cc9900") //oro
					else if(!objs[i].pernottamento && objs[i].viaggio && objs[i].concerto)
						li.css("border","3px solid #4d2600") //marrone
					else if(!objs[i].pernottamento && !objs[i].viaggio && objs[i].concerto)
						li.css("border","3px solid blue") //blue
					else if(!objs[i].pernottamento && objs[i].viaggio && !objs[i].concerto)
						li.css("border","3px solid green") // verde
					else if(objs[i].pernottamento && !objs[i].viaggio && !objs[i].concerto)
						li.css("border","3px solid red")//Rosso
					else if(objs[i].pernottamento && objs[i].viaggio && !objs[i].concerto)
						li.css("border","3px solid #ff6600") // arancione
					}	
				}


			}
			$("#myUL1 li.experience a").on('click',function(){
				$("#myUL1 li.experience a").not(this).removeClass("selected");
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
//var idEventoCliccato;
//var nickLoggato = $("#user_nickname").text();
//
//function dammiExperienceAssociate(idEvento)
//{
////	for(var i=0;i<hashExp.lenght;i++)
////	{
////		alert(hashExp[i].id+" "+hashExp[i].organizzatore);
////	}
//
//	hashExp = [];
//	idEventoCliccato = idEvento;
//	alert("ajax experienceAssociate (idevento) ");
//	$.ajax({
//		type: "POST",
//		url: "DammiExperience",
//		datatype: "json",
//		data: {"eventoID" : idEvento},
//
//		success : function(experiencesJson)
//		{
//			var objs = JSON.parse(experiencesJson);
//
//			var expList = $('ul.experiences');
//			var expListCreate = $('ul.experiencesCreate');
//			var expListPartecipate = $('ul.experiencesPartecipate');
//			expListCreate.empty();
//			expListPartecipate.empty();
//			expList.empty();
//			for (var i = 0, len = objs.length; i < len; i+=2)
//			{
////				alert(objs[i+1].nickname);
//				
//				var exp = 
//				{
//						id : objs[i].ID,
//						limiteAdesione : objs[i].limite_adesione,
//						posti : objs[i].posti,
//						descrizione : objs[i].descrizione,
//						eventoId : idEvento,
//						organizzatore : objs[i+1].nickname,
//						nicknamePartecipanti : []
//				};
//
//				for(var x = 0;x<objs[i].partecipanti.length;x++)
//				{
//					exp.nicknamePartecipanti.push(objs[i].partecipanti[x].nickname);
//				}
//				hashExp[objs[i].ID] = exp;
//				
//				if(nickLoggato == exp.organizzatore)
//					{
//					var li = $('<li/>')
//					.addClass("experience")
//					.appendTo(expListCreate);
//
//					var dateArr = objs[i].limite_adesione.split("-");
//					var date = dateArr[2]+"/"+dateArr[1]+"/"+dateArr[0];
//
//					var aaa = $('<a/>')
//					.attr("data-toggle", "modal")
//					.attr("data-target", "#mostra_experience")
////					.text("experience : ID="+objs[i].ID+" posti TOTALI="+objs[i].posti+" limite adesione="+objs[i].limite_adesione+" descrizione="+objs[i].descrizione )
//					.html("<p id=\"titolista\">Codice: </p>"+objs[i].ID+"<p id =\"titolista\"> | Posti: </p>"+objs[i].posti+"<p id=\"titolista\"> | Descrizione: </p>"+objs[i].descrizione +"<br></br> <p id=\"titolista\">Limite adesione: </p>"+date+"<p id=\"titolista\"> | Organizzatore: </p>"+objs[i+1].nickname)
//					.attr("id",objs[i].ID)
//					.appendTo(li);
//					}
//				else
//					{
//					
//					
//				var partecipa = 0;
//				for(var x = 0;x<exp.nicknamePartecipanti.length;x++)
//				{
//					if(nickLoggato== exp.nicknamePartecipanti[x])
//						{
//							partecipa = 1;
//							break;
//						}
//				}
//				if(partecipa==1)
//					{
//					var li = $('<li/>')
//					.addClass("experience")
//					.appendTo(expListPartecipate);
//
//					var dateArr = objs[i].limite_adesione.split("-");
//					var date = dateArr[2]+"/"+dateArr[1]+"/"+dateArr[0];
//
//					var aaa = $('<a/>')
//					.attr("data-toggle", "modal")
//					.attr("data-target", "#mostra_experience")
////					.text("experience : ID="+objs[i].ID+" posti TOTALI="+objs[i].posti+" limite adesione="+objs[i].limite_adesione+" descrizione="+objs[i].descrizione )
//					.html("<p id=\"titolista\">Codice: </p>"+objs[i].ID+"<p id =\"titolista\"> | Posti: </p>"+objs[i].posti+"<p id=\"titolista\"> | Descrizione: </p>"+objs[i].descrizione +"<br></br> <p id=\"titolista\">Limite adesione: </p>"+date+"<p id=\"titolista\"> | Organizzatore: </p>"+objs[i+1].nickname)
//					.attr("id",objs[i].ID)
//					.appendTo(li);
//					}
//				else{
//				var li = $('<li/>')
//				.addClass("experience")
//				.appendTo(expList);
//
//				var dateArr = objs[i].limite_adesione.split("-");
//				var date = dateArr[2]+"/"+dateArr[1]+"/"+dateArr[0];
//
//				var aaa = $('<a/>')
//				.attr("data-toggle", "modal")
//				.attr("data-target", "#mostra_experience")
////				.text("experience : ID="+objs[i].ID+" posti TOTALI="+objs[i].posti+" limite adesione="+objs[i].limite_adesione+" descrizione="+objs[i].descrizione )
//				.html("<p id=\"titolista\">Codice: </p>"+objs[i].ID+"<p id =\"titolista\"> | Posti: </p>"+objs[i].posti+"<p id=\"titolista\"> | Descrizione: </p>"+objs[i].descrizione +"<br></br> <p id=\"titolista\">Limite adesione: </p>"+date+"<p id=\"titolista\"> | Organizzatore: </p>"+objs[i+1].nickname)
//				.attr("id",objs[i].ID)
//				.appendTo(li);		
//			
//					}}
//			}
//			$("#myUL li.experience a").on('click',function(){
//				$("#myUL li.experience a").not(this).removeClass("selected");
//				$(this).addClass("selected");
//				var id = $(this).attr("id");
//				dammiDettagliExperience(hashExp[id]);
//			});
//
//		},
//		error : function(experiencesJson)
//		{
//			alert("ERROR");
//		}
//
//	});
//}
