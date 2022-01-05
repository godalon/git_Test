Chart.defaults.global.defaultFontFamily = '-apple-system, BlinkMacSystemFont, "Nanum Gothic", Roboto, "Nanum Gothic", Arial, "Nanum Gothic", sans-serif, "Nanum Gothic",  "Nanum Gothic", "Nanum Gothicl", "Nanum Gothic"';


//현재 날짜
let today = new Date();
let year = today.getFullYear();
let month = today.getMonth() + 1;

var months = new Array();
var income = new Array();
var user = new Array();

/*$.getJSON("income.do",
				  {"year":"2020"},
				  function(data){
					  alert(data);					  
					 $.each(data,function(key,val){
						alert(val.month);
						months.push(val.month);
						income.push(val.income);												 
					 }) //each
					 
				  });*/

//월별 매출
$.ajax({
	url: "incomeChart.do",
	type: "post",
	async: false,
	data: { "year": year, "month": month },
	success: function(data) {
		data = $.parseJSON(data);
		for (var i = 0; i < data.length; i++) {
			months.push(data[i].month + "월");
			income.push(data[i].income);
			user.push(data[i].user);
		}
	},
	error: function(e) {
		alert(e);
	}

})


//월별 등록회원수
/*$.ajax({
url:"userCount.do",
type:"post",
async:false,
success:function(data){
	data = $.parseJSON(data);			
	for(var i=0;i<data.length;i++){				
		user.push(data[i].user);
	}			
},
error: function(e){
	alert(e);
}
	
})
*/






// CHART1
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
//////////////////////////////////////////////
var ctx1 = document.getElementById("myChart1");
var charData1 = {
	labels: months,
	datasets: [
		{
			label: "등록회원수",
			yAxisID: "A",
			data: user,
			backgroundColor: "rgba(75, 192, 192, 0.8)",
			
			
		},
		{
			label: "월 매출(만원)",
			yAxisID: "B",
			data: income,
			backgroundColor: "rgba(54, 162, 235, 0.8)",
			
			
		},
	],
};
var myChart1 = new Chart(ctx1, {
	type: "bar",
	data: charData1,
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
	          			suggestedmax: 80,
						
						
					},
					
					
				},
				{
					id: "B",
					type: "linear",
					position: "right",
					ticks: {
						suggestedmin: 0,
	          			suggestedmax: 2000,
						
					},
					gridLines: {
                    display: true,
                    drawOnChartArea: false,
                  }
					
					
				},	
			],
		},
	},
});
//////////////////////////////////////////////
// CHART1 END





$("#selectyear1").change(function() {
	year=$("#selectyear1").val();
	month = today.getMonth() + 1;
	if($("#selectyear1").val()<today.getFullYear()){
		month=12;
	}
	
	
	months = new Array();
	income = new Array();
	user = new Array();
	

	$.ajax({
		url: "incomeChart.do",
		type: "post",
		async: false,
		data: { "year": year, "month": month },
		success: function(data) {
			data = $.parseJSON(data);
			for (var i = 0; i < data.length; i++) {
				months.push(data[i].month + "월");
				income.push(data[i].income);
				user.push(data[i].user);
			}
		},
		error: function(e) {
			alert(e);
		}

	})

	//차트 다시 그리기
	myChart1.destroy();
	charData1 = {
		labels: months,
		datasets: [
			{
				label: "등록회원수",
				yAxisID: "A",
				data: user,
				backgroundColor: "rgba(75, 192, 192, 0.8)",
				min: 0,
	          			suggestedmax: 80,
			},
			{
				label: "월 매출(만원)",
				yAxisID: "B",
				data: income,
				backgroundColor: "rgba(54, 162, 235, 0.8)",
			},
		],
	};

	myChart1 = new Chart(ctx1, {
		type: "bar",
		data: charData1,
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
	          				suggestedmax: 80,
						
						},
					},
					{
						id: "B",
						type: "linear",
						position: "right",
						ticks: {
						suggestedmin: 0,
	          			suggestedmax: 2000,
						
					},
					gridLines: {
                    display: true,
                    drawOnChartArea: false,
                  }
					},
				],
			},
		},
	});

	//////////////////차트 다시그리기


});
