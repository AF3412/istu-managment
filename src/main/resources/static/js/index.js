$(function () {
    getDataValues();
});

function drawGraph(dataValues) {

    for (var i = 0, len = dataValues.length, value; i < len; i++) {
        value = dataValues[i].values[0];
    }

    $("#gantt").gantt({
        source: dataValues,
        navigate: "scroll",
        scale: "days",
        minScale: "days",
        maxScale: "months",
        itemsPerPage: 10,
        scrollToToday: false,
        useCookie: false
    });
}

function getDataValues() {
    /*var demoSource = [
        {
            name: "Проект",
            desc: "Анализ",
            values: [{
                from: 1320192000000,
                to: 1322401600000,
                label: "Requirement Gathering",
                customClass: "ganttRed"
            }]
        }, {
            desc: "Сбор информации",
            values: [{
                from: 1322611200000,
                to: 1323302400000,
                label: "Scoping",
                customClass: "ganttRed"
            }]
        }, {
            desc: "Создание прототипа",
            values: [{
                from: 1322611200000,
                to: 1323302400000,
                label: "Scoping",
                customClass: "ganttRed"
            }]
        } ];*/

    $.ajax({
        url: '/getTasks',
        type: 'post',
        contentType: "application/json",
        success: function (data) {
            console.log(JSON.parse(data));
            drawGraph(JSON.parse(data));
        }
    });

    $.ajax({
        url: '/getCost',
        type: 'post',
        contentType: "application/json",
        success: function (data) {
            var cost = JSON.parse(data);
            console.log(cost);
            for (var i = 0; i < cost.length; i++) {
                console.log(cost[i].name + ' ' + cost[i].cost);
                $('#cost').append('<p class="m-1">' + cost[i].name + ': ' + cost[i].cost + '</p>');
            }

        }
    });

    // return demoSource;
}