Chart.defaults.global.defaultFontFamily = '-apple-system, BlinkMacSystemFont, "Nanum Gothic", Roboto, "Nanum Gothic", Arial, "Nanum Gothic", sans-serif, "Nanum Gothic",  "Nanum Gothic", "Nanum Gothicl", "Nanum Gothic"';

// 트레이너별 월 수익
// chart1에서 먼저 선언하였음
//현재 날짜
today = new Date();
year = today.getFullYear();
month = today.getMonth() + 1;

//차트 데이터
var income = new Array();
var tid = new Array();
var tname = new Array();
var tphoto = new Array();

$.ajax({
	url: "incomeTrainerChart.do",
	type: "post",
	async: false,
	data: { "year": year, "month": month },
	success: function(data) {
		data = $.parseJSON(data);
		for (var i = 0; i < data.length; i++) {
			tid.push(data[i].tid);
			income.push(data[i].income);
			tname.push(data[i].tname);
			tphoto.push(data[i].tphoto);
		}
	},
	error: function(e) {
		alert(e);
	}

}) 

Chart.plugins.register({
	beforeRender: function(chart) {
		if (chart.config.options.showAllTooltips) {
			// create an array of tooltips
			// we can't use the chart tooltip because there is only one tooltip per chart
			chart.pluginTooltips = [];
			chart.config.data.datasets.forEach(function(dataset, i) {
				chart.getDatasetMeta(i).data.forEach(function(sector, j) {
					chart.pluginTooltips.push(
						new Chart.Tooltip(
							{
								_chart: chart.chart,
								_chartInstance: chart,
								_data: chart.data,
								_options: chart.options.tooltips,
								_active: [sector],
							},
							chart
						)
					);
				});
			});

			// turn off normal tooltips
			chart.options.tooltips.enabled = false;
		}
	},
	afterDraw: function(chart, easing) {
		if (chart.config.options.showAllTooltips) {
			// we don't want the permanent tooltips to animate, so don't do anything till the animation runs atleast once
			if (!chart.allTooltipsOnce) {
				if (easing !== 1) return;
				chart.allTooltipsOnce = true;
			}

			// turn on tooltips
			chart.options.tooltips.enabled = true;
			Chart.helpers.each(chart.pluginTooltips, function(tooltip) {
				tooltip.initialize();
				tooltip.update();
				// we don't actually need this since we are not animating tooltips
				tooltip.pivot();
				tooltip.transition(easing).draw();
			});
			chart.options.tooltips.enabled = false;
		}
	},
});

//CHART2
//////////////////////////////////////////////////
var ctx2 = document.getElementById("myChart2");
var charData2 = {
	labels: tname,
	datasets: [
		{
			label: "월 수익(만원)",
			yAxisID: "A",
			data: income,
			backgroundColor: "rgba(75, 192, 192, 0.8)",
		},
	],
};
var myChart2 = new Chart(ctx2, {
	type: "bar",
	data: charData2,
	// 옵션
	options: {
		scales: {
			yAxes: [
				{
					id: "A",
					type: "linear",
					position: "left",
					ticks: {
						suggetedmin: 0,
	          				suggestedmax: 2000,
					},
				},
			],
		},
	},
});

// 이달의 트레이너 처음 뿌릴 때
//	var monthtrainer = new Array();
//	var monthtrainerphoto = new Array();
//	var max = Math.max.apply(null, income);
//	for (var i = 0; i < income.length; i++) {
//		if (income[i] == max) {
//			monthtrainer.push(tname[i]);
//			monthtrainerphoto.push(tphoto[i]);
//		}
//	}
//	var tmonth = document.querySelector("#tmonthname");
//	var tmonthphoto = document.querySelector("#tmonthphoto");
//	var tmonthdata = new String();
//	var tmonthphotodata = new String();
//	for (var i = 0; i < monthtrainer.length; i++) {
//		
//		
//		var data = "<h5 class='card-title'>" + monthtrainer[i] + " 트레이너 </h5>";
//		var photodata = "<img src='img/TrainerPhoto/"+monthtrainerphoto[i]+"' class='card-img-top' />";
//		tmonthdata += data;
//		tmonthphotodata+=photodata;
//	}
//	tmonthphoto.innerHTML = tmonthphotodata;
//	tmonth.innerHTML = tmonthdata;

