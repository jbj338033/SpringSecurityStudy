package com.jbj338033.springsecuritystudy.controller;

import com.jbj338033.springsecuritystudy.dto.JoinDto;
import com.jbj338033.springsecuritystudy.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/join")
public class JoinController {
    private final JoinService service;

    @GetMapping
    public String getJoin() {
        return "join";
    }

    @PostMapping
    public String postJoin(JoinDto dto) {
        service.join(dto);

        return "redirect:/login";
    }
}
