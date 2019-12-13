<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="form-container">
	<div class="form-header">
		<h2>Register</h2>
	</div>
	<form id="regForm" class="my-form">
		<input type="text" name="fname" placeholder="First Name"
			required="required"> <input type="text" name="lname"
			required="required" placeholder="Last Name">
		<p class="hd-lbl eid" hidden>*Mandatory field</p>
		<input type="text" id="eid" name="eid" required="required"
			placeholder="Email ID">
		<p class="hd-lbl phn" hidden>*Mandatory field</p>
		<input type="text" name="phn" required="required"
			placeholder="Phone Number">
		<textarea name="adrs" required="required" placeholder="Address"></textarea>
		<p class="hd-lbl pas" hidden>*Combination of Upper case,Lower
			case,Number and Special Character it should be min length of 8 and
			Max 15</p>
		<input type="password" name="pas" required="required"
			placeholder="Passward">
		<p class="hd-lbl conpas" hidden>*Does not matches</p>
		<input type="password" name="conpas" required="required"
			placeholder="Confirm Password" required="required"> <input class="button" type="submit" value="Register">
	</form>
</div>