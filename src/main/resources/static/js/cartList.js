$(function () {
    var userId = $('#userId').val();

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

       //이미 품절된 상품이 포함되었을 시 이벤트 중지
       const soldoutlist = new Array();
       var soldout_Item_NUM = 0;

       $("#sold-out-span").each(function (index, item) {
           soldout_Item_NUM++;
       });

       if(soldout_Item_NUM>0){
            alert("품절된 상품이 포함되어있습니다. /n 삭제 후 재시도해주세요");
            event.preventDefault();
            return flase;
       }else{
           if(confirm("장바구니 상품을 주문하시겠습니까 ?")){
               location.href="/order/cartItems";
           }
       }


    });

    //주문하러가기 버튼 클릭 시
    $(document).on('click', '#indexBtn', function () {
        location.href="/";
    });

    //주문목록 버튼 클릭 시
    $(document).on('click', '#Btn-orderList', function () {
        location.href="/mypage/orderList/"+userId;
    });

});