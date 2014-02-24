<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	session.removeAttribute("current_movie");
	session.removeAttribute("current_group");
	session.removeAttribute("groups");
	response.sendRedirect("show?operation=showAll");
%>