<!DOCTYPE html>
<html lang="en">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>  
<%@ include file="/common/packages.jsp"%>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

		<title>answerManage</title>

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
							<label class="col-sm-1 control-label no-padding-left" for="form-field-1">时间:</label>
								<input type="text" id="s_anserTime" class="col-xs-10 col-sm-3" />
								<label class="col-sm-2 control-label no-padding-left" for="form-field-1">试卷id:</label>
								<input type="text" id="s_paperID"   class="col-xs-10 col-sm-3" />
								
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

		  	<table id="example" class="table table-striped table-bordered table-hover">
		   
		     <thead>
		            <tr>
					
		                <th>试卷id</th>
		                <th>用户id</th>
		                <th>答案</th>
		                <th>得分</th>
		                <th>答题时间</th>
		                
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
								<input type="text" id ="i_user_id" name="user_id" style="display:none" value="0"  placeholder="id" class="col-xs-10 col-sm-8" />
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户名 :</label>

										<div class="col-sm-9">
											<input type="text" id ="i_user_name" name="user_name" placeholder="Username" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 密码 :</label>

										<div class="col-sm-9">
											<input type="text" id="i_password" name="password" placeholder="password" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group" id="confirmPwd">
										<label class="col-sm-3 control-label no-padding-right"  for="form-field-1"> 确认密码 :</label>

										<div class="col-sm-9">
											<input type="text" id="i_confirmPassword" placeholder="confirmPassword" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">邮箱:</label>

										<div class="col-sm-9">
											<input type="text" id="i_e_mail" name="e_mail" placeholder="Email" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">备注:</label>

										<div class="col-sm-9">
											<input type="text" id="i_description" name="description" placeholder="description" class="col-xs-10 col-sm-8" />
										</div>
									</div>
								</form>
							</div>
							<div class="modal-footer no-margin-top">
								<button onclick="saveUser()" class="btn btn-sm btn-success pull-left" style="margin-left:33%;" >
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
								<button onclick="deleteUser()" class="btn btn-sm btn-success pull-left" style="margin-left:33%;" data-dismiss="modal">
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
	        url: '<%=path%>/answer/getAnswer', 
	        type: 'POST',
	        data: function(d){
	        	d.answerTime = $('#s_anserTime').val();
	        
	        }
	     //   {"user_name" :$('#username').val(),"e_mail":$('#email').val()}
	    },
	   
	     "columns":[
	     	 
	      {"data":"paperID"},
	      {"data":"userID"},
	      {"data":"answers"},
	      {"data":"score"},
	      {"data":"answerTime"}
	      
  		],
  		select: {
						style: 'single'
					}
  		
	 });

	});


	function search(){
		var table = $('#example').DataTable();
		table.ajax.reload();
	}

  </script>
  
</html>
