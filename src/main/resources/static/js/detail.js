$('textarea').each(function () {
    this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
}).on('input', function () {
    this.style.height = 'auto';
    this.style.height = (this.scrollHeight) + 'px';
});

/*$(function () {
    $(document).on('click', '#orderInfo-btn', function () {
        var orderId = $('#input-orderId').val();
        console.log("orderId="+orderId);
        var url = '/mypage/orderInfo/'+orderId;

        location.href=url;
    });
});*/