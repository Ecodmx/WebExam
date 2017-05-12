<!DOCTYPE html>
<html lang="en">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>  
<%@ include file="/common/packages.jsp"%>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

		<title>quesManage</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<c:url value="/css/dataTables.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/jquery-ui.custom.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/css/chosen.min.css"/>" />

		
		<script src="<c:url value="/js/jquery.dataTables.min.js"/>"></script>
		<script src="<c:url value="/js/jquery.dataTables.bootstrap.min.js"/>"></script>
		<script src="<c:url value="/js/dataTables.buttons.min.js"/>"></script>
		<script src="<c:url value="/js/buttons.flash.min.js"/>"></script>
		<script src="<c:url value="/js/buttons.html5.min.js"/>"></script>
		<script src="<c:url value="/js/buttons.print.min.js"/>"></script>
		<script src="<c:url value="/js/buttons.colVis.min.js"/>"></script>
		<script src="<c:url value="/js/dataTables.select.min.js"/>"></script>

	</head>
  <body class="no-skin">
	
				
  	<div class="page-content">
	  	<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
			
					<div class="form-group" >
						
						<div class = "form-horizontal">
							<div class="col-sm-9">
							<label class="col-sm-1 control-label no-padding-left" for="form-field-1">题目:</label>
								<input type="text" id="s_content" name="user_name"  class="col-xs-4 col-sm-3" />
								<label class="col-sm-1 control-label no-padding-left" for="form-field-1">类别:</label>
								<input type="text" id="s_type" name="menuName"  class="col-xs-4 col-sm-3" />
								<label class="col-sm-1 control-label no-padding-left" for="form-field-1">创建者:</label>
								<input type="text" id="s_createUser" name="menuName"  class="col-xs-4 col-sm-3" />
								
							</div>
							
							<button onclick="search()" class="btn col-xs-10 col-sm-2 btn-sm" style="margin-left:25px;">
								<i class="ace-icon glyphicon glyphicon-zoom-in"></i>
									查询 
								</button>
						</div>
				

					</div>
					
		
			</div>
		</div>
	  	<div class="row">
			<div class="col-xs-12">
				<div class="btn-toolbar" style="margin:10px 0;">
						<button  onclick="showAddForm()"  class="btn btn-success">
							<i class="ace-icon fa fa-check"></i>
								新增题目
						</button>
						<button  onclick="editQues()" class="btn btn-info">
							<i class="ace-icon fa fa-pencil-square-o"></i>
								修改题目
						</button>
						<button  onclick="deleteUser()" class="btn btn-danger">
							<i class="ace-icon fa   fa-ban "></i>
								删除题目
						</button>

					</div>
	

		  	<table id="example" class="table table-striped table-bordered table-hover">
		   
		     <thead>
		            <tr>
						
		                <th>题目内容</th>
		                <th>题目类别</th>
		                <th>选项一</th>
		                <th>选项二</th>
		                <th>选项三</th>
		                <th>选项四</th>
		                <th>答案</th>
		                <th>创建时间</th>
		                <th>创建者</th>
		                <th>分值</th>
		                <th>备注</th>
		            </tr>
		        </thead>

		    </table>
		    </div>
	    </div>
