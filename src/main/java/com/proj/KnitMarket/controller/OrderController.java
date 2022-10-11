package com.proj.KnitMarket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.KnitMarket.Service.CartService;
import com.proj.KnitMarket.Service.OrderService;
import com.proj.KnitMarket.Service.UserService;
import com.proj.KnitMarket.domain.Order.Order;
import com.proj.KnitMarket.domain.Order.OrderRepository;
import com.proj.KnitMarket.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/order")
public class OrderController {

    @Value("tosspayments.ck")
    private String tossPayments_ck;

    @Value("tosspayments.sk")
    private String tossPayments_sk;

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

    //주소정보 없으면 되돌아가기


    //단일상품주문
    @GetMapping("/{itemId}")
    public String order_item_get(@PathVariable("itemId") Long itemId, HttpSession httpSession, Model model) {
        String email = (String) httpSession.getAttribute("email");
        UserResponseDto userResponseDto = userService.findByEmail(email);
        AddressDto addressDto = userService.getAddress(userResponseDto.getId());

        if (addressDto.getId() == null) {
            String url, msg;
            url = "/mypage/info";
            msg = "주소등록 후 이용가능한 서비스입니다";
            model.addAttribute("url", url);
            model.addAttribute("msg", msg);
            return "common/message";
        }

        OrderDto orderDto = orderService.order(itemId, email);
        List<OrderItemDto> orderItemDtoList = orderService.entityToDto(orderDto);

        model.addAttribute("orderList", orderItemDtoList);
        model.addAttribute("order", orderDto);
        model.addAttribute("address", addressDto);
        return "user/order";
    }

    //장바구니상품주문
    @GetMapping("/cartItems")
    public String order_items_post(Model model, HttpSession httpSession) {
        Long userId = (Long) httpSession.getAttribute("id");
        AddressDto addressDto = userService.getAddress(userId);

        if (addressDto.getId() == null) {
            String url, msg;
            url = "/mypage/info";
            msg = "주소등록 후 이용가능한 서비스입니다";
            model.addAttribute("url", url);
            model.addAttribute("msg", msg);
            return "common/message";
        }

        OrderDto orderDto = orderService.orders(userId);
        List<OrderItemDto> orderItemDtoList = orderService.entityToDto(orderDto);

        model.addAttribute("orderList", orderItemDtoList);
        model.addAttribute("address", addressDto);
        model.addAttribute("order", orderDto);
        return "user/order";
    }

    //결제

    @RequestMapping("/success")
    public String confirmPayment(
            @RequestParam String paymentKey, @RequestParam String orderId, @RequestParam Long amount,
            Model model) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((tossPayments_sk + ":").getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("orderId", orderId);
        payloadMap.put("amount", String.valueOf(amount));

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(payloadMap), headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(
                "https://api.tosspayments.com/v1/payments/" + paymentKey, request, JsonNode.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) { //결제성공
            String[] temp = orderId.split("-");
            Long savedOrderId = Long.parseLong(temp[0]);
            log.info("savedOrderId={}", savedOrderId);

            //order status 변경 & item sellstatus 변경
            OrderDto orderDto = orderService.changeStatus(savedOrderId);
            log.info("orderDto={}", orderDto.getUser().getId());

            //장바구니 비우기
            cartService.cartRemoveAll(orderDto.getUser().getId());

            AddressDto addressDto = userService.getAddress(orderDto.getUser().getId());
            List<OrderItemDto> orderItemDtoList = orderService.entityToDto(orderDto);

            model.addAttribute("orderList", orderItemDtoList);
            model.addAttribute("address", addressDto);
            model.addAttribute("order", orderDto);
            return "user/success";

        } else {
            log.info("결제실패");
            JsonNode failNode = responseEntity.getBody();

            model.addAttribute("message", failNode.get("message").asText());
            model.addAttribute("code", failNode.get("code").asText());

            return "user/fail";
        }
    }

    //주문내역 상세
    @GetMapping("/orderDetail/{orderId}")
    public String order_detail_get(@PathVariable("orderId") Long orderId, Model model) {
        Order order = orderRepository.findOrderById(orderId);

        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .user(order.getUser())
                .orderItems(order.getOrderItems())
                .orderStatus(order.getOrderStatus())
                .totalPrice(order.getTotalPrice())
                .regTime(order.getRegTime())
                .build();

        AddressDto addressDto = userService.getAddress(orderDto.getUser().getId());
        List<OrderItemDto> orderItemDtoList = orderService.entityToDto(orderDto);

        model.addAttribute("orderList", orderItemDtoList);
        model.addAttribute("address", addressDto);
        model.addAttribute("order", orderDto);

        return "user/success";
    }
}


