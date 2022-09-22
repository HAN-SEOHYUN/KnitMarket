function checkFields(){
    console.log("checkFields 실행");
    var itemName = document.getElementById('itemName').value;
    var price = document.getElementById('price').value;
    var itemDesc = document.getElementById('itemDesc').value;

    console.log(itemName);
    console.log(price);
    console.log(itemDesc);

    if(itemName == '' || itemDesc=='' || price==0 || price.isNaN('abc')){
        alert("상품정보를 올바르게 입력해주세요");
        event.preventDefault();
        return false;
    }

    if(document.getElementById("formFile").files.length == 0){
        alert("상품사진을 첨부해주세요");
        event.preventDefault();
        return false;
    }
}

$(function () {

    $('#save_Btn').on('click', uploadImage);
    console.log("ajax 실행");

    function uploadImage() {
        var file = $('#formFile')[0].files[0];
        var formData = new FormData();
        let url = "/item/register";
        formData.append('data', file);

        $.ajax({
            type: 'POST',
            url: '/item/uploadImg',
            data: formData,
            processData: false,
            contentType: false
        }).success(function (data) {
            location.replace(url);
        }).fail(function (error) {
            alert("상품등록에 실패했습니다. 다시 시도해주세요");
            location.replace("/");
        })
    }

    $('#update_Btn').on('click', uploadImage_update);

    function uploadImage_update() {
        var file = $('#formFile')[0].files[0];
        var formData = new FormData();
        var itemId = $('#itemId').val();

        let url = '/item/register/'+itemId;

        formData.append('data', file);

        $.ajax({
            type: 'POST',
            url: '/item/uploadImg',
            data: formData,
            processData: false,
            contentType: false
        }).success(function (data) {
            location.replace(url);
        }).fail(function (error) {
            alert("상품등록에 실패했습니다. 다시 시도해주세요");
            location.replace("/");
        })
    }

});

