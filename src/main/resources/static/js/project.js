var dateStart = +moment();
var dateEnd = +moment();
var dateHumanStart = dateStart;
var dateHumanEnd = dateEnd;
var daysCount;

$(function () {

    $('#dataTable').DataTable();
    moment.locale('ru');
    $('#daterange').daterangepicker({
        "locale": {
            "applyLabel": "Выбрать",
            "cancelLabel": "Отмена"
        }
    }, function(start, end) {
        dateStart = start.format('x');
        dateEnd = end.format('x');//X
        dateHumanStart = start.format('DD-MM-YYYY');
        dateHumanEnd = end.format('DD-MM-YYYY');
        daysCount = Math.ceil(moment.duration(end.diff(start)).asDays());
    });

    $('.btn-outline-secondary').click(function () {
        var taskRow = $(this).parent().parent();
        edit(taskRow);
    })

    $('#save').click(function () {
        $('#modalProject').modal('hide');
        var employee = $('#employee option:selected');
        var task = {
            id: $('#id').val(),
            name: $('#name').val(),
            employeeId: employee.val(),
            employeeName: employee.text(),
            dateStart: dateStart,
            dateEnd: dateEnd,
            dateHumanStart: dateHumanStart,
            dateHumanEnd: dateHumanEnd
        }
        save(task);
    });


})

function save(task) {

    $.ajax({
        url: '/saveTask',
        type: 'post',
        data: JSON.stringify(task),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        complete: function (data) {
            clearModal();
            location.reload();
        }
    });
}

function edit(taskRow) {
    dateHumanStart = $(taskRow).find('td[name=dateStart]').text();
    dateHumanEnd = $(taskRow).find('td[name=dateEnd]').text();
    dateStart = $(taskRow).find('td[name=dateEnd]').data('start');
    dateEnd = $(taskRow).find('td[name=dateEnd]').data('end');
    $('#name').val($(taskRow).find('td[name=name]').text());
    $('#employee').val($(taskRow).find('td[name=employeeName]').data('eid'));
    console.log($(taskRow).find('td[name=dateStart]').data('start'));
    console.log($(taskRow).find('td[name=dateEnd]').data('end'));
    $("#daterange").data('daterangepicker').setStartDate(dateHumanStart);
    $("#daterange").data('daterangepicker').setEndDate(dateHumanEnd);
    $("#daterange").data('daterangepicker').updateView();
    $("#daterange").data('daterangepicker').updateCalendars();
    $('#id').val($(taskRow).find('td[name=id]').text());
    $('#modalTask').modal('show');
}

function clearModal() {
    $('.modal :input').val('');
}