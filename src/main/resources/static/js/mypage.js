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