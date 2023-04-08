package com.swathiprasad.openai.openaiintegration.service;

import com.swathiprasad.openai.openaiintegration.dto.AiRequestDTO;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AiService {

    public String generateImage(final AiRequestDTO aiRequestDTO) {
        try {
            String token = System.getenv("OPENAI_TOKEN");
            OpenAiService openAiService = new OpenAiService(token);
            CreateImageRequest imageRequest = CreateImageRequest.builder()
                    .prompt(aiRequestDTO.text())
                    .build();

            return openAiService.createImage(imageRequest).getData().get(0).getUrl();
        } catch(Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public List<String> generateText(final AiRequestDTO aiRequestDTO) {
        try {
            String token = System.getenv("OPENAI_TOKEN");
            OpenAiService openAiService = new OpenAiService(token);
            CompletionRequest completionRequest = CompletionRequest.builder()
                    .model("text-davinci-003")
                    .prompt(aiRequestDTO.text())
                    .build();

            return openAiService
                    .createCompletion(completionRequest)
                    .getChoices()
                    .stream()
                    .map(CompletionChoice::getText)
                    .collect(toList());
        } catch(Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
