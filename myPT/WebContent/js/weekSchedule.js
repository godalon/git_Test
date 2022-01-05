var today = new Date();
var day = today.getDay();

// 실행시 크기 확인하고 수행
if (window.matchMedia("(max-width: 800px)").matches == true) {
  console.log(day);
  $(document).ready(function () {
    for (var i = 0; i < 7; i++) {
      var week = "#week" + String(i);
      if (i == day) continue;
      $(week).attr("style", "display:none");
    }
    $("#weekView" + day).attr("style", "display:table-cell");
  });
} else {
  $(document).ready(function () {
    for (var i = 0; i < 7; i++) {
      var week = "#week" + String(i);
      $(week).attr("style", "display:flex");
    }
  });
  $("#weekView" + day).attr("style", "display:none");
}

// 사이즈 조절 확인 후 수행
$(window).resize(function () {
  if (window.matchMedia("(max-width: 800px)").matches == true) {
    console.log(day);
    $(document).ready(function () {
      for (var i = 0; i < 7; i++) {
        var week = "#week" + String(i);
        if (i == day) continue;
        $(week).attr("style", "display:none");
      }
      $("#weekView" + day).attr("style", "display:table-cell");
    });
  } else {
    $(document).ready(function () {
      for (var i = 0; i < 7; i++) {
        var week = "#week" + String(i);
        $(week).attr("style", "display:flex");
      }
    });
    $("#weekView" + day).attr("style", "display:none");
  }
});

// 자신 클릭 가능하게 만들기
jQuery(document).ready(function ($) {
  $(".clickable-row").click(function () {});
});

// 클릭했을때 data-href="#" #으로 이동
// jQuery(document).ready(function($) {
//   $(".clickable-row").click(function() {
//       window.location = $(this).data("href");
//   });
// });
