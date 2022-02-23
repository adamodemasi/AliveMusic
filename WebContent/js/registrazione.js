$(document).ready(function(){

	$("#registrati").prop("disabled", true);
	
	

	$("#termini").on("click",function(){

		if($("#termini").is(':checked'))
			$("#registrati").prop("disabled", false);
		else
			$("#registrati").prop("disabled", true);

	});


});




$("#FormRegister").submit(function(e) {

	$.ajax({
		type: "POST",
		url: "RegistraUtente",
		data: $("#FormRegister").serialize(), 
		async:false,
		success: function(data)
		{
			alert(data);
			if(data.valueOf() == "puoiLoggare")
			{
				$("#facebook-result")
				.html(
						"<div class=\"alert alert-danger\" role=\"alert\">Email già registrata.</div>");
		setTimeout(function() {
		}, 2000);
			}
			else if(data.valueOf() == "nicknameUguale")
			{
				$("#facebook-result")
				.html(
						"<div class=\"alert alert-danger\" role=\"alert\">Nickname non disponibile.</div>");
		setTimeout(function() {
		}, 2000);
			}
			else
			{
				$("#facebook-result")
				.html(
						"<div class=\"alert alert-success\" role=\"alert\">  Stai per entrare nel sito.</div>");
		setTimeout(
				function() {
					window.location.href = "profilo.jsp";
				}, 2000);
			}

		},
		error: function(data)
		{
			alert("ERROR");
		}
	});

	e.preventDefault();

});