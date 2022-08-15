/*
$(function (){
    $('#save_Btn').click(function(){
        var data = {
            "itemName":$('#itemName').val(),
            "itemDesc": $('#itemDesc').val(),
            "price": $('#price').val()
        };

        $.ajax({
            type: 'POST',
            url: '/knitmarket/register',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),formData,
            success: function (res) {
                //등록된 상품 상세보기로 이동하기

                alert("상품이 등록되었습니다3");
                let url ="/knitmarket/"; //detail?id=${res}
                location.replace(url);
            }
        });

    });
});

*/
