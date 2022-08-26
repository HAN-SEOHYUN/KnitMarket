$(function () {

    //del 버튼 눌렀을 때



    //del 버튼 mouseover
    $(document).on('mouseover', '#xWrap', function () {
        $(this).css('background','#f3d2d2');

    });
    $(document).on('mouseout', '#xWrap', function () {
        $(this).css('background','');
    });

});