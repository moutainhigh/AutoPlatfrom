function getLineBasicChart(id, url, jsonData,time,text) {
	$.get(url, function (date) {
		jsonData.series = date;
		jsonData.xAxis = date;
		if(time == 'default'){
			jsonData.title.text = text;
		}else if(time == 'year'){
			jsonData.title.text = text;
		}else if(time == 'quarter'){
			jsonData.title.text = text;
		}else if(time == 'month'){
			jsonData.title.text = text ;
		}else if(time == 'week'){
			jsonData.title.text = text;
		}else if(time == 'day'){
			jsonData.title.text = text;
		}

		Highcharts.chart(id, jsonData);
	}, "json");
}

function getColumnarChart(id, url, jsonData,time,text) {
	getLineBasicChart(id, url, jsonData,time,text);
}

function getPieChart(id, url, jsonData) {
	$.get(url, function (data) {
		jsonData.series[0].data = data.pies;
		Highcharts.chart(id, jsonData);
	}, "json");
}

