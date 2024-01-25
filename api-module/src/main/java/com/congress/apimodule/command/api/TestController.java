package com.congress.apimodule.command.api;

import com.congress.commonmodule.common.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/test")
public class TestController {

    @GetMapping
    public ApplicationResponse<String> testMethod() {

        String text = "sample text for test";
        return ApplicationResponse.ok(text, "샘플 텍스트 문장입니다.");
    }

}
