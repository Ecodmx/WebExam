<%@ page language="java" import="java.util.*,com.model.*" pageEncoding="utf-8"%>
<%@ page import="java.util.*,java.io.*,java.text.*,java.net.*"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt"  prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/font-awesome/4.5.0/css/font-awesome.min.css"/>" />
   		
		<!-- page specific plugin styles -->
  
		<!-- text fonts -->
<!-- 		<link rel="stylesheet" href="<c:url value="/css/fonts.googleapis.com.css"/>" /> -->
		
		<!-- ace styles -->
		<link rel="stylesheet" href="<c:url value="/css/ace.min.css"/>" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="<c:url value="/css/ace-part2.min.css"/>" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="<c:url value="/css/ace-skins.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/ace-rtl.min.css"/>" />
		
		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="<c:url value="/css/ace-ie.min.css"/>" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="<c:url value="/js/ace-extra.min.js"/>"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="<c:url value="/js/html5shiv.min.js"/>"></script>
		<script src="<c:url value="/js/respond.min.js"/>"></script>
		<![endif]-->
		
		
		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="<c:url value="/js/jquery-2.1.4.min.js"/>"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="<c:url value="/js/jquery-1.11.3.min.js"/>"></script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='<c:url value="/js/jquery.mobile.custom.min.js"/>'>"+"<"+"/script>");
		</script>
		<script src="<c:url value="/js/bootstrap.min.js"/>"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="<c:url value="/js/excanvas.min.js"/>"></script>
		<![endif]-->
		<script src="<c:url value="/js/jquery-ui.custom.min.js"/>"></script>
		<script src="<c:url value="/js/jquery.ui.touch-punch.min.js"/>"></script>
		<script src="<c:url value="/js/jquery.easypiechart.min.js"/>"></script>
		<script src="<c:url value="/js/jquery.sparkline.index.min.js"/>"></script>
		<script src="<c:url value="/js/jquery.flot.min.js"/>"></script>
		<script src="<c:url value="/js/jquery.flot.pie.min.js"/>"></script>
		<script src="<c:url value="/js/jquery.flot.resize.min.js"/>"></script>

		<!-- ace scripts -->
		<script src="<c:url value="/js/ace-elements.min.js"/>"></script>
		<script src="<c:url value="/js/ace.min.js"/>"></script>

		<!-- inline scripts related to this page -->
