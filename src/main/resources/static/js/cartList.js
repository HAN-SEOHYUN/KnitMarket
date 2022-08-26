$(function () {
    //X버튼 mouseover
    $(document).on('mouseover', '#xWrap', function () {
        $(this).css('background','#f3d2d2');

    });
    $(document).on('mouseout', '#xWrap', function () {
        $(this).css('background','');
    });

    //삭제하기 버튼 클릭 시
    $(document).on('click', '#whiteDelBtn', function () {
        var delCheckedList=[];
        var result;
        $('input[name="delCheckbox"]:checked').each(function(){
            delCheckedList.push($(this).val());
        });
        if(delCheckedList.length<1){
            alert("선택된 상품이 없습니다");
        }else{
           result = confirm("선택한 상품을 삭제하시겠습니까 ?");
        }

        if(result){
            location.href = "/knitmarket/cartRemoveList/"+delCheckedList;
        }else{

        }
    });
});