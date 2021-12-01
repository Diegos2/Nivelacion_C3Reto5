google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(grafica);

function cargaData(){
    var data = google.visualization.arrayToDataTable([
        ['Año', '% Población'],
        ['2006',  45.9],
        ['2010',  51.2],
        ['2015',  56.4]
      ]);
      return data;
}

function grafica(){
    let data = cargaData();
    var chart = new google.visualization.LineChart(document.getElementById('grafico'));
    var options = {title:'Evolución sobre peso en población colombiana', curveType: 'function',
    legend: { position: 'bottom'}};
    chart.draw(data, options);
}
