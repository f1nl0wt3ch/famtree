/*GET CURRENT URL*/
function getURL(){
	var url = window.location.href;
	return url.split("/")[0]+"//"+url.split("/")[2]+"/"+url.split("/")[3];
}
$("#bornDateInput").datepicker();
$("#bornDateInput").datepicker('option', 'dateFormat', 'yy-mm-dd');
$("#uploadLab").click(function(){
	$(":input[type='file']").click();
});
var urlPost = getURL();
$(":input[name='btnSubmit']").click(function(){
	/*GET ALL INPUT*/
	var fullName = $(":input[name='fullNameInput']").val();
	var address = $(":input[name='addressInput']").val();
	var email = $(":input[name='emailInput']").val();
	var phone = $(":input[name='phoneInput']").val();
	var bornDate = $(":input[name='bornDateInput']").val();
	var detail = $(":input[name='detailTextare']").val();
	var alive = new Object();
	alive = {
			fullName: fullName,
			address: address,
			email: email,
			phone: phone,
			bornDate: bornDate,
			detail: detail
	}
	$.ajax({
		beforeSend : function() {
			
		},
		url : urlPost,
		method : "POST",
		crossDomain: true,
		dataType : "html",
		data : JSON.stringify(alive),
		success : function() {
			alert("Successfully inserted new member!");
			$("#result").load();
		},
		error: function(){
			alert("error!");
		}
	})
}
);
/*DISPLAY ALIVE PEOPLE TO RESULT DIV*/
$( document ).ready(function(){
	$.ajax({
		url: getURL()+"/alive",
	    method: "GET",
	    dataType: "JSON",
	    success: function(data){
	    	       var nameArray = new Array();
		    	   for(var i=0; i < data.length; i++){
		    		   nameArray.push(data[i].fullname);
		    		   $("#result").append(
		    				   '<div class="'+'column-famtree">'+
		    				    '<div class="'+'card-famtree">'+
		    				      '<img src="'+data[i].imageLink+'" alt="'+data[i].fullname+'">'+
		    				      '<div class="'+'container-famtree">'+
		    				        '<h2>'+data[i].fullName+'</h2>'+
		    				        '<p class="'+'title">'+data[i].bornDate+'</p>'+
		    				        '<p>'+data[i].address+'</p>'+
		    				        '<p>'+data[i].email+'</p>'+
		    				        '<p><button class="'+'button-famtree">'+data[i].phone+'</button></p>'+
		    				      '</div>'+
		    				    '</div>'+
		    				  '</div>'
		    		   );
		    	     }
		    	   $("#searchDiv").autocomplete({
		    		   source: nameArray
		    	   });
	    },
	    error: function(){
	    	   alert("Error");
	    }
	});
});