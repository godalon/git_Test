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
			case "userName":
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
				
			
			case "tel":
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
		
		if($('#nickname').val()=="관리자" || $('#nickname').val()=="admin")
		{
			wrongFormat($('#nickname'),'다른 닉네임을 입력해주세요');
			$('#nickname').val("");
			return false;
		}
		
			
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
						$('#nickname').val("");
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
					
						//qr코드 생성 (안)
						if($("#qrcode").children().length>0){
							$("#qrcode").children().remove();
						}
						
						var qrcode = new QRCode(document.getElementById("qrcode"), {
        					text: "http://192.168.0.47:8080/myPT/qrCheck.do?email="+$("#email").val(),
        					width: 500,
        					height: 500,
						});
					}
					else if(data==1)
					{				
						alert("사용 불가한 이메일입니다");
						$('#email').val("");
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
	});
	


	
//공백값 처리
//현재보다 이후 날짜의 생년/현재보다 이전 날짜의 시작일 불가하도록 설정 - 유효값확인 전에 가능할 지도



function checkUser(form)
{
	
    if($('#confirmNick').val()!=$('#nickname').val() || $("#confirmNick").val()=="")
	{
		alert("닉네임을 중복체크해주세요");
		return false;
	}
    
	    
	if($('#confirmEmail').val()!=$('#email').val() || $("#confirmEmail").val()=="")
	{
		alert("이메일을 중복체크해주세요");
		return false;
	}
	if($('#password').val()!=$('#confirmPassword').val())
	{
		alert("비밀번호가 서로 다릅니다");
		return false;
	}
	
	//생성된 qr코드의 src 값을 qrcode에 넣어줌
	$("#qrcode").val($("#qrcode img").attr("src"));

	return true;		       
}

function checkTrainer(form)
{

	if($('#confirmNick').val()!=$('#nickname').val() || $("#confirmNick").val()=="")
	{
		alert("닉네임을 중복체크해주세요");
		return false;
	}
			    
	if($('#confirmEmail').val()!=$('#email').val() || $("#confirmEmail").val()=="")
	{
		alert("이메일을 중복체크해주세요");
		return false;
	}
	if($('#password').val()!=$('#confirmPassword').val())
	{
		alert("비밀번호가 서로 다릅니다");
		return false;
	}	
	
	if($('#photo').val()==0)
	{
		alert("파일을 선택하세요.");
		return false;
	}
	
	return true;
	
}
	

$("#photo").change(function () 
{
	var maxSize  = 2 * 1024 * 1024	 
    var fileSize = this.files[0].size;
	    
    if (fileSize > maxSize) 
    {
        alert("첨부파일 사이즈는 2MB 이내로 등록 가능합니다.");
        $("#photo").val("");
        return;
    }   
    
});






	

	  
	  