/////////////////이달의 트레이너

// 이달의 트레이너 처음 뿌릴 때	
	var monthtrainer = new Array();
	var monthtrainerphoto = new Array();
	var max = Math.max.apply(null, income);
	for (var i = 0; i < income.length; i++) {
		if (income[i] == max) {
			monthtrainer.push(tname[i]);
			monthtrainerphoto.push(tphoto[i]);
		}
	}
	var tmonth = document.querySelector("#tmonth");	
	var data = new String();
	if(monthtrainer.length>1){
		for (var i = 0; i < monthtrainer.length; i++) {			
	
		data += "<div class='card'><div class='card-body'><div id='tmonthname"+(i+1)+"'></div></div></div>";			
		
		}
		tmonth.innerHTML = data;
		
		for(var i=0;i<monthtrainer.length;i++){
			var tmonthname = document.querySelector("#tmonthname"+(i+1));			
			var namedata = "<h5 class='card-title'>" + monthtrainer[i] + " 트레이너 </h5>";	
			tmonthname.innerHTML = namedata;
		}
	}else {
		data += "<div class='card'><div id='tmonthphoto'></div><div class='card-body'><div id='tmonthname'></div></div></div>";
		tmonth.innerHTML = data;
		
		var tmonthname = document.querySelector("#tmonthname");
		var tmonthphoto = document.querySelector("#tmonthphoto");
		var namedata = "<h5 class='card-title'>" + monthtrainer[0] + " 트레이너 </h5>";
		var photodata = "<img src='img/TrainerPhoto/"+monthtrainerphoto[0]+"' class='card-img-top' />";
				
		tmonthphoto.innerHTML = photodata;
		tmonthname.innerHTML = namedata;
		
	}
	
//	for (var i = 0; i < monthtrainer.length; i++) {			
//	
//		data += "<div class='card'><div id='tmonthphoto"+(i+1)+"'></div><div class='card-body'><div id='tmonthname"+(i+1)+"'></div></div></div>";			
//		
//	}
//	tmonth.innerHTML = data
//	
//	for(var i=0;i<monthtrainer.length;i++){
//		var tmonthname = document.querySelector("#tmonthname"+(i+1));
//		var tmonthphoto = document.querySelector("#tmonthphoto"+(i+1));
//		var namedata = "<h5 class='card-title'>" + monthtrainer[i] + " 트레이너 </h5>";
//		var photodata = "<img src='img/TrainerPhoto/"+monthtrainerphoto[i]+"' class='card-img-top' />";
//				
//		tmonthphoto.innerHTML = photodata;
//		tmonthname.innerHTML = namedata;
//	}

/////////////////이달의 트레이너



//연도 선택 이벤트
$("#selectyear2").change(function() {
	month = today.getMonth()+1;
	if ($("#selectyear2").val() < year) {
		month = 12;
	}
	
	year = $("#selectyear2").val();

	var montharr = new Array();
	for (var i = 1; i <= month; i++) {
		montharr.push(i + "월");
	}

	$("#selectmonth").empty();
	$("#selectmonth").append("<option value='default' disabled selected>월 선택</option>");

	for (var count = 0; count < montharr.length; count++) {
		var option = $(
			"<option value='" + (count + 1) + "'>" + montharr[count] + "</option>"
		);
		$("#selectmonth").append(option);
	}

});




