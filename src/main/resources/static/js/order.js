$(window).load(function () {
    var tossPayments = TossPayments("test_sk_qLlDJaYngro2Bjq0Y2KVezGdRpXx");
    var button = document.getElementById("payment-button");

    var orderId = $('#orderIdInput').val();

    button.addEventListener("click", function () {
        var method = "카드";// "카드" 혹은

        var paymentData = {
            amount: 19000,
            orderId: orderId,
            orderName: "토스 티셔츠",
            customerName: "이토페",
            successUrl: window.location.origin + "/success",
            failUrl: window.location.origin + "/fail",
        };
        tossPayments.requestPayment(method, paymentData);
    });
});