</div>
			<div id="modal-table" class="modal fade" tabindex="-1">
					<div class="modal-dialog" >
						<div class="modal-content">
							<div class="modal-header no-padding">
								<div class="table-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
										<span class="white">&times;</span>
									</button>
									<span id="header_text">新增用户</span>
								</div>
							</div>

							<div class="modal-body no-padding">
							<form id="addForm"  class = "form-horizontal">
								<div class = "form-horizontal">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 题目内容:</label>

										<div class="col-sm-9">
											<textarea  id="i_ques_content" name="content" placeholder="content" class="col-xs-10 col-sm-8" ></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">题目类别 :</label>

										<div class="col-sm-9">
											<input type="text" id="i_ques_type" name="quesType" placeholder="quesType" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 选项一 :</label>

										<div class="col-sm-9">
											<input type="text" id="i_ques_select1" name="select1" placeholder="select1" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">选项二:</label>

										<div class="col-sm-9">
											<input type="text" id="i_ques_select2" name="select2" placeholder="select2" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">选项三:</label>

										<div class="col-sm-9">
											<input type="text" id="i_ques_select3" name="select3" placeholder="select3" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">选项四:</label>

										<div class="col-sm-9">
											<input type="text" id="i_ques_select4" name="select4" placeholder="select4" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">答案:</label>

										<div class="col-sm-9">
											<input type="text" id="i_ques_answer" name="answer" placeholder="answer" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">分值:</label>

										<div class="col-sm-9">
											<input type="text" id="i_ques_score" name="score" placeholder="score" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">备注:</label>

										<div class="col-sm-9">
											<input type="text" id="i_ques_description"  name="description" placeholder="description" class="col-xs-10 col-sm-8" />
										</div>
									</div>
								</div>
								</form>
							</div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-success pull-left" style="margin-left:33%;" data-dismiss="modal">
									<i class="ace-icon fa fa-check"></i>
									确定
								</button>
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="ace-icon fa fa-times"></i>
									关闭
								</button>
							</div>
						</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div>
<div id="modal-delete-table" class="modal fade" tabindex="-1">
						<div class="modal-dialog" >
						<div class="modal-content">
							<div class="modal-header no-padding">
								<div class="table-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
										<span class="white">&times;</span>
									</button>
									删除用户
								</div>
							</div>

							<div class="modal-body no-padding">
							
								<div class = "form-horizontal">
										<div style="margin-left:35%;">
											<h4 class="text-danger">确定删除用户？</h4>
										</div>
									
								</div>
							</div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-success pull-left" style="margin-left:33%;" data-dismiss="modal">
									<i class="ace-icon fa fa-check"></i>
									确定
								</button>
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="ace-icon fa fa-times"></i>
									关闭
								</button>
							</div>
						</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
</div>

  </body>
  <script type="text/javascript">

   $(document).ready(function(){
	
	var table = $('#example').DataTable({
	  	
	   "serverSide": true,
	    "ajax": {
	        url: '<%=path%>/ques/getAllQuestion',
	        type: 'POST',
	        data: function(d){
// 	        	d.user_name = $('#username').val();
	        	d.content = $('#s_content').val();
	        	d.quesType = $('#s_type').val();
	        	d.createUserName = $('#s_createUser').val();
	        }
	     //   {"user_name" :$('#username').val(),"e_mail":$('#email').val()}
	    },
	   
	     "columns":[
	     	   
          {"data":"content"},
          {"data":"quesType"},
	      {"data":"select1"},
	      {"data":"select2"},
	      {"data":"select3"},
	      {"data":"select4"},
	      {"data":"answer"},
	      {"data":"createTime"},
	      {"data":"createUserName"},
	      {"data":"score"},
	      {"data":"description"}
	      
  		],  		select: {
						style: 'single'
					}
	 });

	});
	function showAddForm(){
		$('#modal-table').modal('show');
		console.log($('#header_text'));
		$('#header_text')[0].innerHTML="新增题目";
		$('#addForm')[0].reset();
	}
	function saveQues(){
	var table = $('#example').DataTable();
	    $.ajax({
	         url : "<%=path%>/user/saveUser",    
	         type : "POST",    
	         data : $('#addForm').serialize(),    
	         success : function(data) {    
	             console.log(data);
	             if(data.flag == "true"){
	             	bootbox.alert(data.msg,function(){
	             		$('#modal-table').modal('hide');
	             		table.ajax.reload();
	             	});
	             }else{
	             	bootbox.alert(data.msg);
	             }
	         },    
	         error : function(data) {
	        	 alert("error");

	         }    
	    });   
		
	}
	function editQues(){
		var table = $('#example').DataTable();
		
		var formdata = table.row('.selected').data();
		console.log(formdata);
		if(formdata == null){
			bootbox.alert("请选择一条数据");
			return;
		}
			$('#modal-table').modal('show');
			$('#header_text')[0].innerHTML="修改题目";
			$('#i_ques_content').val(formdata.content);
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
  </script>
  
</html>
