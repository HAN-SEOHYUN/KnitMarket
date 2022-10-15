$(function () {

    //카카오 로그인 버튼 클릭시
    $('#kakaoLoginBtn').click(function(){
        var role = "";
        if ($('#checkbox_user').is(":checked")) {
            role = "user";
        } else {
            role = "seller";
        }
        var kakaoUrl = "https://kauth.kakao.com/oauth/authorize?" +
            "client_id=0a9af639b0425e40c10ad4d291ce4637" +
            "&redirect_uri=http://knitmarket.shop/kakaoLogin/requestToken_" + role +
        "&response_type=code";
        location.href = kakaoUrl;
    });

    //네이버 로그인 버튼 클릭시
    $('#naverLoginBtn').click(function(){
        var role = "";
        if ($('#checkbox_user').is(":checked")) {
            role = "user";
        } else {
            role = "seller";
        }
        var kakaoUrl = "https://nid.naver.com/oauth2.0/authorize?" +
            "response_type=code" +
            "&client_id=AU7orLrqqxELyBf6vaPd" +
            "&redirect_uri=http://knitmarket.shop/naverLogin/requestToken_" + role +
            "&state=STATE_STRING";
        location.href = kakaoUrl;
    });//한개만 선택되게하기

});

//한개만 선택되게
function checkOnlyOne(element) {

    const checkboxes
        = document.getElementsByName("checkbox_role");

    checkboxes.forEach((cb) => {
        cb.checked = false;
    })

    element.checked = true;
}