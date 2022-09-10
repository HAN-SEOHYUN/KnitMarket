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
            location.href = "/cart/cartRemoveList/"+delCheckedList;
        }else{

        }
    });

    //장바구니 주문하기 버튼 클릭 시
    $(document).on('click', '#blackOrderBtn', function () {
       var count = document.getElementsByClassName('card-body').length;
       if(count<1){
           alert("장바구니가 비어있습니다");
       }else{
           if(confirm("장바구니 상품을 주문하시겠습니까 ?")){
               location.href="/order/cartItems";
           }
       }
    });
});