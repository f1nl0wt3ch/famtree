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