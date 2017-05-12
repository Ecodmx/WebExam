<!DOCTYPE html>
<html lang="en">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>  
<%@ include file="/common/packages.jsp"%>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

		<title>chooseExam</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
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
			
					<div class="form-group" >
						
						<div class = "form-horizontal">
							<div class="col-sm-9">
							<form id="tostart"></form>
						<label for="form-field-select-3">选择考试类型:</label>
							
								<br />
								<select class="chosen-select form-control col-xs-10 col-sm-3" onchange="getExam(this.options[this.options.selectedIndex].value)" id="form-field-select-3" data-placeholder="Choose an exam type...">
								<option value="">  </option>
								
								</select>
								<label for="form-field-select-2">选择试卷名称:</label>
								<select class="chosen-select form-control col-xs-10 col-sm-3" id="form-field-select-2" data-placeholder="Choose a paper..">
								<option value="">  </option>
								
								</select>
								<button onclick="startExam()" class="btn col-xs-10 col-sm-2 btn-sm" style="margin-top:25px;margin-left:25px;">
								<i class="ace-icon glyphicon glyphicon-zoom-in"></i> 
									进入考试
								</button>
							</div>
						</div>
				

					</div>
					
		
			</div>
		</div>

</div>
  </body>
  <script type="text/javascript">


   $(document).ready(function(){
	appendData();
	var table = $('#example').DataTable({
	   "serverSide": true,
	    "ajax": {
	        url: '<%=path%>/user/getAllUser',
	        type: 'POST',
	        data: function(d){
	        	d.user_name = $('#username').val();
	        	d.e_mail = $('#email').val();
	        }
	     //   {"user_name" :$('#username').val(),"e_mail":$('#email').val()}
	    },
	   
	     "columns":[
	     	 
	      {"data":"user_name"},
	      {"data":"password"},
	      {"data":"e_mail"},
	      {"data":"description"}
	      
  		],
  		select: {
						style: 'single'
					}
  		
	 });

	});
	function appendData(){
		    $.ajax({    
	         url : "<%=path%>/exam/getType",    
	         type : "POST",    
	      //   data : $('#addForm').serialize(),    
	         success : function(data) {    
	             console.log(data);
	             $.each(data, function (i, item) {
	              $("#form-field-select-3").append("<option value='"+item.examType+"'>"+item.examType+"</option>");
	             });
	            
	         },    
	         error : function(data) {    
	         alert("error");
	//               $( '#serverResponse').html(data.status + " : " + data.statusText + " : " + data.responseText);    
	         }    
	    });
	
	}
	function getExam(x){
	$("#form-field-select-2").empty();
		$.ajax({     
	        url: "<%=path%>/exam/getExam",
	        type: "POST",
			data:{"examtype":x},	       
	        success: function (data) {
	        	console.log(data);
	            $.each(data, function (i, item) {
	              $("#form-field-select-2").append("<option value='"+item.quesIds+"'>"+item.paperName+"</option>");
	             });
	        }
    });
	}
	
	function showAddForm(){
		$('#modal-table').modal('show');
		$('#addForm')[0].reset();
	}
	function saveUser(){
var table = $('#example').DataTable();
	    $.ajax({    
	         url : "<%=path%>/user/saveUser",    
	         type : "POST",    
	         data : $('#addForm').serialize(),    
	         success : function(data) {    
	             console.log(data);
	             if(data.msg == "true"){
	             	bootbox.alert("用户新增成功",function(){
	             		$('#modal-table').modal('hide');
	             		table.ajax.reload();
	             	});
	             }
	         },    
	         error : function(data) {    
	         alert("error");
	//               $( '#serverResponse').html(data.status + " : " + data.statusText + " : " + data.responseText);    
	         }    
	    });   
		
	}
	function editUser(){
		var table = $('#example').DataTable();
		console.log(table.row('.selected').data());
		var formdata = table.row('.selected').data();
		$('#modal-table').modal('show');
		$('#i_user_id').val(formdata.user_id);
		$('#i_user_name').val(formdata.user_name);
		$('#i_password').val(formdata.password);
		$('#confirmPwd').hide();
		$('#i_e_mail').val(formdata.e_mail);
		$('#i_description').val(formdata.description);
	}
	function search(){
		var table = $('#example').DataTable();
		table.ajax.reload();
	}
	function showDelModal(){
	
	var table = $('#example').DataTable();
	var data = table.row('.selected').data();
// 	console.log(data);
	if(data == null){
		bootbox.alert("请选择一条数据");
	}else{
		$('#modal-delete-table').modal('show');
	}
 
	}
	function deleteUser(){
		
		var table = $('#example').DataTable();
		console.log(table.row('.selected').data());
		var userID = table.row('.selected').data().user_id;
		 $.ajax({    
	         url : "<%=path%>/user/delUser",    
	         type : "POST",    
	         data :{"userID":userID},    
	         success : function(data) {    
	             console.log(data);
	             if(data.msg == "true"){
	             	bootbox.alert("用户删除成功",function(){
	             		table.ajax.reload();
	             	});
	             }
	         },    
	         error : function(data) {    
	         alert("error");
	//               $( '#serverResponse').html(data.status + " : " + data.statusText + " : " + data.responseText);    
	         }    
	    });   
	}
	
	function startExam(){
		
		var quesIDS = $("#form-field-select-2").find("option:selected").val();
		
	
		window.location.href = "<%=path%>/ques/startExam?quesIds="+quesIDS;


	}
  </script>
  
</html>
