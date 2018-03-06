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
$(":input[name='btnSubmit']").click(function(){
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
				loadData();
			},
			error: function(){
				alert("error!");
			}
		});
	}
}
);
/*LOAD DATA FROM PAGE LOADING*/
var loadData = function(){
	$.ajax({
		url: getURL()+"/alive",
	    method: "GET",
	    dataType: "JSON",
	    success: function(data){
	    	 alert(getURL());
	    	       $("#result").html("");
	    	       var pages = $(":input[name='pages']").val();
	    	       var nameArray = new Array();
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
		    	   $("#searchDiv").autocomplete({
		    		   source: nameArray
		    	   });
	    },
	    error: function(){
	    	   alert("Error to load data!");
	    }
	});
}

/*DISPLAY ALIVE PEOPLE TO RESULT DIV*/
$( document ).ready(function(){
	loadData();
	/*DELETE MEMBER*/
	$(".glyphicon-famtree").on('click',function(){
		var id = $(this).attr('id');
		$.ajax({
			beforeSend : function() {
				alert(id);
			},
			url : getURL()+"/delete",
			method : "POST",
			crossDomain: true,
			dataType : "html",
			data : JSON.stringify(id),
			success : function(xhr,response) {
				if(response === 1){
					alert('Successfully deleted member!');
					loadData();
				}
			},
			error: function(){
				alert("error!");
			}
		});
	});
});