// 월 선택이벤트
$("#selectmonth").change(function() {
	month = $("#selectmonth").val();
	tid = new Array();
	income = new Array();
	tname = new Array();
	tphoto = new Array();	

	$.ajax({
		url: "incomeTrainerChart.do",
		type: "post",
		async: false,
		data: { "year": year, "month": month },
		success: function(data) {
			data = $.parseJSON(data);
			for (var i = 0; i < data.length; i++) {
				tid.push(data[i].tid);
				income.push(data[i].income);
				tname.push(data[i].tname);
				tphoto.push(data[i].tphoto);
			}
		},
		error: function(e) {
			alert(e);
		}

	})

	//차트 다시 그리기
	myChart2.destroy();
	charData2 = {
		labels: tname,
		datasets: [
			{
				label: "월 수익(만원)",
				yAxisID: "A",
				data: income,
				backgroundColor: "rgba(75, 192, 192, 0.8)",
			},
		],
	};

	myChart2 = new Chart(ctx2, {
		type: "bar",
		data: charData2,
		// 옵션
		options: {
			scales: {
				yAxes: [
					{
						id: "A",
						type: "linear",
						position: "left",
						ticks: {
							min: 0,
	          				suggestedmax: 2000,
						},
					},
				],
			},
		},
	});

	//////////////////차트 다시그리기
	
	// 이달의 트레이너 처음 뿌릴 때	
	monthtrainer = new Array();
	monthtrainerphoto = new Array();
	var max = Math.max.apply(null, income);
	for (var i = 0; i < income.length; i++) {
		if (income[i] == max) {
			monthtrainer.push(tname[i]);
			monthtrainerphoto.push(tphoto[i]);
		}
	}
	var tmonth = document.querySelector("#tmonth");	
	var data = new String();
	if(monthtrainer.length>1){
		for (var i = 0; i < monthtrainer.length; i++) {			
	
		data += "<div class='card'><div class='card-body'><div id='tmonthname"+(i+1)+"'></div></div></div>";			
		
		}
		tmonth.innerHTML = data;
		
		for(var i=0;i<monthtrainer.length;i++){
			var tmonthname = document.querySelector("#tmonthname"+(i+1));			
			var namedata = "<h5 class='card-title'>" + monthtrainer[i] + " 트레이너 </h5>";	
			tmonthname.innerHTML = namedata;
		}
	}else {
		data += "<div class='card'><div id='tmonthphoto'></div><div class='card-body'><div id='tmonthname'></div></div></div>";
		tmonth.innerHTML = data;
		
		var tmonthname = document.querySelector("#tmonthname");
		var tmonthphoto = document.querySelector("#tmonthphoto");
		var namedata = "<h5 class='card-title'>" + monthtrainer[0] + " 트레이너 </h5>";
		var photodata = "<img src='img/TrainerPhoto/"+monthtrainerphoto[0]+"' class='card-img-top' style='height:20rem'/>";
				
		tmonthphoto.innerHTML = photodata;
		tmonthname.innerHTML = namedata;
		
	}
	
//// 이달의 트레이너 처음 뿌릴 때	
//	var monthtrainer = new Array();
//	var monthtrainerphoto = new Array();
//	var max = Math.max.apply(null, income);
//	for (var i = 0; i < income.length; i++) {
//		if (income[i] == max) {
//			monthtrainer.push(tname[i]);
//			monthtrainerphoto.push(tphoto[i]);
//		}
//	}
//	var tmonth = document.querySelector("#tmonth");	
//	var data = new String();	
//	for (var i = 0; i < monthtrainer.length; i++) {			
//	
//		data += "<div class='card'><div id='tmonthphoto"+(i+1)+"'></div><div class='card-body'><div id='tmonthname"+(i+1)+"'></div></div></div>";		
//		
//	}
//	tmonth.innerHTML = data
//	
//	for(var i=0;i<monthtrainer.length;i++){
//		var tmonthname = document.querySelector("#tmonthname"+(i+1));
//		var tmonthphoto = document.querySelector("#tmonthphoto"+(i+1));
//		var namedata = "<h5 class='card-title'>" + monthtrainer[i] + " 트레이너 </h5>";
//		var photodata = "<img src='img/TrainerPhoto/"+monthtrainerphoto[i]+"' class='card-img-top' />";
//		
//		tmonthphoto.innerHTML = photodata;
//		tmonthname.innerHTML = namedata;
//	}
	

/////////////////이달의 트레이너
	

/////////////////이달의 트레이너




	$("#tincome").text(month + "월의 트레이너 수익");
	$("#tmonthhead").text(month + "월의 트레이너");
});
//////////////////////////////////////////////
// CHART2 END
