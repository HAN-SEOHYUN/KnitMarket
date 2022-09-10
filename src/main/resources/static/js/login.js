$(function () {
    $('#kakaoLoginBtn').click(function(){
        var role = "";
        if ($('#checkbox_user').is(":checked")) {
            role = "user";
        } else {
            role = "seller";
        }
        var kakaoUrl = "https://kauth.kakao.com/oauth/authorize?" +
            "client_id=0a9af639b0425e40c10ad4d291ce4637" +
            "&redirect_uri=http://localhost:8086/kakaoLogin/requestToken_" + role +
        "&response_type=code";
        location.href = kakaoUrl;
    });//한개만 선택되게하기


});

function checkOnlyOne(element) {

    const checkboxes
        = document.getElementsByName("checkbox_role");

    checkboxes.forEach((cb) => {
        cb.checked = false;
    })

    element.checked = true;
}