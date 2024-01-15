$("#grafico_numero_dias").highcharts({
    chart: { type: "line" },
    title: { text: "Número de dias até ao alcance de 100M de utilizadores" },
    xAxis: { categories: [0, 500, 1000, 1500, 2000, 2500, 3000, 3500],
            title:  {text: "Número de dias"},        
    },
    yAxis: {title: {text: "Número de utilizadores"}},
    series: [
        {
            name: "ChatGPT",
            data: [0, 100000000] 
        },
        {
            name: "Instagram",
            data: [1000000, 70000000, 100000000] 
        },
        {
            name: "Spotify",
            data: [600000, 10000000, 50000000, 90000000, 100000000] 
        },
        {
            name: "Facebook",
            data: [500000, 8000000, 35000000, 70000000, 100000000] 
        },
        {
            name: "Twitter",
            data: [0, 100000, 4000000, 12000000, 50000000, 100000000] 
        },
        {
            name: "Netflix",
            data: [0, 2000, 150000, 1500000, 5500000, 35000000, 60000000, 100000000] 
        }
    ]
    });

$("#grafico_empregabilidade").highcharts({
    chart: { type: "column" },
    title: { text: "Empregabilidade de Doutoramentos em IA na América do Norte" },
    subtitle: {text: "Fonte: https://hai.stanford.edu/news/state-ai-9-charts"},
    xAxis: { categories: [2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017,2018,2019,2020],
            title:  {text: "Ano"},        
    },
    yAxis: {title: {text: "Número de contratações"}},
    series: [
        {
            name: "Governo",
            data: [76, 64, 101, 74, 85, 77, 134, 116, 162, 180, 153] 
        },
        {
            name: "Indústria",
            data: [148, 127, 148, 125, 128, 119, 197, 176, 235, 245, 214] 
        },
    ]
    });
    