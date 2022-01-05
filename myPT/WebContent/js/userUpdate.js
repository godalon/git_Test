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

$('input[type=text], input[type=password]').on('keyup', function(){
	let id = this.id;
	let regexp =/01\d{1}-\d{3,4}-\d{4}/;
	let inputVal= $(this).val();

		switch(id)
		{	
			
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

	
	
	
function check(form)
{

	if($('#password').val()!=$('#confirmPassword').val())
	{
		alert("비밀번호가 서로 다릅니다");
		return false;
	}
	
// 공백값 처리
//현재보다 이후 날짜의 생년/현재보다 이전 날짜의 시작일 불가하도록 설정 - 유효값확인 전에 가능할 지도
	
	
	return true;
		       
}
	
