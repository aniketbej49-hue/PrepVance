package com.interviewportal.smartportal.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    private final Client client;

    public GeminiService(@Value("${gemini.api.key}") String apiKey) {

        this.client = Client.builder()
                .apiKey(apiKey)
                .build();
    }

    public String testGemini() {

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3.8-flash",
                        "Say hello to my SmartPortal application in one sentence.",
                        null
                );

        return response.text();
    }
}