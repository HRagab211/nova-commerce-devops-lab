package com.hossam.novacommerce.devops;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/devops")
public class LabCheckController {
    private final String applicationName;

    public LabCheckController(@Value("${spring.application.name}") String applicationName) {
        this.applicationName = applicationName;
    }

    @GetMapping("/lab-check")
    Map<String, Object> labCheck() {
        return Map.of(
                "application", applicationName,
                "status", "running",
                "timestamp", Instant.now().toString(),
                "nextChallenge", "Connect this endpoint through Nginx, add metrics scraping, and run failure drills."
        );
    }
}
