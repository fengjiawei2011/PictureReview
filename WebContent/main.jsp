<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.*;"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	List<PictureBean> pictures = (List<PictureBean>) session
			.getAttribute("pictures");
	String group = request.getParameter("group");
	String interest = "";
	String currentPage = request.getParameter("currentPage");
	String pages = request.getParameter("pages");
	//System.out.println("currentpage --->" + currentPage);
	//System.out.println("pictures size--->" + pictures.size());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet' href='css/style.css' media='screen' />
<link rel='stylesheet' href='css/bootstrap.min.css' media='screen' />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<script src="js/blocksit.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		
	 	/* var l = $('#islike').attr("class").substring(0,6) ;
		if( l == "like"){
			$('#islike').addClass("glyphicon glyphicon-ok");
		}else{
			$('#islike').addClass("glyphicon glyphicon-remove");
		}  */
		
		//vendor script
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
					$('#lab_'+id).removeClass("glyphicon-ok");
					$('#lab_'+id).addClass("glyphicon-remove");
					$("#" + data.id).text("unlike");
				} else {
					// alert("unlike successfully");
					$('#lab_'+id).removeClass("glyphicon-remove");
					$('#lab_'+id).addClass("glyphicon-ok");
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

<link rel="shortcut icon"
	href="http://www.inwebson.com/wp-content/themes/inwebson2/favicon.ico" />
<link rel="canonical"
	href="http://www.inwebson.com/demo/blocksit-js/demo2/" />
</head>


<body>
	<!-- Header -->
	<header id="header"> <!--  <h1>Picture Review</h1>-->
	<div>
		<ul class="pager">
			<li><a href="#" onclick="save()">save</a></li>
			<li id="prev"><a href="show?operation=prev"
				onclick="return prev()">Previous</a></li>
			<li><span> <label id="currentPage"><%=currentPage%></label>
					of <label id="pages"><%=pages%></label>
			</span></li>
			<li id="next"><a href="show?operation=next"
				onclick="return next()">Next</a></li>
			<li id="saveNext"><a href="show?operation=saveNext"
				onclick="return next()">Save&Next</a></li>
			<li><div class="btn-group">
					<button type="button" class="btn btn-info dropdown-toggle"
						data-toggle="dropdown">
						<%=group%>
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="show?group=1">Group 1</a></li>
						<li><a href="show?group=2">Group 2</a></li>
						<li><a href="show?group=3">Group 3</a></li>
					</ul>
				</div></li>
		</ul>
	</div>
	<div class="clearfix"></div>
	</header>
	<!-- Content -->
	<section id="wrapper"> <!--  <hgroup>
	<h2>Picture Review</h2>
	<h3>Picture Review</h3>
	</hgroup> -->


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
				<a href="<%=pictures.get(i).getLocal_add()%>"><img
					src="<%=pictures.get(i).getUrl()%>" /></a>
			</div>
			<strong><%=pictures.get(i).getTitle()%></strong>
			<!-- title  -->
			<p><%=pictures.get(i).getAlt()%></p>
			<!-- description  -->
			<div>
			<%
				if(interest.equals("like")){
					%><label id="lab_<%=pictures.get(i).getId()%>" class="glyphicon glyphicon-ok"></label>
			<%}else{%><label id="lab_<%=pictures.get(i).getId()%>" class="glyphicon glyphicon-remove"></label>
			<%}%>
				<a href="#" id="<%=pictures.get(i).getId()%>"
					onclick="like('<%=pictures.get(i).getId()%>')"><%=interest%></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<%=pictures.get(i).getSource()%>">source</a>
				
			</div>
		</div>
		<%
			}
			} else {
				response.sendRedirect("index.jsp");
			}
		%>
	</div>

	<!--  
	<div>
		<a id="first" href="#">First</a>  	
		<a id="prev" href="#">Prev</a> 
		<label id="page" >1 of 100</label>
		<a id="next" href="#">Next</a> 
		<a id="last" href="#">Last</a>
	</div>
	--> </section>

</body>

</html>