$(function (){
    var data = {
        itemName: $('#itemName').val(),
        itemDesc: $('#itemDesc').val(),
        price: $('#price').val()
    };
    $('#saveBtn').click(function(){
        $.ajax({
            type: 'POST',
            url: '/api/v1/register',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('상품이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    });
});