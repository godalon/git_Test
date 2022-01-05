function getWeekNo(v_date_str) {
 var date = new Date();
 if(v_date_str){
  date = new Date(v_date_str);
 }
 return Math.ceil(date.getDate() / 7);
}
console.log(new Date(2020,7));
console.log(getWeekNo(new Date(2020,7)));
var today=new Date();
var year=today.getFullYear();
var month=today.getMonth()+1;
var date=today.getDate();
var thisDay=today.getDay();
var weekDates=new Array();
var week=getWeekNo(today);
var days=new Array();

if(thisDay==0){
	for(var i=1;i<=5;i++){
		weekDates[i-1]=date+i;
	}
}else if(thisDay==6){
	for(var i=0;i<5;i++){
		weekDates[i-1]=date+i+1;
	}
}else{
	for(var i=1;i<=5;i++){
		weekDates[i-1]=date-thisDay+i;
		console.log(weekDates[i-1]);
	}
}

var json;

$.ajax({
		url:"userSchedule.do",
		type:"post",
		async:false,
		data:{
			"today":year+"-"+month+"-",
			"weekDates":weekDates,
		},
		success:function(data){
			var obj=JSON.parse(data);
			json=JSON.parse(data);
			$.each(obj,function(index,value){
				if(value.hasOwnProperty("time")){
					$("#btn"+index).text("PT");
					$("#btn"+index).attr("class","card bg-danger text-white");
					$("#btn"+index).attr("data-target","#PT");
				}else if(value.hasOwnProperty("part")){
					$("#btn"+index).text("프로그램");
					$("#btn"+index).attr("class","card bg-light");
					$("#btn"+index).attr("data-target","#program");
				}else{
					console.log("아무것도 없음");
					$("#btn"+index).attr("style","display:none");
				}
				
				
			})		
		},
		error:function(e){
			alert("실패");
			alert(e);
		}
})
$.each(json,function(index,value){
		console.log(value);
		if(value.hasOwnProperty("time")){
			$("#btn"+index).on("click",function(){
				$("#ptTime").text(value.time);
			})
		}else if(value.hasOwnProperty("part")){
			$("#btn"+index).on("click",function(){
				$("#part").text(value.part);
				$("#mention").text(value.mention);
			})
		}else{
			
		}
		
});












