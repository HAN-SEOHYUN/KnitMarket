package com.proj.KnitMarket.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.KnitMarket.Service.CartService;
import com.proj.KnitMarket.Service.OrderService;
import com.proj.KnitMarket.Service.UserService;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.dto.AddressDto;
import com.proj.KnitMarket.dto.OrderDto;
import com.proj.KnitMarket.dto.OrderItemDto;
import com.proj.KnitMarket.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/knitmarket")
public class OrderController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;

    @PostConstruct
    private void init() {
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) {
            }
        });
    }

    //단일상품주문
    @GetMapping("/order/{itemId}")
    public String order_item_get(@PathVariable("itemId")Long itemId, HttpSession httpSession, Model model){
        String email = (String) httpSession.getAttribute("email");
        UserResponseDto userResponseDto = userService.findByEmail(email);
        AddressDto addressDto = userService.getAddress(userResponseDto.getId());
        OrderDto orderDto = orderService.order(itemId, email);
        List<OrderItemDto> orderItemDtoList = orderService.entityToDto(orderDto);

        model.addAttribute("orderList",orderItemDtoList);
        model.addAttribute("order",orderDto);
        model.addAttribute("address",addressDto);
        return "user/order";
    }

    //장바구니상품주문
    @GetMapping("/order/cartItems")
    public String order_items_post(Model model,HttpSession httpSession){
        Long userId = (Long) httpSession.getAttribute("id");
        AddressDto addressDto = userService.getAddress(userId);
        OrderDto orderDto = orderService.orders(userId);
        List<OrderItemDto> orderItemDtoList = orderService.entityToDto(orderDto);

        model.addAttribute("orderList",orderItemDtoList);
        model.addAttribute("address",addressDto);
        model.addAttribute("order",orderDto);
        return "user/order";
    }

    //결제
    //http://localhost:8086/success?orderId=1234567&paymentKey=vG45eDbZnodP9BRQmyarYBdE4boBL8J07KzLNkE6AOMwXYWl&amount=19000
    private final String SECRET_KEY = "test_sk_qLlDJaYngro2Bjq0Y2KVezGdRpXx";
    @RequestMapping("/order/success")
    public String confirmPayment(
            @RequestParam String paymentKey, @RequestParam String orderId, @RequestParam Long amount,
            Model model) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((SECRET_KEY + ":").getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("orderId", orderId);
        payloadMap.put("amount", String.valueOf(amount));

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(payloadMap), headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(
                "https://api.tosspayments.com/v1/payments/" + paymentKey, request, JsonNode.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) { //결제성공

            String [] temp = orderId.split("-");
            Long savedOrderId = Long.parseLong(temp[0]);

            //order status 변경
            //item sellstatus 변경
            OrderDto orderDto = orderService.changeStatus(savedOrderId);

            //장바구니 비우기
            cartService.cartRemoveAll(orderDto.getUser().getId());

            return "success";
        } else {
            log.info("결제실패");
            JsonNode failNode = responseEntity.getBody();

            model.addAttribute("message", failNode.get("message").asText());
            model.addAttribute("code", failNode.get("code").asText());

            return "fail";
        }
    }

    @RequestMapping("/order/fail")
    public String failPayment(@RequestParam String message, @RequestParam String code, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("code", code);
        return "redirect:/";
    }


}
