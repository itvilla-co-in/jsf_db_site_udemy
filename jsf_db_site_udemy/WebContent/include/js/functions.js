function txt_tabs(tabs_id,editor_id){
	$(document).ready(function(){
		$('.tb_'+editor_id).removeClass('slc');
		$('#'+tabs_id).addClass('slc');
		//alert($('.edits'));
		$('.editor_'+editor_id).removeClass('slc');
		$('.editor_'+editor_id).hide();

		$('#'+tabs_id+'_content').show();
		$('#'+tabs_id+'_content').addClass('slc');
	});
}

$(document).ready(function(){
	var visina = $(window).height();
	if(visina < 400){
		visina = 400;
	}
	
	if($(window).width() >= 760){
		$('.container').css("min-height","" + (visina - 30) + "px");
	}else{
		$('.container').css("min-height","" + (visina - 10) + "px");
	}
	
	$('.menu-toggle').click(function(){
		$('.menu').toggle();
	});
	
	$('.filter-toggle').click(function(){
		$('.filters').toggle();
	});
	
	$(".toggle").click(function(){
		if($(this).parent().hasClass("collapsed")){
			$(this).parent().removeClass("collapsed");
			$(this).removeClass("reversed");
		}else{
			$(this).parent().addClass("collapsed");
			$(this).addClass("reversed");
		};
	});
	
	$( "table.list > tbody" ).sortable({
		placeholder: "ui-state-highlight",
		start: function(event, ui) 
        {
            $( "table.list > tbody" ).addClass('dragged');
			$( "body" ).css("overflow","hidden");
        },
		stop: function(event, ui) 
        {
            $( "table.list > tbody" ).removeClass('dragged');
			$( "body" ).removeAttr("style");
			var data = $(this).sortable("toArray");
			//sjx('save_order', data, $("#table").val());
        },
		cancel:".no-sort"
	});
	
	$( ".cat-list" ).sortable({
		placeholder: "ui-state-highlight",
		start: function(event, ui) 
        {
            $( ".cat-list" ).addClass('dragged');
			$( "body" ).css("overflow","hidden");
			$(this).children("ul.sort").addClass('disabled');
        },
		stop: function(event, ui) 
        {
            $( ".cat-list" ).removeClass('dragged');
			$( "body" ).removeAttr("style");
			$(this).children("ul.sort").removeClass('disabled');
			var data = $(this).sortable("toArray");
			//sjx('save_order', data, $("#table").val());
        },
		items: "> li",
		cancel:".no-sort"
	});
	
	$( ".sort" ).sortable({
		placeholder: "ui-state-highlight",
		start: function(event, ui) 
        {
            $( ".cat-list" ).addClass('dragged');
			$( "body" ).css("overflow","hidden");
			$(".cat-list").find("li").addClass('disabled');
			$(this).children("ul.sort").addClass('disabled');
			$(this).children("li").removeClass('disabled');
        },
		stop: function(event, ui) 
        {
            $( ".cat-list" ).removeClass('dragged');
			$( "body" ).removeAttr("style");
			$(this).children("ul.sort").removeClass('disabled');
			$(".cat-list").find("li").removeClass('disabled');
			var data = $(this).sortable("toArray");
			//sjx('save_order', data, $("#table").val());
        },
		items: "> li",
		cancel:".no-sort"
	});
	
	$( ".image-sort" ).sortable({
		placeholder: "ui-state-highlight",
		start: function(event, ui) 
        {
			ui.placeholder.height(ui.item.height()-6);
        },
		stop: function(event, ui) 
        {
			var data = $(this).sortable("toArray");
			//sjx('save_order', data, 'site_photos');
        },
		items: "> div.entry-image",
		cancel:".no-sort"
	});
	
	$( ".file-sort" ).sortable({
		placeholder: "ui-state-highlight",
		start: function(event, ui) 
        {
			ui.placeholder.height(ui.item.height()-6);
        },
		stop: function(event, ui) 
        {
			var data = $(this).sortable("toArray");
			//sjx('save_order', data, $(this).find(".file_table").val());
        },
		items: "> div.entry-dokument",
		cancel:".no-sort"
	});
			
	$( "table.list > tbody" ).disableSelection();
	$( ".cat-list" ).disableSelection();
});

$(window).resize(function(){
	var visina2 = $(window).height();
	if(visina2 < 400){
		visina2 = 400;
	}	
	if($(window).width() >= 760){
		$('.container').css("min-height","" + (visina2 - 30) + "px");
		$('.menu').css('display', '');
		if($(window).width() >= 960){
			$('.filters').css('display', '');
		}
	}else{
		$('.container').css("min-height","" + (visina2 - 10) + "px");
	}
});

$(window).scroll(function(){
	if($(document).scrollTop() >= 75){
		$('.save').addClass('scrolled');
	}else{
		$('.save').removeClass('scrolled');
	};
});

$(window).bind("load", function() {
   var max = -1;
	$(".entry-image").each(function(){
		var h = $(this).height(); 
		max = h > max ? h : max;
	});
	$(".entry-image").css("height", max);
});