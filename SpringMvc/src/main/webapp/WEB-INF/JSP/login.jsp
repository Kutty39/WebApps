<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String display = "none", displayerr = "none";
    if (request.getAttribute("regsucs") != null) {
        if (request.getAttribute("regsucs").equals("success")) {
            display = "block";
        } else if (request.getAttribute("regsucs").equals("error")) {
            displayerr = "block";
        }
    }
%>
<div class="form-container">
    <div class="form-header">
        <h2>Login</h2>
    </div>
    <form:form id="lgnForm" class="my-form" modelAttribute="lgnForm">
        <div class="form-group">
            <p class="hd-lbl" id="errormsg" style="text-align: center;display:<%=displayerr%>;">Invalid User Name or
                Password!!!!</p>
            <p class="hd-lbl" id="successmsg" style="text-align: center;display:<%=display%>;">Thanks for Register with
                us!!!</p>
            <p class="hd-lbl eid" hidden>Enter valid email id</p>
            <form:input id="usr" type="text" path="email" required="required" placeholder="Email"/>
        </div>
        <div class="form-group">
            <p class="hd-lbl pas" hidden>Password should not be empty</p>
            <form:input type="password" path="pas" required="required" placeholder="Password"/>
        </div>
        <input class="button" type="submit" value="Login">
    </form:form>
</div>
