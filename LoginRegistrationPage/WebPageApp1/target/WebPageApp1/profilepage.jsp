<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% 
	HashMap<String,String> detail=(HashMap)request.getAttribute("detail");
	String firstname=detail.get("firstname");
	String lastname=detail.get("lastname");
	String email=detail.get("email");
	String phone=detail.get("phone");
	String address=detail.get("address");
	%>
<div class="form-container">
	<div class="form-header">
		<h2>Profile</h2>
	</div>
	<form id="profForm" class="my-form">
		<input type="text" name="fname"	required="required" value=<%= firstname %>> 
		<input type="text" name="lname"	required="required" placeholder="Last Name" value=<%= lastname %>>
		<input type="text" name="eid" disabled="disabled" value=<%= email %>>
		<p class="hd-lbl phn" hidden>*Mandatory field</p>
		<input type="text" name="phn" required="required"
			placeholder="Phone Number"  value=<%= phone %>>
		<textarea name="adrs" required="required" ><%= address %></textarea>
		<input type="password" name="pas" required="required"
			placeholder="Confirm Passward"> 
			<input class="button" type="submit" value="Update">
	</form>
</div>