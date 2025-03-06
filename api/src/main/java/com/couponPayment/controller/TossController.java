package com.couponPayment.controller;

import com.couponPayment.config.RandomStringGenerator;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/toss/billing")
public class TossController {
    private static final Logger logger = LoggerFactory.getLogger(TossController.class);

    @Value("${toss.clientKey}")
    private String clientKey;

    @GetMapping("/request")
    public String requestTossUi(Model model){
        String customerKey = RandomStringGenerator.generateRandomString(10);

        model.addAttribute("clientKey", clientKey);
        model.addAttribute("successUrl", "http://localhost:8081/api/v1/toss/billing/success");
        model.addAttribute("failUrl", "http://localhost:8081/api/v1/toss/billing/fail");
        model.addAttribute("customerKey", customerKey);
        return "toss";
    }

    @GetMapping("/success")
    public String success(@RequestParam String customerKey,
                          @RequestParam String authKey){
        //http://localhost:8081/api/v1/toss/billing/success?customerKey=xFl5CrMdIL&authKey=bln_p6XnzaNmdEB
        logger.info("Received customerKey: {}, authKey: {}", customerKey, authKey);
        //service 빌링키 요청
        return "index";
    }
}


