<!DOCTYPE html>
<html lang="en">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>  
<%@ include file="/common/packages.jsp"%>
<%String time = request.getParameter("time");
	String score  = request.getParameter("score");
 %>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

		<title>chooseExam</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<c:url value="/css/exercise.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/icomonn.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/dataTables.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/jquery-ui.custom.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/chosen.min.css"/>" />
		<style type="text/css">
			#alertModal{
		 	position: fixed;
		    top:25%;
		    lef: 50%;
		  
		   }
		</style>
		
		<script src="<c:url value="/js/jquery.dataTables.min.js"/>"></script>
		<script src="<c:url value="/js/jquery.dataTables.bootstrap.min.js"/>"></script>
		<script src="<c:url value="/js/dataTables.buttons.min.js"/>"></script>
		<script src="<c:url value="/js/buttons.flash.min.js"/>"></script>
		<script src="<c:url value="/js/buttons.html5.min.js"/>"></script>
		<script src="<c:url value="/js/buttons.print.min.js"/>"></script>
		<script src="<c:url value="/js/buttons.colVis.min.js"/>"></script>
		<script src="<c:url value="/js/dataTables.select.min.js"/>"></script>
		<script src="<c:url value="/js/bootbox.js"/>"></script>  
	</head>
  <body class="no-skin">
	
				
  	<div class="page-content">
	  	<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
			
					
					<div class="review-report">
					<div class="graph-box clearfix">
					<div class="graph-info">
					<div class="graph-statistic">
					
					</div>
					<div class="content-item-order graph-detail"  style="font-size:22px ;color:#438EB9;">

					<p>
					<span class="item-label" >得分：</span>
					<span class="item-label" id = "score"></span>
					</p>
					<p>
					<span class="item-label" >用时：</span>
					<span class="item-label" id="time"></span>
					</p>
					</div>
					</div>
					</div>
					</div>
		
			</div>
		</div>

</div>
  </body>
  <script type="text/javascript">
	var score = "<%=score %>";
	var time = "<%=time %>";
	console.log(score);
	console.log(time);
  	$("#score")[0].innerHTML = score;
  	$("#time")[0].innerHTML = time;
  </script>
  
</html>
