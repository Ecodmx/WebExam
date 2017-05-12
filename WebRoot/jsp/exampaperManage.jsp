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
							<label class="col-sm-2 control-label no-padding-left" for="s_paperName">试卷名称:</label>
								<input type="text" id="s_paperName" name="paperName"  class="col-xs-3 col-sm-3" />
								<label class="col-sm-2 control-label no-padding-left" for="s_papaerType">试卷类型:</label>
								<input type="text" id="s_papaerType" name="papaerType"  class="col-xs-3 col-sm-3" />
								

							</div>
								<button onclick="search()" class="btn col-xs-3 col-sm-2 btn-sm" style="margin-left:25px;">
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
								新增试卷
						</button>
						<button  onclick="editExam()" class="btn btn-info">
							<i class="ace-icon fa fa-pencil-square-o"></i>
								修改试卷
						</button>
						<button  onclick="deleteExam()" class="btn btn-danger">
							<i class="ace-icon fa   fa-ban "></i>
								删除试卷
						</button>

					</div>
	

		  	<table id="example" class="table table-striped table-bordered table-hover">
		   
		     <thead>
		            <tr>
						
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
			<!-- 模态框表单 -->
			<div id="modal-table" class="modal fade" tabindex="-1">
					<div class="modal-dialog" >
						<div class="modal-content">
							<div class="modal-header no-padding">
								<div class="table-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
										<span class="white">&times;</span>
									</button>
									<span id = "header_text">新增试卷</span>
								</div>
							</div>

							<div class="modal-body no-padding">
							
								<form id="addForm" class = "form-horizontal">
								<input type="text" id ="i_paperid" name="paperID" style="display:none" value="0"  placeholder="id" class="col-xs-10 col-sm-8" />
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 试卷名称:</label>

										<div class="col-sm-9">
											<input type="text" id="i_paperName" name="paperName" placeholder="paperName" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 试卷类型 :</label>

										<div class="col-sm-9">
											<select class="chosen-select form-control " id="i_examType" name="examType" style="width:295px;" onchange="getExam(this.options[this.options.selectedIndex].value)" id="form-field-select-3" data-placeholder="Choose an exam type...">
												<option value="">  </option>
								
											</select>
										
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">题目IDs:</label>

										<div class="col-sm-9">
											<input type="text" id="i_quesIDs" name="quesIds" placeholder="quesIds" class="col-xs-10 col-sm-8" />
											<button id="quesBtn"  type="button" class="btn btn-info btn-sm dropdown-toggle">
													选择题目
													
										</button>
										</div>
									
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">状态:</label>

										<div class="col-sm-9">
											<input type="text"  id="i_state" name="state" placeholder="state" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">分发组别:</label>

										<div class="col-sm-9">
											<input type="text"  id="i_toGroup" name="toGroup" placeholder="toGroup" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1">备注:</label>

										<div class="col-sm-9">
											<input type="text"  id="i_description" name="description" placeholder="description" class="col-xs-10 col-sm-8" />
										</div>
									</div>
									</form>
								</div>
							</div>
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-success pull-left"   onclick="savePaper()" style="margin-left:33%;" data-dismiss="modal">
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
<div id="modal-chooseQues" class="modal fade"  tabindex="-1">
					<div class="modal-dialog" style="width:1000px" >
						<div class="modal-content">
							<div class="modal-header no-padding">
								<div class="table-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
										<span class="white">&times;</span>
									</button>
									选择题目
								</div>
							</div>

							<div class="modal-body no-padding">
								<div class="row">
									<div class="col-xs-12">
									  	<table id="table_ques" style="width:100%" class="table table-striped table-bordered table-hover">
		   
									     <thead>
									            <tr>
													
									                <th >题目内容</th>
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
							<div class="modal-footer no-margin-top">
								<button class="btn btn-sm btn-success pull-left"   onclick="chooseQuest()" style="margin-left:33%;" data-dismiss="modal">
									<i class="ace-icon fa fa-check"></i>
									确定
								</button>
								<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
									<i class="ace-icon fa fa-times"></i>
									关闭
								</button>
							</div>
						</div>

					</div>
					
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
	   $("#quesBtn").click(function(){  
	         $("#modal-chooseQues").modal("show");  
	   });  
		appendData();
		
	 
	var table = $('#example').DataTable({
	   "serverSide": true,
	    "ajax": {
	        url: '<%=path%>/exam/getAllExamPaper',
	        type: 'POST',
	        data: function(d){
// 	        	d.user_name = $('#username').val();
	        	d.paperName = $('#s_paperName').val();
	        	d.examType = $('#s_papaerType').val();
	        }
	     //   {"user_name" :$('#username').val(),"e_mail":$('#email').val()}
	    },
	   
	     "columns":[

	      {"data":"paperName"},
	      {"data":"examType"},
	      {"data":"quesIds"},
	      {"data":"state"},
	      {"data":"toGroup"},
	      {"data":"createUser"},
	      {"data":"createTime"},
	      {"data":"description"}
  		],  
  			select: {
						style: 'single'
					}
	 });
	 var table2 = $('#table_ques').DataTable({
		"serverSide": true,

	    "ajax": {
	        url: '<%=path%>/ques/getAllQuestion',
	        type: 'POST',

	     //   {"user_name" :$('#username').val(),"e_mail":$('#email').val()}
	    },
	   	"bAutoWidth":false,
		
	   
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
						style: 'milty'
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
	              $("#i_examType").append("<option value='"+item.examType+"'>"+item.examType+"</option>");
	             });
	            
	         },    
	         error : function(data) {    
	         alert("error");
	//               $( '#serverResponse').html(data.status + " : " + data.statusText + " : " + data.responseText);    
	         }    
	    });
	
	}
	function showAddForm(){
		$('#modal-table').modal('show');
		console.log($('#header_text'));
		$('#header_text')[0].innerHTML="新增试卷";
		$('#addForm')[0].reset();
		$('#i_quesIDs').attr("disabled",true);
	}
	function savePaper(){
	var table = $('#example').DataTable();
	    $.ajax({
	         url : "<%=path%>/exam/savePaper",    
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
	function editExam(){
		var table = $('#example').DataTable();
		
		var formdata = table.row('.selected').data();
		console.log(formdata);
		if(formdata == null){
			bootbox.alert("请选择一条数据");
			return;
		}
			$('#modal-table').modal('show');
			$('#header_text')[0].innerHTML="修改试卷";
			$('#i_paperid').val(formdata.paperID);
			$('#i_paperName').val(formdata.paperName);
			$('#i_examType').val(formdata.examType);
			$('#i_quesIDs').val(formdata.quesIds);
			$('#i_quesIDs').attr("disabled",true);
			$('#i_state').val(formdata.state);
			$('#i_toGroup').val(formdata.toGroup);
			
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
	function deleteExam(){
		
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
	function chooseQuest(){
		
		var table2 = $('#table_ques').DataTable();
		var formdata = table2.rows('.selected').data();
		var quesIDs = "";
		if(formdata == null) return;
		 $.each(formdata, function (i, item) {
		 		if(i == formdata.length-1){
		 			quesIDs = quesIDs+ item.quesID ;
		 		}else{
		 			quesIDs = quesIDs+ item.quesID + ",";
		 		}
	            
	     });
	    $('#i_quesIDs').val( quesIDs);
	    
		
		
	}
	
  </script>
  
</html>
