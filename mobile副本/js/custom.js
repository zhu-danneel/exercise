﻿jQuery(document).ready(function($) {	"use strict";	var options = {}; 	if ($('#gallery-swiper a').length) { 		$('#gallery-swiper a').photoSwipe(options); 	}	if ($('.gallery a').length)	{		$('.gallery a').photoSwipe(options);	}	if ($('.photography a').length)	{		$('.photography a').photoSwipe(options);	}	$('.notification').append('<div class="close">X</div>');	$('.notification .close').click(function(){		$(this).parent().animate({opacity: "0"});		$(this).parent().slideUp();	});	$(window).resize(function() {		var mt = ($('body').height() / 2) - ($('.content').height() / 2) - 30;		if (mt < 8) mt = 8;		$('.vertical-centered').css({			marginTop: mt + "px"		});	});	$(window).resize();});