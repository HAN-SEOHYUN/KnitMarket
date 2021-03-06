package com.knitmarket.api.controller;

import com.knitmarket.api.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.image.ImageProducer;
import java.sql.ClientInfoStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PostController {



    @PostMapping("/posts")
    public Map<String,String> post(@RequestBody @Valid PostCreate params, BindingResult result){

        if(result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField(); //title
            String errorMessage = firstFieldError.getDefaultMessage(); //..에러메세지

            Map<String,String> error = new HashMap<>();
            error.put(fieldName,errorMessage);
            return error;

        }
        return Map.of();
    }

}
