//현재 날짜
// chart1에서 먼저 선언하였음
today = new Date();
year = today.getFullYear();
month = today.getMonth()+1;

var montharr= new Array();

for(var i=1; i<=month;i++){
	montharr.push(i+"월");
}



// 월선택 셀렉트
for (var count = 0; count < montharr.length; count++) {
  var option = $(
    "<option value='" + (count+1) + "'>" + montharr[count] + "</option>"
  );
  $("#selectmonth").append(option);
}
////////////////////////////



$(window).resize(function () {
  if (window.matchMedia("(max-width: 950px)").matches == true) {
    $("#card1").attr("class", "col-6");
    $("#card2").attr("class", "col-6");
    $("#card3").attr("class", "col-6");
    $("#card4").attr("class", "col-6");
  } else if (window.matchMedia("(max-width: 450px)").matches == true) {
    $("#card1").attr("class", "col-12");
    $("#card2").attr("class", "col-12");
    $("#card3").attr("class", "col-12");
    $("#card4").attr("class", "col-12");
  } else {
    $("#card1").attr("class", "col-3");
    $("#card2").attr("class", "col-3");
    $("#card3").attr("class", "col-3");
    $("#card4").attr("class", "col-3");
  }
});
