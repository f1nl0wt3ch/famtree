/*GET CURRENT URL*/
function getURL(){
	var url = window.location.href;
	return url.split("/")[0]+"//"+url.split("/")[2]+"/"+url.split("/")[3];
}
$("#bornDateInput, #diedDate, #bornDate, #bornModal").datepicker();
$("#bornDateInput, #diedDate, #bornDate, #bornModal").datepicker('option', 'dateFormat', 'yy-mm-dd');
$("#uploadLab").click(function(){
	$(":input[type='file']").click();
});

/*HANDLE OPTION SELECT EVENT*/
$(".option-square").click(function() {
	$("input[type='text']").val("");
	var data = $(this).attr("data");
	switch (data) {
	case "1":
		$("#diedFieldset").hide();
		$("#aliveFieldset").show();
		break;
	case "2":
		$("#aliveFieldset").hide();
		$("#diedFieldset").show();
		break;
	default:
		$("#diedFieldset").hide();
		$("#diedFieldset").hide();
		break;
	}
});
var START = 0;
/*LOAD DATA FROM PAGE LOADING*/
var loadData = function(){
	$.ajax({
		url: getURL()+"/alive",
	    method: "GET",
	    dataType: "JSON",
	    success: function(data){
	    	       $("#result").html("");
	    	       if(data.length > 6){
		    	   for(var i = 0+ START; i < 6+START; i++){
		    		   nameArray.push(data[i].fullname);
		    		   $("#result").append(
		    				   '<div class="'+'column-famtree">'+
		    				    '<div class="'+'card-famtree">'+
		    				    '<span class="'+'glyphicon glyphicon-remove glyphicon-famtree"'+'id="'+data[i].id+'"></span>'+
		    				      '<img src="'+data[i].imageLink+'" alt="'+data[i].fullname+'">'+
		    				      '<div class="'+'container-famtree">'+
		    				        '<h2>'+data[i].fullName+'</h2>'+
		    				        '<p class="'+'title">'+data[i].bornDate+'</p>'+
		    				        '<p>'+data[i].address+'</p>'+
		    				        '<p>'+data[i].email+'</p>'+
		    				        '<p><button class="'+'button-famtree" id="'+data[i].id+'" data-toggle="'+'modal" data-target="'+'#myModal">'+data[i].phone+'</button></p>'+
		    				      '</div>'+
		    				    '</div>'+
		    				  '</div>'
		    		   );
		    	     }
		    		   var pages = Math.round(data.length/6);
		    		   $("#paginationUl").html("");
		    		   for(var i=0; i < pages; i++){
		    			   $("#paginationUl").append(
		    					   "<li id='"+i+"'><a>"+(i+1)+"</a></li>"
		    					   );
		    		   }
		    		   $("#"+PAGE).addClass("active");
		    	   } else {
		    		   for(var i=0; i < data.length; i++){
			    		   nameArray.push(data[i].fullname);
			    		   $("#result").append(
			    				   '<div class="'+'column-famtree">'+
			    				    '<div class="'+'card-famtree">'+
			    				    '<span class="'+'glyphicon glyphicon-remove glyphicon-famtree"'+'id="'+data[i].id+'"></span>'+
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
		    	   }
	    },
	    error: function(){
	    	   alert("Error to load data!");
	    }
	});
}
var nameArray = new Array();
/*AUTOCOMPLETE*/
$("#searchDiv").autocomplete({
	   source: nameArray
});
var PAGE = 0;
/*HANDLE PAGE SELECTION*/
$( document ).on("click","#paginationUl li", function(){
	$("#paginationUl li").removeClass();
	$(this).addClass("active");
	var page = parseInt($(this).attr("id"));
	START = page*6;
	PAGE = page;
	loadData();
});
/*HANDLE PHONE CLICK*/
$( document).on("click",".button-famtree", function(){
	 var id= $(this).attr("id");
     $.ajax({
	    	    url : getURL()+"/update?"+id,
	 		method : "GET",
	 		crossDomain: true,
	 		success : function(data) {
	 			$("input[name='fullnameModal']").val(data.fullName);
	 			$("input[name='emailModal']").val(data.email);
	 			$("input[name='addressModal']").val(data.address);
	 			$("input[name='phoneModal']").val(data.phone);
	 			$("input[name='bornModal']").val(data.bornDate);
	 			$("input[name='idModal']").val(data.id);
	 		},
	 		error: function(){
	 			alert("error!");
	 		}
     });
});

/*HANDLE UPDATE MODAL CLICK*/
$( document ).on("click","input[name='updateModal']", function(){
	var alive = new Object();
	var fullname = $("input[name='fullnameModal']").val();
	var email = $("input[name='emailModal']").val();
	var address = $("input[name='addressModal']").val();
	var phone = $("input[name='phoneModal']").val();
	var bornDate = $("input[name='bornModal']").val();
	var id = $("input[name='idModal']").val();
	alive = {
	    fullName: fullname,
	    email: email,
	    address: address,
	    phone: phone,
	    bornDate: bornDate,
	    id: id
	}
	$.ajax({
		beforeSend: function(){
		},
		url : getURL()+"/update",
		method : "POST",
		crossDomain: true,
		dataType : "html",
		data : JSON.stringify(alive),
		success : function() {
			$('#myModal').modal('hide');
			loadData();
		},
		error: function(){
			alert("error!");
		}
	});
});

/*DELETE MEMBER*/
$( document ).on("click",".glyphicon-famtree",function(){
	var id = $(this).attr('id');
	$.ajax({
		beforeSend : function() {
		},
		url : getURL()+"/delete?"+id,
		method : "GET",
		crossDomain: true,
		success : function(xhr,response) {
				alert('Successfully deleted member!');
				loadData();
		},
		error: function(){
			alert("error!");
		}
	});
});

/*DISPLAY ALIVE PEOPLE TO RESULT DIV*/
$( document ).ready(function(){
	loadData();
	/*HANDLE INSERT NEW MEMBER*/
	$(":input[name='aliveSubmit']").click(function(){
		var alive = new Object();
		/*GET ALL INPUT*/
		var fullName = $(":input[name='fullNameInput']").val();
		var address = $(":input[name='addressInput']").val();
		var email = $(":input[name='emailInput']").val();
		var phone = $(":input[name='phoneInput']").val();
		var bornDate = $(":input[name='bornDateInput']").val();
		var detail = $(":input[name='detailTextare']").val();
		var imageLink = 'https://pbs.twimg.com/profile_images/874276197357596672/kUuht00m_400x400.jpg';
		alive = {
				fullName: fullName,
				address: address,
				email: email,
				phone: phone,
				bornDate: bornDate,
				detail: detail,
				imageLink: imageLink
		}
		if(fullName.trim().length != 0 && email.trim().length != 0 && bornDate.trim().length != 0 ){
			/*AJAX CALL HERE*/
			$.ajax({
				beforeSend : function() {
					
				},
				url : getURL()+"/alive",
				method : "POST",
				crossDomain: true,
				dataType : "html",
				data : JSON.stringify(alive),
				success : function() {
					alert("Successfully insert new member!");
					loadData();
				},
				error: function(){
					alert("error!");
				}
			});
		}
	});
	/*HANDLE INSERT NEW DIED MEMBER*/
	$(":input[name='diedSubmit']").click(function(){
		var died = new Object();
		var name = $(":input[name='fullName']").val();
		var diedDate = $(":input[name='diedDate']").val();
		var bornDate = $(":input[name='bornDate']").val();
		died = {
				name	: name,
		    diedDate: diedDate,
		    bornDate: bornDate
		}
			/*AJAX CALL HERE*/
			$.ajax({
				beforeSend : function() {
				},
				url : getURL()+"/died",
				method : "POST",
				crossDomain: true,
				dataType : "html",
				data : JSON.stringify(died),
				success : function() {
					alert("Successfully insert new member!");
					window.location = getURL()+"/died";
				},
				error: function(){
					alert("error!");
				}
			});
	});
});