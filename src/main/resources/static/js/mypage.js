function execZipcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            var addr = "";

            if (data.userSelectedType === "R") {
                // 도로명 주소
                addr = data.roadAddress;
            } else {
                // 지번주소
                addr = data.jibunAddress;
            }

            document.getElementById("zipcode").value = data.zonecode;
            document.getElementById("address").value = addr;
            document.getElementById("addressDetail").focus();
        },
    }).open();
}

function checkFields(){
    console.log("checkFields 실행");
    var store = document.getElementById('store').value;
    var accountBank = document.getElementById('accountBank').value;
    var accountNum = document.getElementById('accountNum').value;
    var accountName = document.getElementById('accountName').value;

    if(store === '' || accountBank==='' || accountNum==='' || accountName===''){
        alert("모두 입력되지않았습니다. 다시 확인해주세요");
        event.preventDefault();
        return false;
    }else{
        if(store.length>8){
            alert("상점명은 최대 8글자 입력 가능합니다");
            event.preventDefault();
            return false;
        }

    }
}