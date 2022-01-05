/**
 * 
 */

$(function(){

	$("#btnUser").click(function(){
		if($("#email").val()==""){
			alert("이메일을 입력하세요.")
			$("#email").focus();
			return false;
		}
		if($("#pw2").val()==""){
			alert("비밀번호를 입력하세요.")
			$("#pw2").focus();
			return false;
		}
		
		
		$.ajax({
			type:"post",
			url:"userLogin.do",
			data : {"email":$("#email").val(), "pw":$("#pw2").val()},
			success: function(data){
				
				
				data=data.trim();
				if(data==0){
					alert("id나 비밀번호가 틀렸습니다.");
					
					
				}else if(data==1){					
					location.href="userMain.jsp";					
				}
			},
			error: function(e){
				alert("에러가 발생했습니다.")
			}		
				
		})
		
	});	
	
	
	
});