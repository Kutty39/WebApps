$(document).ready(function() {
	// this will bind the method for currently available elements
	$("a.button").click(loadpage);
	// this on method will bind the methods to future elements
	$("#ajx").on("blur","input[type!='submit']",($(this), mandChecker));
	$("#ajx").on("keyup","input[type!='submit']",($(this), mandChecker));
	$("#ajx").on("blur","#eid",check);
	$("#ajx").on("blur","#usr",check);
	$("#ajx").on("submit","#regForm",function(e){
		var s=true;
			$("p.hd-lbl").each(function() {
				if (this.style.display === "block") {
					s=false;
				}
			});
			if(s){
			$.ajax({
				type : "POST",
				url : "regestration",
				data :$("#regForm").serialize(),
				success : function(msg) {
					$("#ajx").html(msg);
				},
				dataType : "html"
			});
			}
			e.preventDefault();
	});
	
	
	$("#ajx").on("submit","#lgnForm",function(e){
		var s=true;
		$("#errormsg").hide();
		$("#successmsg").hide();
			$("p.hd-lbl").each(function() {
				if (this.style.display === "block") {
					s=false;
				}
			});
			if(s){
				$.post("login",$("#lgnForm").serialize(),function(msg) {
					if(msg.search("Login")<0){
						lognaction();}
						$("#ajx").html(msg);
					});
				}
		e.preventDefault();
	});
	
	
	$("#ajx").on("submit","#profForm",function(e){
		let s = true;
		$("p.hd-lbl").each(function() {
			if (this.style.display === "block") {
				s=false;
				return;
			}
		});
		if(s){
			$.post("update",$("#profForm").serialize(),function(msg) {
				alert(msg);
			});
		}
		 e.preventDefault();
	});
	
});

function lognaction(){
	$("a.button").each(function(){
		if($(this).attr('class').indexOf("home")<=0){
		$(this).toggle();}
	});
}
function logconfirm(){
	$("a.button.login").hide()
	$("a.button.register").hide();
	$("a.button.userprof").show();
	$("a.button.logout").show();
}


function loadpage() {
	var cls = $(this).attr("class").replace("button ","");
	console.log(cls.replace("button ",""));
	$.ajax({
		type : "post",
		url : "switchpage",
		data : {"page" : cls},
		success : function(msg) {
			if(cls=="logout"){
				lognaction();
			}
			$("#ajx").html(msg);
		},
		dataType : "html"
	});
}

function check(){
	var obj=$(this);
	if(obj!=null){
	$.ajax({
		type : "post",
		url : "validate",
		data : {"email":obj.val()},
		success : function(msg) {
			if(msg==="true"){
				if(obj.attr("name")==="eid"){
				$(".hd-lbl.eid").text("*Email alread exists.");
				$(".hd-lbl.eid").show();
				}if(obj.attr("name")==="username"){
					$(".hd-lbl.username").hide();
				}
			}else{
				if(obj.attr("name")==="username"){
					$(".hd-lbl.username").text("*Email is not exists. please register and come back");
					$(".hd-lbl.username").show();
				}else if(obj.attr("name")==="eid"){
					$(".hd-lbl.eid").hide();
				}
			}
		}
	});
	}
}

function mandChecker(element) {
	var val = element.target.value;
	var name = element.target.name;
	var obj = $(".hd-lbl." + name);
	if (val === "") {
		obj.show();
	} else {
		obj.show();
		var regx = "";
		if (name === "phn") {
			regx = /^\d{10}$/;
			if (regx.test(val)) {
				obj.hide();
			} else {
				obj.text("* Phone number should be 10 digit");
			}
		} else if (name === "eid") {
			regx = /^[a-zA-z\d._-]+\@[a-zA-Z\d.-]+\.[a-zA-Z]{2,4}$/;
			if (regx.test(val)) {
				obj.hide();
			} else {
				obj.text("* Please enter valid email.");
			}
		} else if (name === "pas") {
			regx = /^((?=.*[a-z])(?=.*[A-Z])(?!.*\s)(?=.*[@$!%*?&])).{8,15}$/;
			if (regx.test(val)) {
				obj.hide();
			} else {
				obj.show();
			}
		} else if (name === "conpas") {
			var ps = $("input[name='pas']").val();
			if (val === ps) {
				obj.hide();
			} else {
				obj.show();
			}
		} else {
			obj.hide();
		}
	}
}