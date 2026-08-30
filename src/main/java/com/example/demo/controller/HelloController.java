package com.example.demo.controller;

import com.example.demo.domain.Quest;
import com.example.demo.domain.QuestRepository;
import com.example.demo.dto.QuestRequest;
import com.example.demo.dto.QuestResponse;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.QuestService;
import com.example.demo.service.WeatherService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class HelloController {

    private final QuestService questService;
    private final FileStorageService fileStorageService;
    private final WeatherService weatherService;

    public HelloController(QuestService questService, FileStorageService fileStorageService, WeatherService weatherService) {
        this.questService = questService;
        this.fileStorageService = fileStorageService;
        this.weatherService = weatherService;
    }

//    @GetMapping("/api/hello")
//    public String hello() {
//        return "hi";
//    }

//    static class UserData {
//        public String name;
//        public int level;
//
//        public UserData(String name, int level) {
//            this.name = name;
//            this.level = level;
//        }
//    }

//    @GetMapping("/api/user")
//    public UserData getUserInfo() {
//        return new UserData("환희", 3);
//    }


    @PostMapping("/api/quest")
    public String createQuest(@Valid @RequestBody QuestRequest request) {

        questService.createQuest(request.title, request.detail);

        return "등록 성공 : " + request.title;
    }

    @GetMapping("/api/quests")
    public List<Quest> getAllQuests() {
        return questService.getAllQuests();
    }

    @GetMapping("/api/quests/{id}")
    public QuestResponse getSingleQuest(@PathVariable Long id) {
        Quest quest = questService.getQuest(id);
        return new QuestResponse(quest);
    }

    @DeleteMapping("/api/quests/{id}")
    public String deleteQuest(@PathVariable Long id) {
        questService.deleteQuest(id);
        return id + " 삭제 완료";
    }

    @PutMapping("/api/quests/{id}")
    public Quest updateQuest(
            @PathVariable Long id,
            @RequestBody QuestRequest request
    ) {
        return questService.updateQuest(id, request.title, request.detail);
    }

    @PatchMapping("/api/quests/{id}/complete")
    public String completeQuest(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        String storedUrl = fileStorageService.store(file);
        questService.completeQuest(id, storedUrl);
        return id + " 완료 처리";
    }

    @GetMapping("/api/weather")
    public String getWeather() {
        double temperature = weatherService.getSeoulTemperature();
        return "서울 현재 기온 : " + temperature;
    }
}
