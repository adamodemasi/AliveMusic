$(document).ready(function() {

$("#myUL li.experience a").on('click',function(){
	$("#myUL li.experience a").not(this).removeClass("selected");
	$(this).addClass("selected");
	var id = $(this).attr("id");
	dammiDettagliExperience(hashExp[id]);
});

	if ($(".profile-userpic img").attr('src')==='')
	{
		$(".profile-userpic img").attr('src',"img/avatar.png");	
	}


	$('ul#profile li').on('click', function() {
		$('ul#profile li.active').removeClass('active');
		$(this).addClass('active');
	});


	$(".boxinfo--experience").hide();
	$(".boxinfo--prodotti").hide();
//	$(".boxinfo--posta").hide();
	$(".boxinfo--info").hide();
//	$(".boxinfo--impostazioni").hide();


	$("#buttonbio").click(function(){
		$(".boxinfo--experience").hide();
		$(".boxinfo--prodotti").hide();
//		$(".boxinfo--posta").hide();
		$(".boxinfo--info").hide();
		$(".boxinfo--bio").slideDown(800);
//		$(".boxinfo--impostazioni").hide();
	});
	$("#buttonexperience").click(function(){
		$(".boxinfo--experience").slideDown(800);
		$(".boxinfo--prodotti").hide();
//		$(".boxinfo--posta").hide();
		$(".boxinfo--info").hide();
		$(".boxinfo--bio").hide();
//		$(".boxinfo--impostazioni").hide();
	});
	$("#buttoninfo").click(function(){
		$(".boxinfo--experience").hide();
		$(".boxinfo--prodotti").hide();
//		$(".boxinfo--posta").hide();
		$(".boxinfo--info").slideDown(800);
		$(".boxinfo--bio").hide();
//		$(".boxinfo--impostazioni").hide();
	});
	$("#buttonprodotti").click(function(){
		$(".boxinfo--experience").hide();
		$(".boxinfo--prodotti").slideDown(800);
//		$(".boxinfo--posta").hide();
		$(".boxinfo--info").hide();
		$(".boxinfo--bio").hide();
//		$(".boxinfo--impostazioni").hide();
	});
});




var nickname = $("#user_nick").text();


var experienceShow = $("#buttonexperience");

var prodottiShow = $("#buttonprodotti");

experienceShow.on('click',function(){
	dammiExperienceCreate();
});

prodottiShow.on('click',function(){
	dammiProdottiCreati();
});

