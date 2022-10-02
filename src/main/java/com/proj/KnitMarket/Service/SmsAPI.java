package com.proj.KnitMarket.Service;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;


@Service
public class SmsAPI {

    public void sendSms(String hp, int orderNo, String name, int totalPrice) throws CoolsmsException {

        String api_key = "NCS7YOD372XJHYT6";
        String api_secret = "9VQKF869G8FVNFIYLPRBFB1GOH2NTZ2I";
        Message coolsms = new Message(api_key, api_secret);
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("to", hp);
        params.put("from", "01049012186");
        params.put("type", "SMS");
        params.put("text", "[뜨개장터] " + " 주문번호: " + orderNo + ", " + name + "님, " + totalPrice + "원 결제완료되었습니다. ~일 내에 발송됩니다");
        params.put("app_version", "test app 1.2");

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }


}
