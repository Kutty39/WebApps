<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="form-container">
    <div class="form-header">
        <h2>Profile</h2>
    </div>
    <form:form id="profForm" class="my-form" modelAttribute="prfForm">
        <form:input type="text" path="fname" required="required"/>
        <form:input type="text" path="lname" required="required" placeholder="Last Name"/>
        <form:input type="text" path="eid" disabled="true"/>
        <p class="hd-lbl phn" hidden>*Mandatory field</p>
        <form:input type="text" path="phn" required="required" placeholder="Phone Number"/>
        <form:textarea path="adrs" required="required"/>
        <form:input type="password" path="pas" required="required" placeholder="Confirm Passward"/>
        <input class="button" type="submit" value="Update">
    </form:form>
</div>