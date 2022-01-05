function isMobile() 
{
    return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
}

var myPg= "";
if (isMobile()) 
{
	myPg="kakaopay";
} 
else 
{
	myPg="inicis";	
}


//	날짜 포맷 yy-MM-dd	
	function getFormattedDate()
	{
		 let a  = new Date();
		 
		 let year= (String)(a.getFullYear()).substring(2,4);
		 let month = a.getMonth()+1;
		 let day=  a.getDate();
		 
		 if(month<10)
		 {
		   month = "0"+month;
		 }
		 if(day<10)
		 {
		  day = "0"+day;
		 }
		 
		 return year+"-"+month+"-"+day;
		 
	}



$(".pTableWrapper").click(function () 
{
 $(this).addClass("active").siblings().removeClass("active");
});



$('#selectedTrainer').val($('#trainer1').val());

function changeTrainer()
{
	$('#selectedTrainer').val($('#trainer2').val());
}

//시작일을 오늘자 이전으로 할 수 없도록 
//$('#startDate').attr('min', getFormattedDate());


var productNames= document.querySelectorAll("h3>span");
var price= document.querySelectorAll(".price");

function pay(input)
{	
	console.log($(productNames[input.id]).text());
	console.log($(price[input.id]).text());
		
	if($('#startdate').val()=="")
		{
		 alert("시작일자를 입력해주세요.");
		 return false;
		}
	
	if(	$('#selectedTrainer').val()=="")
	{
	 alert("트레이너를 입력해주세요.");
	 return false;
	}
			
	  var IMP = window.IMP;
	  IMP.init('imp50784155'); // 가맹점 식별코드
		    
	     IMP.request_pay({
	         pg : myPg,
	         pay_method : 'card',
	         merchant_uid : merchant_uid,
	         name : $(productNames[input.id]).parent().text(),
	         amount : $(price[input.id]).text(),
//	         buyer_email : userEmail,
	         buyer_email : 'tlstprl1806@naver.com',
	         buyer_name : userName,
	         buyer_tel : userTel,
	     },  function(rsp) 
		     {
		         if (rsp.success)
		         {
		        	 var myData= 
		        	 {"history": {
			     				"hid": userId,
			     				"paydate": new Date(),
			     				"price": $(price[input.id]).text(), 
			     				"hcount": $(productNames[input.id]).text(), 
			     				"tid": $('#selectedTrainer').val()
		     					},
		     		  "startdate": $('#startdate').val()
		        	 };
		        	 
		        	 
	            	 console.log(rsp);	            	 	
	            	  msg = '결제가 완료되었습니다.';
	                  msg += '\n고유ID : ' + rsp.imp_uid;
	                  msg += '\n상점 거래ID : ' + rsp.merchant_uid;
	                  msg += '\n결제 금액 : ' + rsp.paid_amount + "원";
	                  msg += '\n카드 승인번호 : ' + rsp.apply_num;
	                  alert(msg);	 
	                  
		             jQuery.ajax({
			            	 url: "paymentData.do", 
			                 type: 'POST',
			                 async: false,
			         		 data: JSON.stringify(myData),
			         		 success: function(data) 
				        		{
					               location.href="userMain.do";
				        		}
			             })
		             }
		        
		         else 
					{
					 alert('결제 실패: '+rsp.error_msg);
		         	}
		         }); 
				
}




	