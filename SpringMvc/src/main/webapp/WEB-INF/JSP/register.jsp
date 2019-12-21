<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="form-container">
    <div class="form-header">
        <h2>Register</h2>
    </div>
    <form:form id="regForm" class="my-form" modelAttribute="regForm">
        <form:input type="text" path="fname" placeholder="First Name"
                    required="required"/>
		<form:input type="text" path="lname" required="required" placeholder="Last Name"/>
        <p class="hd-lbl eid" hidden>*Mandatory field</p>
        <form:input type="text" path="eid" name="eid" required="required"
               placeholder="Email ID"/>
        <p class="hd-lbl phn" hidden>*Mandatory field</p>
        <form:input type="text" path="phn" required="required"
               placeholder="Phone Number"/>
        <form:textarea path="adrs" required="required" placeholder="Address"/>
        <p class="hd-lbl pas" hidden>*Combination of Upper case,Lower
            case,Number and Special Character it should be min length of 8 and
            Max 15</p>
        <form:input type="password" path="pas" required="required"
               placeholder="Passward"/>
        <p class="hd-lbl conpas" hidden>*Does not matches</p>
        <form:input type="password" path="conpas" required="required"
               placeholder="Confirm Password"/>
        <input class="button" type="submit" value="Register">
    </form:form>
</div>