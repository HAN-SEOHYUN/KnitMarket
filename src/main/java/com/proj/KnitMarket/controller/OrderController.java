package com.proj.KnitMarket.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.KnitMarket.Service.CartService;
import com.proj.KnitMarket.Service.OrderService;
import com.proj.KnitMarket.Service.UserService;
import com.proj.KnitMarket.domain.Member.User;
import com.proj.KnitMarket.domain.Order.Order;
import com.proj.KnitMarket.domain.Order.OrderItem;
import com.proj.KnitMarket.domain.Order.OrderRepository;
import com.proj.KnitMarket.dto.*;
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
    private final OrderRepository orderRepository;

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

            //order status 변경 & item sellstatus 변경
            OrderDto orderDto = orderService.changeStatus(savedOrderId);

            //장바구니 비우기
            cartService.cartRemoveAll(orderDto.getUser().getId());

            AddressDto addressDto = userService.getAddress(orderDto.getUser().getId());
            List<OrderItemDto> orderItemDtoList = orderService.entityToDto(orderDto);

            model.addAttribute("orderList",orderItemDtoList);
            model.addAttribute("address",addressDto);
            model.addAttribute("order",orderDto);
            return "user/success";
        } else {
            log.info("결제실패");
            JsonNode failNode = responseEntity.getBody();

            model.addAttribute("message", failNode.get("message").asText());
            model.addAttribute("code", failNode.get("code").asText());

            return "fail";
        }
    }

    //사용자 주문목록
    @GetMapping("orderList/{userId}")
    public String orderList_get(@PathVariable("userId")Long userId,Model model){
        List<OrderDto> orderDtoList = userService.selectOrderList(userId);
        model.addAttribute("orderList",orderDtoList);
        return "user/orderList";
    }




    /* orderId 로 주문 상세 */

    //결제실패
    /*@RequestMapping("/order/fail")
    public String failPayment(@RequestParam String message, @RequestParam String code, Model model) {
        model.addAttribute("message", message);
        model.addAttribute("code", code);
        return "redirect:/";
    }*/


    }


