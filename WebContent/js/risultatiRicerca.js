$(document).ready(function() {

	$("#myUL li.utente a").on('click',function(){
		$("#myUL li.utente a").not(this).removeClass("selected");
		$(this).addClass("selected");
		setSessionUtente($(this).attr("id"));
	});
	

	
});

function setSessionUtente(nickname_Cercare) {
	
	$.ajax({
		type: "POST",
		url: "mostraAltroUtente",
		data: {"nickname":nickname_Cercare},
		async: false,
		
		success : function()
		{
			alert("success");
		},
		error : function()
		{
			alert("error");
		}
	
	});

}