function rightFormat(input)
{
	$(input).css('borderColor','green');	
	$(input).siblings('div').html('<img src="img/greenCheck.png" width="20px">');
}

function neutralFormat(input)
{
	$(input).css('borderColor','');
	$(input).siblings('div').html('');
}

function wrongFormat(input, text)
{
	$(input).css('borderColor','red');	
	$(input).siblings('div').html(text).css('color', 'red');
}

$('input[type=text], input[type=password], input[type=email]').on('keyup', function(){
	let id = this.id;
	let regexp =/01\d{1}-\d{3,4}-\d{4}/;
	let inputVal= $(this).val();

		switch(id)
		{	
			case "trainerName":
				if(inputVal.length<2)
				{
					wrongFormat($(this),'2자 이상 입력해주세요')
				}
				else if(inputVal.length>=2)
				{
					rightFormat($(this));
				}
				break;
				
			case "nickname":
				if($('#confirmNick').val()!=inputVal)
				{
					neutralFormat($(this))
				}
				break;
				
			
			case "phone":
				if(!regexp.test(inputVal))
				{
					wrongFormat($(this),'올바른 번호를 입력해주세요')
				}
				else
				{
					rightFormat($(this));				
				}
				break;
			
			case "email":
				if($('#confirmEmail').val()!=inputVal)
				{
					neutralFormat($(this))
				}
				break;
				
			case "password":
			case "confirmPassword":
				if($('#password').val()=="" || $('#confirmPassword').val()=="")
				{
					neutralFormat($('#confirmPassword'));
					$('#password').css('borderColor','');
				}
				else if ($('#password').val()!=$('#confirmPassword').val()) 
				{
				  wrongFormat($('#confirmPassword'),'비밀번호가 일치하지 않습니다')
				  $('#password').css('borderColor','red');
	            } 

	            else
	            {
					rightFormat($('#confirmPassword'));
					$('#password').css('borderColor','green');	
	            }
			break;

	}
	
})



	
	$('#nickCheck').click(function()
		{
//		한글,숫자,영문자
		let regexp=/^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\*]+$/;

		if($('#nickname').val().length<2 ||!regexp.test($('#nickname').val()))
			{
				wrongFormat($('#nickname'),'한글,숫자,영문자/2자 이상 입력해주세요 ');
				return false;
			}
		// 현재닉네임일 경우(윤)
		if($('#nowNick').val()==$('#nickname').val()){
			alert("현재 닉네임입니다.");
			rightFormat($('#nickname'));
		}else{
			
			$.ajax({
				type:"post",
				url:"nickCheck.do",
				data : {"nickname":$("#nickname").val()},
				success: function(data){
					data=data.trim();
					console.log(data);
					if(data==0)
					{
						alert("사용 가능한 닉네임입니다");
						$("#confirmNick").val($('#nickname').val());
						rightFormat($('#nickname'));

					}
					else if(data==1)
					{				
						alert("사용 불가한 닉네임입니다");
						$('#nickname').val($('#nowNick').val()); // 원래닉네임으로 (윤)
					}
					else
					{
						alert("오류 발생");
					}
				},
				error: function(e){
					alert("에러가 발생했습니다.")
				}		
					
		});
		}
	});

	
	$('#emailCheck').click(function()
		{
//		이메일 확인용 정규식
			let regexp=/^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$/;
			
			if(!regexp.test($('#email').val()))
			{
				wrongFormat($('#email'),'올바른 이메일을 입력해주세요');
				return false;
			}
			// 현재이메일일 경우(윤)
		if($('#nowEmail').val()==$('#email').val())
			{
			alert("현재 이메일입니다.");
			rightFormat($('#email'));
			} else{

				$.ajax({
					type:"post",
					url:"emailCheck.do",
					data : {"email":$("#email").val()},
					success: function(data){
						data=data.trim();
						console.log(data);
						if(data==0)
						{
							alert("사용 가능한 이메일입니다");
							$('#confirmEmail').val($('#email').val());
							 rightFormat($('#email'));
						}
						else if(data==1)
						{				
							alert("사용 불가한 이메일입니다"); 
							$('#email').val($('#nowEmail').val()); // 현재이메일로(윤)
						}
						else
						{
							alert("오류 발생");
						}
					},
					error: function(e){
						alert("오류가 발생했습니다.")
					}		
						
				});
		}
	});
	
function cancel(){
	if(confirm("변경 내용은 사라집니다 계속하시겠습니까?")){
		window.location.href="trainerList.do";
	}else {
		return false;
	}
}
	
	
	
	
function check(form)
{
// 현재 닉네임,이메일이면 그냥 넘어가도록 설정 (윤)
    if($('#confirmNick').val()!=$('#nickname').val() && $('#nowNick').val()!=$('#nickname').val())
	{
		alert("닉네임을 중복체크해주세요");
		return false;
	}
	    
	if($('#confirmEmail').val()!=$('#email').val() && $('#nowEmail').val()!=$('#email').val())
	{
		alert("이메일을 중복체크해주세요");
		return false;
	}
	if($('#password').val()!=$('#confirmPassword').val())
	{
		alert("비밀번호가 서로 다릅니다");
		return false;
	}
	
// 공백값 처리
//현재보다 이후 날짜의 생년/현재보다 이전 날짜의 시작일 불가하도록 설정 - 유효값확인 전에 가능할 지도
	
	
	return true;
		       
}
	
