//현재 날짜
//	날짜 포맷 yy.MM.dd	
	function getFormattedDate()
	{
		 let today  = new Date();
		 
		 let year= (String)(today.getFullYear()).substring(2,4);
		 let month = today.getMonth()+1;
		 let day=  today.getDate();
		 
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
	
var crowded = document.querySelector("#crowded");
var date = getFormattedDate();

		
		$.ajax({
			type:"post",
			url:"crowded.do",			
			data : {"today":date},
			success: function(data){			
				
				data=data.trim();
				if(data==0){
					var htmldata = "<i class='fas fa-smile fa-2x text-success'></i>";
					crowded.innerHTML = htmldata;
				}else if(data==1){			
					var htmldata = "<i class='fas fa-meh fa-2x text-warning'></i>";
					crowded.innerHTML = htmldata;
				}else {
					var htmldata = "<i class='fas fa-frown fa-2x text-danger'></i>";
					crowded.innerHTML = htmldata;
				}
			},
			error: function(e){
				alert("에러가 발생했습니다.")
								
			}		
				
		})
		
	
	
	
	
