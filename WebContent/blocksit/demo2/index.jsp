<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.*;"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>BlocksIt.js Demonstration #2 - Pinterest Dynamic Grid Layout with CSS3 Transitions</title>
<meta name="description" content="BlocksIt.js jQuery plugin Demonstration #2 Pinterest dynamic grid with CSS3 Transitions by inWebson.com"/>
<meta name="keywords" content="demonstration,demo,jquery,blocksit.js,css3,dynamic,grid,layout,inwebson"/>
<link rel='stylesheet' href='style.css' media='screen' />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<!--[if lt IE 9]>
<script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<script src="../blocksit.min.js"></script>
<script>
$(document).ready(function() {
	//vendor script
	$('#header')
	.css({ 'top':-50 })
	.delay(1000)
	.animate({'top': 0}, 800);
	
	$('#footer')
	.css({ 'bottom':-15 })
	.delay(1000)
	.animate({'bottom': 0}, 800);
	
	//blocksit define
	$(window).load( function() {
		$('#container').BlocksIt({
			numOfCol: 5,
			offsetX: 8,
			offsetY: 8
		});
	});
	
	//window resize
	var currentWidth = 1100;
	$(window).resize(function() {
		var winWidth = $(window).width();
		var conWidth;
		if(winWidth < 660) {
			conWidth = 440;
			col = 2
		} else if(winWidth < 880) {
			conWidth = 660;
			col = 3
		} else if(winWidth < 1100) {
			conWidth = 880;
			col = 4;
		} else {
			conWidth = 1100;
			col = 5;
		}
		
		if(conWidth != currentWidth) {
			currentWidth = conWidth;
			$('#container').width(conWidth);
			$('#container').BlocksIt({
				numOfCol: col,
				offsetX: 8,
				offsetY: 8
			});
		}
	});
});


var like = function(id) {

	//alert($("#"+id).text());
	$.ajax({

		url : 'like',
		type : 'POST',
		dataType : 'json',
		data : {
			id : id,
			isLike : $("#" + id).text()
		},
		success : function(data) {
			if (data.isLike) {
				// alert("like successfully");
				$("#" + data.id).text("unlike");
			} else {
				// alert("unlike successfully");
				$("#" + data.id).text("like");
			}

		},
		error : function() {
			alert("error");
		}
	});

	return false;
};

var save = function() {

	// alert("save");
	$.ajax({
		url : 'like',
		type : 'POST',
		dataType : 'json',
		data : {
			"save" : "save"
		},
		success : function(data) {
			alert(data.success);
		},
		error : function() {
			alert("error");
		}
	});
	return false;
};

var prev = function() {
	//alert("prev");
	var current = $('#currentPage').text();

	if (current == 1) {
		$('#prev').addClass('disabled');
		return false;
	}

};

var next = function() {
	//alert("next");
	var current = $('#currentPage').text();
	var pages = $('#pages').text();
	//alert("current page is "+current + ", pages ="+ pages);
	if (current == pages) {
		//alert(" disabled the button");
		$('#next').addClass('disabled');
		$('#saveNext').addClass('disabled');
		return false;
	}
};
</script>
<link rel="shortcut icon" href="http://www.inwebson.com/wp-content/themes/inwebson2/favicon.ico" />
<link rel="canonical" href="http://www.inwebson.com/demo/blocksit-js/demo2/" />
</head>
<body>

<!-- Header -->
<header id="header">
	<h1>BlocksIt.js | Dynamic Grid Layout jQuery Plugin</h1>
	<div id="backlinks">
		<a href="../">Back to Home Page &raquo;</a>
		<a href="http://www.inwebson.com/jquery/blocksit-js-dynamic-grid-layout-jquery-plugin/">Visit Plugin Article Page &raquo;</a>
	</div>
	<div class="clearfix"></div>
</header>
<%

	List<PictureBean> pictures = (List<PictureBean>) session.getAttribute("pictures");
	String group = request.getParameter("group");
	String interest = "";
	String currentPage = request.getParameter("currentPage");
	String pages = request.getParameter("pages");

%>

<!-- Content -->
<section id="wrapper">
	<hgroup>
		<h2>BlocksIt.js Demonstration 2</h2> 
		<h3>Pinterest Dynamic Grid Layout with CSS3 Transitions (RESIZE)</h3>
	</hgroup>
<div id="container">

<%
			if (pictures != null) {
				for (int i = 0; i < pictures.size(); i++) {
					if (pictures.get(i).getInteresting() == 0) {
						interest = "like";
					} else {
						interest = "unlike";
					}
		%>
		<div class="grid">
			<div class="imgholder">
				<a href="<%=pictures.get(i).getLocal_add() %>"><img src="<%=pictures.get(i).getUrl()%>" /></a>
			</div> 
			<strong ><%=pictures.get(i).getTitle()%></strong>
			<!-- title  -->
			<p><%=pictures.get(i).getAlt()%></p>
			<!-- description  -->
			<div>
				<a id="<%=pictures.get(i).getId()%>" onclick="like('<%=pictures.get(i).getId()%>')"><%=interest%></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<%=pictures.get(i).getSource() %>">source</a>
			</div>
		</div>
		<%
			}
			} else {
				response.sendRedirect("index.jsp");
			}
		%>
	
</div>
</section>

<!-- Footer -->
<footer id="footer">
	<span>&copy; 2012 <a href="http://www.inwebson.com">inWebson.com</a>. Design by <a href="http://www.inwebson.com/contactus">Kenny Ooi</a>. Powered by <a href="http://www.inwebson.com/jquery/">jQuery</a>.</span>
</footer>

</body>
</html>