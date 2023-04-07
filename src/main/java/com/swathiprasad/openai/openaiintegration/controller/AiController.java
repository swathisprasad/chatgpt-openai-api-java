package com.swathiprasad.openai.openaiintegration.controller;

import com.swathiprasad.openai.openaiintegration.dto.AiRequestDTO;
import com.swathiprasad.openai.openaiintegration.service.AiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping(value = "/open-ai/")
@RestController
public class AiController {

    private AiService aiService;

    @PostMapping("/generate-image")
    public String generateImage(@RequestBody AiRequestDTO aiRequestDTO) {
        return aiService.generateImage(aiRequestDTO);
    }

    @PostMapping("/generate-text")
    public List<String> generateText(@RequestBody AiRequestDTO aiRequestDTO) {
        return aiService.generateText(aiRequestDTO);
    }
}
