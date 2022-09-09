function checkFields(){
    var itemName = document.getElementById('itemName').value;
    var price = document.getElementById('price').value;
    var itemDesc = document.getElementById('itemDesc').value;

    console.log(itemName);
    console.log(price);
    console.log(itemDesc);

    if(itemName == '' || itemDesc=='' || price==0){
        alert("상품정보를 모두 입력해주세요");
        event.preventDefault();
        return false;
    }

    if(document.getElementById("formFile").files.length == 0){
        alert("상품사진을 첨부해주세요");
        event.preventDefault();
        return false;
    }

}
