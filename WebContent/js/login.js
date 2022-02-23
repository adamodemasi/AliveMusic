$(document).ready(function() {

	$("#FormLogin").submit(function(e) {

		$.ajax({
			type: "POST",
			url: "LoginCheck",
			data: $("#FormLogin").serialize(), 
			async:false,
			success: function(data)
			{
				if(data.valueOf() == "fallito")
				{
					//alert("DATI ERRATI");
					$("#facebook-result")
					.html("<div class=\"alert alert-danger\" role=\"alert\">Login fallito.</div>");
			setTimeout(
					function() {
						window.location.href = "/AliveMusic/login.jsp";
					}, 2000);
				}
				else
				{
					$(location).attr('href', "/AliveMusic/profilo.jsp");
				}

			},
			error: function(data)
			{
				alert("ERROR");
			}
		});

		e.preventDefault();

	});

$("#FormPass").submit(function(e){
		
		$.ajax({
			type: "POST",
			url: "CambiaPassword",
			data: $("#FormPass").serialize(), 
			async:false,
			success: function(data)
			{
				if(data.valueOf() == "fallito")
				{
					//alert("DATI ERRATI");
					$("#facebook-results")
					.html("<div class=\"alert alert-danger\" role=\"alert\">Nessuna mail trovata</div>");
			setTimeout(
					function() {
						window.location.href = "/AliveMusic/login.jsp";
					}, 2000);
				}
				else
				{
					$("#facebook-results")
					.html("<div class=\"alert alert-success\" role=\"alert\">Nuova Password inviata</div>");
			setTimeout(
					function() {
						window.location.href = "/AliveMusic/login.jsp";
					}, 2000);
				}

			},
			error: function(data)
			{
				alert("ERROR");
			}
		});

		e.preventDefault();
	})	

});

