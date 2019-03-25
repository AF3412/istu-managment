$(function () {
    getDataValues();
})

function drawGraph(dataValues) {

    // shifts dates closer to Date.now()
    var offset = new Date().setHours(0, 0, 0, 0) -
        new Date(dataValues[0].values[0].from).setDate(35);
    for (var i = 0, len = dataValues.length, value; i < len; i++) {
        value = dataValues[i].values[0];
        value.from += offset;
        value.to += offset;
    }

    $("#gantt").gantt({
        source: dataValues,
        navigate: "scroll",
        scale: "weeks",
        minScale: "hours",
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

    // return demoSource;
}