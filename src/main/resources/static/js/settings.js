$(function () {

    $('#dataTable').DataTable();

    $('#save').click(function () {
        $('#modalEmployee').modal('hide');
        save();
    });

    $('.btn-outline-secondary').click(function () {
        var employeeRow = $(this).parent().parent();
        edit(employeeRow);
    })

})

function save() {
    var employee = {
        name: $('#name').val(),
        hourRate: $('#hourRate').val(),
        position: $('#position').val(),
        id: $('#id').val()
    };

    $.ajax({
        url: '/saveEmployee',
        type: 'post',
        data: JSON.stringify(employee),
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

function edit(employeeRow) {
    $('#name').val($(employeeRow).find('td[name=name]').text());
    $('#hourRate').val($(employeeRow).find('td[name=hourRate]').text());
    $('#position').val($(employeeRow).find('td[name=position]').text());
    $('#id').val($(employeeRow).find('td[name=id]').text());
    $('#modalEmployee').modal('show');
}

function clearModal() {
    $('.modal :input').val('');
}