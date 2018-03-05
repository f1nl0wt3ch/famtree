<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=BenchNine|Satisfy|Shadows+Into+Light" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/libs/jquery-ui-1.11.1/jquery-ui.css">
<link rel="stylesheet" href="resources/css/card.css" />
<link rel="stylesheet" href="resources/css/normalize.css" />
<link rel="stylesheet" href="resources/css/demo.css" />
<link rel="stylesheet" href="resources/css/component.css" />
<link rel="stylesheet" href="resources/css/index.css">

<!-- JAVASCRIPT AND JQUERY -->
<script src="https://code.jquery.com/jquery-1.12.1.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<title>FAMILY TREE</title>
<body>
	<div class="container">
	    <div class="row">
	       <!-- FORM TO UPLOAD EXCEL FILE -->
			<div class="col-sm-4 col-xs-12 nav-header">
			</div>
			<!-- col-sm-4 col-xs-12 -->
			<!-- ALL MEMBER OF FAMILY -->
	       <div class="col-sm-8 col-xs-12 nav-header">
	           
	       </div><!-- col-sm-8 col-xs-12 -->
	    </div><!-- row -->
	    <div class="row">
	       <!-- FORM TO UPLOAD EXCEL FILE -->
	       <div class="col-sm-4 col-xs-12 nav-body">
	          <fieldset>
	             <legend>Insert new member</legend>
                 <div class="form-group">
                      <label for="fullNameInput">Fullname</label>
                      <input type="text" name="fullNameInput" class="form-control">
                 </div>
                 <div class="form-group">
                      <label for="addressInput">Address</label>
                      <input type="text" name="addressInput" class="form-control">
                 </div>
                   <div class="form-group">
                      <label for="emailInput">Email</label>
                      <input type="text"  name="emailInput" class="form-control">
                 </div>
                 <div class="form-group">
                      <label for="phoneInput">Phone</label>
                      <input type="text" name="phoneInput" class="form-control">
                 </div>
                 <div class="form-group">
                      <label for="bornDateInput">Borndate</label>
                      <input type="text" name="bornDateInput" id="bornDateInput" class="form-control">
                 </div>
                 <div class="form-group">
                      <label for="detailTextarea">More information:</label>
                      <textarea name="detailTextarea" class="form-control" rows="5" cols="10"></textarea>
                 </div>
                 <div class="form-group">
						 <input type="file" name="file-1[]" id="imageFile" class="inputfile inputfile-1" data-multiple-caption="{count} files selected" multiple />
						 <label for="file-1"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/></svg> <span>Choose a file&hellip;</span></label>
	            </div>
	            <div class="form-group">
                      <input type="button" class="btn-submit" name="btnSubmit" value="Submit">
                      <input type="button" class="btn-submit" name="btnCancel" value="Cancel">
                 </div>
               </fieldset>   
			</div><!-- col-sm-4 col-xs-12 -->
	       <!-- ALL MEMBER OF FAMILY -->
	       <div class="col-sm-8 col-xs-12 nav-body">
	               <fieldset>
	                     <div class="col-sm-12 col-xs-12" id="searchDiv">
	                       <div class="input-group">
						    <span class="input-group-addon">Search member</span>
						    <input id="memberInput" type="text" class="form-control" name="memberInput" placeholder="Enter a name">
						  </div>
	                     </div>
	                    <legend>All member</legend>
	                    <div id="result"></div>
	                    <input type="hidden" name="totalAlive" value="${totalAlive }">
	                    <c:if test="${totalAlive > 1}">
	                        <ul class="pagination pagination-lg" id="paginationUl">
		                        <c:forEach var="alive" begin="0" end="2">
		                            <li><a href="#"><c:out value ="${alive}"/></a></li>
		                        </c:forEach>
	                        </ul>
	                    </c:if>
	               </fieldset>
	       </div><!-- col-sm-8 col-xs-12 -->
	    </div><!-- row -->
	</div><!-- container -->
<script type="text/javascript" src="resources/js/index.js"></script>	
<script src="resources/js/custom-file-input.js"></script>
</body>
</html>
