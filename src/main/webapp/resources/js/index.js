/*GET CURRENT URL*/
function getURL(){
	var url = window.location.href;
	return url.split("/")[0]+"//"+url.split("/")[2]+"/"+url.split("/")[3];
}
$(function(){
	$("#bornDateInput").datepicker();
	$("#bornDateInput").datepicker('option', 'dateFormat', 'yy-mm-dd');
	$("#uploadLab").click(function(){
		$(":input[type='file']").click();
	});
	var urlPost = getURL();
	$(":input[name='btn-submit']").click(function(){
		/*GET ALL INPUT*/
		var fullName = $("#fullNameInput").val();
		var address = $("#addressInput").val();
		var email = $("#emailInput").val();
		var phone = $("#phoneInput").val();
		var bornDate = $("#bornDateInput").val();
		var alive = new Object();
		alive['fullName'] = fullName;
		alive['address'] = address;
		alive['phone'] = phone;
		alive['bornDate'] = bornDate;
		$.ajax({
			beforeSend : function() {
				alert(JSON.stringify(alive));
			},
			url : urlPost,
			method : "POST",
			crossDomain: true,
			dataType : "html",
			data : JSON.stringify(alive),
			success : function() {
				alert("success!");
			},
			error: function(){
				alert("error!");
			}
		})
	}
	);
});
/*DISPLAY ALIVE PEOPLE TO RESULT DIV*/
$( document ).ready(function(){
	$.ajax({
		url: getURL()+"/alive",
	    method: "GET",
	    dataType: "JSON",
	    success: function(data){
	    	   for(var i=0; i < data.length; i++){
	    		   $("ul").append("<li>" +
	    		   		          "<div class=col-><img src='"+data[i].imageLink+"'/></div>"
	    		   		          +"<div><b>"+data[i].fullName+"</b><b>"+data[i].phone+"</b></div>"
	    		   	              +"</li>");
	    	   }
	    },
	    error: function(){
	    	   alert("Error");
	    }
	});
});