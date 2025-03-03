package com.couponPayment.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TossControllerTest {
    @Autowired
    private MockMvc mockMvc; // MockMvc를 사용하여 실제 HTTP 요청을 보냅니다.

    @Test
    void requestTossUi() throws Exception {
        // mockMvc를 사용하여 GET 요청을 보냄
        mockMvc.perform(get("/api/v1/toss/billing/request"))
                .andExpect(status().isOk())  // HTTP 상태 코드 200 확인
                .andExpect(view().name("toss"))  // "toss" 뷰가 반환되는지 확인
                .andExpect(model().attributeExists("clientKey"))  // clientKey 모델 속성이 존재하는지 확인
                .andExpect(model().attributeExists("successUrl"))  // successUrl 모델 속성이 존재하는지 확인
                .andExpect(model().attributeExists("failUrl"))  // failUrl 모델 속성이 존재하는지 확인
                .andExpect(model().attributeExists("customerKey"));  // customerKey 모델 속성이 존재하는지 확인
    }


    @Test
    void success() throws Exception {
        String customerKey = "xFl5CrMdIL";
        String authKey = "bln_p6XnzaNmdEB";

        mockMvc.perform(get("/api/v1/toss/billing/success")
                        .param("customerKey", customerKey)
                        .param("authKey", authKey))
                .andExpect(status().isOk()) // Check if status is OK
                .andExpect(view().name("index")) // Check if the view name is "index"
                .andReturn();

    }

}