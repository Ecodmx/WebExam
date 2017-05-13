<!DOCTYPE html>
<html lang="en">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>  
<%@ include file="/common/packages.jsp"%>

<% String ids = (String)request.getAttribute("ids"); %>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="<c:url value="/css/exercise.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/icomonn.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/dataTables.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/jquery-ui.custom.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/chosen.min.css"/>" />


<script src="<c:url value="/js/jquery.knob.js"/>"></script>
<script src="<c:url value="/js/jquery.ccountdown.js"/>"></script>
<script src="<c:url value="/js/init.js"/>"></script>
<script src="<c:url value="/js/bootbox.js"/>"></script>  
<style type="text/css">
.subject-options{
	margin-left:20px;
	width:90%;
	}
				#alertModal{
		 	position: fixed;
		    top:25%;
		    lef: 50%;
		  
		   }
</style>
  </head>
  
  <body >
		<div id="top" class="container">
			<div id="wrapper">
					<div class="row" style="padding-left: 10%;">
						<div class="row">

							<div class="span8" style="padding-top: 10px;">
								<div class="ccounter2">
										<input class="knob hour" data-readOnly="true" data-width="150" data-min="0" data-max="24" data-displayPrevious=true data-fgColor="#800080" data-skin="tron" data-thickness=".2" value="75">
										<input class="knob minute" data-readOnly="true" data-width="150" data-min="0" data-max="60" data-displayPrevious=true data-fgColor="#ffec03" data-skin="tron" data-thickness=".2" value="75">
										<input class="knob second" data-readOnly="true" data-width="150" data-min="0" data-max="60" data-displayPrevious=true data-fgColor="#00CED1" data-skin="tron" data-thickness=".2" value="75">
								</div>
							</div>
						</div>
					</div>
			</div>
		</div>
		


		<div class="row">
			<div class="col-xs-12">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<input type="hidden" name="content" id="answer"/>
						<div class="subject-question">
							<pre id="ques_content"></pre>
						</div>

						<a  href="javascript:void(0);" class="subject-options " id="choice1" >
							<label class="radio ">
								<span class="icons"></span>
								<input type="radio" data-toggle="radio" value="A" >
								<pre id="select1"> </pre>
							</label>
						</a>
						<a href="javascript:void(0);" class="subject-options "  id="choice2">
							<label class="radio ">
								<span class="icons"></span>
								<input type="radio" data-toggle="radio" value="B" >
								<pre id="select2"> </pre>
							</label>
						</a>
						<a href="javascript:void(0);" class="subject-options "  id="choice3">
							<label class="radio ">
								<span class="icons"></span>
								<input type="radio" data-toggle="radio" value="C" >
								<pre id="select3"> </pre>
							</label>
						</a>
						<a href="javascript:void(0);" class="subject-options "  id="choice4">
							<label class="radio ">
								<span class="icons"></span>
								<input type="radio" data-toggle="radio" value="D" >
								<pre id="select4"> </pre>
							</label>
						</a>
						
					</div>
				</form>
			</div>
			
		</div>
		<div class="subject-next">


<input id="aheadFinish" type="submit" name="button" class="btn warning-btn" value="提前交卷">
<input id="next" type="button" name="button" onclick="nextQues()" class="btn btn-primary" value="下一题">
<input id="submitExam"  type="button" name="button" onclick="submitExam1()" class="btn btn-danger" value="提交试卷">
</div>
	<div class="answer-sheet-box open">
		
		<ul class="answer-sheet-num clearfix " id = "ques_card">
		

		</ul>
	</div>
</body>
<script type="text/javascript">
	$("#submitExam").hide();
	console.log(quesids);
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	var hours = date.getHours()+2;
	var hours1 = date.getHours();
	var min = date.getMinutes();
	var seconds = date.getSeconds();
	if(hours >= 24){
		day = day+1;
		hours = hours -24;
	}
	var time = hours+":" +date.getMinutes()+":"+date.getSeconds();

	$(".ccounter2").ccountdown(year,month,day,''+time);
	
