/**
 * 
 *//*

function pagination(){
	alert(11);
	$.ajax({
		url:'pagination',
		type:'POST',
		dataType:'json',
		data:{flag: "first"},
		success: function(msg){
			alert(msg.currentpage);
			$('#page').html(msg.currentpage+"of"+pages);
		}
	}); 
} 

	$(document).ready(function() {
		//vendor script
		 $.ajax({
		url:'pagination',
		type:'POST',
		dataType:'json',
		data:{flag: "first"},
		success: function(msg){
			alert(msg.currentpage);
			$('#page').html(msg.currentpage+"of"+pages);
		}
	}); 
		
		$('#header').css({
			'top' : -50
		}).delay(1000).animate({
			'top' : 0
		}, 800);

		$('#footer').css({
			'bottom' : -15
		}).delay(1000).animate({
			'bottom' : 0
		}, 800);

		//blocksit define
		$(window).load(function() {
			$('#container').BlocksIt({
				numOfCol : 5,
				offsetX : 8,
				offsetY : 8
			});
		});

		//window resize
		var currentWidth = 1100;
		$(window).resize(function() {
			var winWidth = $(window).width();
			var conWidth;
			if (winWidth < 660) {
				conWidth = 440;
				col = 2
			} else if (winWidth < 880) {
				conWidth = 660;
				col = 3
			} else if (winWidth < 1100) {
				conWidth = 880;
				col = 4;
			} else {
				conWidth = 1100;
				col = 5;
			}

			if (conWidth != currentWidth) {
				currentWidth = conWidth;
				$('#container').width(conWidth);
				$('#container').BlocksIt({
					numOfCol : col,
					offsetX : 8,
					offsetY : 8
				});
			}
		});

	
	


var like = function(id) {
	
	alert($("#"+id).text());
	$.ajax({
		
		url : 'like',
		type : 'POST',
		dataType : 'json',
		data : {
			id : id,
			isLike:$("#"+id).text()
		},
		success : function(data) {
			if (data.isLike) {
				//alert("like successfully");
				$("#" + data.id).text("unlike");
			} else {
				//alert("unlike successfully");
				$("#" + data.id).text("like");
			}

		},
		error : function() {
			alert("error");
		}
	});

	return false;
};

var save= function(){
	
	//alert("save");
	$.ajax({
		url:'like',	
		type:'POST',
		dataType:'json',
		data:{"save":"save"},
		success: function(data){
			alert(data.success);
		},
		error : function() {
			alert("error");
		}
	});
	
};


 var next = function(){
	alert("next");
	var current = $('#currentPage').text();
	var pages= $('#pages').text();
	alert("current page is "+current + ", pages ="+ pages);
	if(current == pages){
		alert(" disabled the button");
		$('#next').addClass('disabled');
		$(function () {
		    $('a.something').on("click", function (e) {
		        e.preventDefault();
		    });
		});
	}
 
	*/