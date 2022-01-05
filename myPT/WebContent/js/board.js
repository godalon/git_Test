if (window.matchMedia("(max-width: 940px)").matches == true) {
  $("#title").css("width", "40%");
}
$(window).resize(function () {
  if (window.matchMedia("(max-width: 940px)").matches == true) {
    $("#title").css("width", "40%");
  } else {
  }
});