console.log($(".ccounter2"));

		var quesids = "<%=ids%>";
		var ids = quesids.split(",");
		var count = 0;
		var answers = new Array(ids.length);
		var score = new Array(ids.length);
		score[0] = 0;
	$("a.subject-options").click(function(){
		$("a.subject-options").removeClass("selected");
		$("a.subject-options label").removeClass("checked");
		$(this).addClass("selected");
		$(this).children("label").addClass("checked");
		var answer = $(this).children("label").children("input")[0].value;
		console.log($("#answer")[0].value);
		console.log(answer);
		if(answer == $("#answer")[0].value ){
			score[count+1] = 2;
		}else{
			score[count+1] = 0;
		}
		console.log(score);
		answers[count+1] = answer;
		console.log(answers);
	});
	console.log($("a.subject-options")[0]);
		$.each(ids, function (i, item) {
				  console.log(ids);
				  var li = $("<li></li>");
	              li.append("<a href='javascript:void(0);' id='ques_"+i+"'  data-qid='"+item+"'>"+(i+1)+"</a>");
	              $("#ques_card").append(li);
	             });
	      
	   	$("#ques_card a:first").addClass("answering-num");
	  $("#ques_card a").click(function(){
			count = $(this)[0].innerHTML -1;
			console.log(count);
			if(count == ids.length-1){
				$("#aheadFinish").hide();
				$("#submitExam").show();
				$("#next").hide();
			}else{
				$("#aheadFinish").show();
				$("#submitExam").hide();
				$("#next").show();
			}
			
			$.ajax({
	         url : "<%=path%>/ques/getQuesByID",    
	         type : "POST",    
	         data : {"quesid":ids[count]},
	         success : function(data) {    
	             $("#answer")[0].value = data.answer;
	             $("#ques_content")[0].innerHTML = data.content;
	             $("#select1")[0].innerHTML=data.select1;
	             $("#select2")[0].innerHTML=data.select2;
	             $("#select3")[0].innerHTML=data.select3;
	             $("#select4")[0].innerHTML=data.select4;
	            
	         },    
	         error : function(data) {
	        	 alert("error");
	         }    
	    });   
	    	$("a.subject-options").removeClass("selected");
	    	$("a.subject-options label").removeClass("checked");
	    	var answer = answers[count+1];
	    	console.log(answer);
	    	switch(answer){
	    		case "A":	$("#choice1").addClass("selected");
	    					$("#choice1 label").addClass("checked");
							break;
	    		case "B":$("#choice2").addClass("selected");
	    					$("#choice2 label").addClass("checked");
	    					break;
	    		case "C":$("#choice3").addClass("selected");
	    					$("#choice3 label").addClass("checked");
	    					break;
	    		case "D":$("#choice4").addClass("selected");
	    				$("#choice4 label").addClass("checked");
	    					break;
	    	}
			$("a[id*='ques_']").removeClass("answering-num");
	    	$("#ques_"+count).addClass("answering-num");
	});
		$.ajax({
	         url : "<%=path%>/ques/getQuesByID",    
	         type : "POST",    
	         data : {"quesid":ids[count]},
	         success : function(data) {    
	             $("#answer")[0].value = data.answer;
	             $("#ques_content")[0].innerHTML = data.content;
	             $("#select1")[0].innerHTML=data.select1;
	             $("#select2")[0].innerHTML=data.select2;
	             $("#select3")[0].innerHTML=data.select3;
	             $("#select4")[0].innerHTML=data.select4;
	            
	         },    
	         error : function(data) {
	        	 alert("error");

	         }    
	    });   
	    
	    
	function nextQues(){
	$("a.subject-options").removeClass("selected");
	$("a.subject-options label").removeClass("checked");
	count = count+1;
			if(count == ids.length-1){
				$("#aheadFinish").hide();
				$("#submitExam").show();
				$("#next").hide();
			}else{
				$("#aheadFinish").show();
				$("#submitExam").hide();
				$("#next").show();
			}
		$.ajax({
	         url : "<%=path%>/ques/getQuesByID",    
	         type : "POST",    
	         data : {"quesid":ids[count]},
	         success : function(data) {    
	             $("#answer")[0].value = data.answer;
	             $("#ques_content")[0].innerHTML = data.content;
	             $("#select1")[0].innerHTML=data.select1;
	             $("#select2")[0].innerHTML=data.select2;
	             $("#select3")[0].innerHTML=data.select3;
	             $("#select4")[0].innerHTML=data.select4;
	            
	         },    
	         error : function(data) {
	        	 alert("error");
	         }    
	    });  
	    var answer = answers[count+1];
	    	console.log(answer);
	    	switch(answer){
	    		case "A":	$("#choice1").addClass("selected");
	    					$("#choice1 label").addClass("checked");
							break;
	    		case "B":$("#choice2").addClass("selected");
	    					$("#choice2 label").addClass("checked");
	    					break;
	    		case "C":$("#choice3").addClass("selected");
	    					$("#choice3 label").addClass("checked");
	    					break;
	    		case "D":$("#choice4").addClass("selected");
	    				$("#choice4 label").addClass("checked");
	    					break;
	    	} 
	    $("#ques_"+(count-1)).removeClass("answering-num");
	    $("#ques_"+count).addClass("answering-num");
	}
	function submitExam1(){
	
		var an = answers.toString();
		console.log(an);
		var s = an.split(",");
		console.log(s);
		
		//计算总分
		var finalScore = 0;
		$.each(score, function (i, item) {
				console.log(item);
				
				 finalScore = finalScore+item;
				
	             });
	   
		 bootbox.confirm("是否确认交卷?",function(res){
			 var now = new Date();

			var nhours = now.getHours() - hours1;
			var nmin = now.getMinutes() - min;
			var nsecond = now.getSeconds() - seconds;
			if(nsecond<0){
				nsecond = nsecond+60;
				nmin = nmin-1;
			}
			if(nmin<0){
				nmin = nmin+60;
				nhours = nhours-1;
			}
			var ntime = nhours+":" +nmin+":"+nsecond;
			
			console.log(ntime);
		 	if(res == true){
		 	$.ajax({
	         url : "<%=path%>/answer/addAnswer",    
	         type : "POST",    
	         data : {"answer":an,"score":finalScore},
	         success : function(data) {
	           window.location.href = "<%=path%>/jsp/examResult.jsp?score="+finalScore+"&time="+ntime;
	         },    
	         error : function(data) {
	        	 alert("error");
	         }    
	    });   
	 		
		 	}
		 });
	}
	
</script>
</html>
