<!DOCTYPE html>
<html lang="en">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>  
<%@ include file="/common/packages.jsp"%>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

		<title>examPaperManage</title>

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
							<label class="col-sm-1 control-label no-padding-left" for="form-field-1">试卷名称:</label>
								<input type="text" id="username" name="user_name"  class="col-xs-10 col-sm-3" />
								<label class="col-sm-2 control-label no-padding-left" for="form-field-1">试卷类型:</label>
								<input type="text" id="menuName" name="menuName"  class="col-xs-10 col-sm-3" />
								
								<button onclick="search()" class="btn col-xs-10 col-sm-2 btn-sm" style="margin-left:25px;">
								<i class="ace-icon glyphicon glyphicon-zoom-in"></i>
									查询 
								</button>
							</div>
						</div>
				

					</div>
					
		
			</div>
		</div>
	  	<div class="row">
			<div class="col-xs-12">
				<div class="btn-toolbar" style="margin:10px 0;">
						<button  onclick="add()"  class="btn btn-success">
							<i class="ace-icon fa fa-check"></i>
								新增试卷
						</button>
						<button  onclick="add()" class="btn btn-info">
							<i class="ace-icon fa fa-pencil-square-o"></i>
								修改试卷
						</button>
						<button  onclick="deleteUser()" class="btn btn-danger">
							<i class="ace-icon fa   fa-ban "></i>
								删除试卷
						</button>

					</div>
	

		  	<table id="example" class="table table-striped table-bordered table-hover">
		   
		     <thead>
		            <tr>
						<th class="center">
							<label class="pos-rel">
								<input type="checkbox" class="ace" />
								<span class="lbl"></span>
							</label>
						</th>
		                <th>试卷名称</th>
		                <th>试卷类型</th>
		                <th>题目IDS</th>
		                <th>试卷状态</th>
		                <th>分发组别(班级)</th>
		                <th>创建者</th>
		                <th>创建时间</th>
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
									添加用户
								</div>
							</div>

							<div class="modal-body no-padding">
							
								<div class = "form-horizontal">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户名 :</label>

										<div class="col-sm-9">
											<input type="text" id="form-field-1" placeholder="Username" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 密码 :</label>

										<div class="col-sm-9">
											<input type="text" id="form-field-1" placeholder="password" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 确认密码 :</label>

										<div class="col-sm-9">
											<input type="text" id="form-field-1" placeholder="confirmPassword" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">邮箱:</label>

										<div class="col-sm-9">
											<input type="text" id="form-field-1" placeholder="Email" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">备注:</label>

										<div class="col-sm-9">
											<input type="text" id="form-field-1" placeholder="description" class="col-xs-10 col-sm-8" />
										</div>
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
	        url: '<%=path%>/exam/getAllExamPaper',
	        type: 'POST',
	        data: function(d){
// 	        	d.user_name = $('#username').val();
	        	d.menuName = $('#menuName').val();
	        }
	     //   {"user_name" :$('#username').val(),"e_mail":$('#email').val()}
	    },
	   
	     "columns":[
	     	     {
                 "sClass": "text-center",
                 "data": "paperID",
                 "render": function (data, type, full, meta) {
                     return '<input type="checkbox"  class="ace"  value="' + data + '" /><span class="lbl"></span>';
                 },
                 "bSortable": false
             },
	      {"data":"paperName"},
	      {"data":"examType"},
	      {"data":"quesIds"},
	      {"data":"state"},
	      {"data":"toGroup"},
	      {"data":"createUser"},
	      {"data":"createTime"},
	      {"data":"description"}
  		]
	 });

	});
	function add(){
		$('#modal-table').modal('show');
	}
	function search(){
		var table = $('#example').DataTable();
		table.ajax.reload();
	}
	function deleteUser(){
		$('#modal-delete-table').modal('show');
	}
  </script>
  
</html>
