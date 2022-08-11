$(function (){

    $('#save_Btn').click(function(){
        var data = {
            "itemName":$('#itemName').val(),
            "itemDesc": $('#itemDesc').val(),
            "price": $('#price').val()
        };

        alert("제이쿼리정상작동");
        $.ajax({
            type: 'POST',
            url: '/knitmarket/register',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                alert("등록성공");
            }
        });

    });
});

