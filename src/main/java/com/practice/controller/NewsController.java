package com.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.entity.News;
import com.practice.repo.NewsRepo;
import com.practice.service.FromStringToNewsConverterService;
import com.practice.service.JsonFileReaderService;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
public class NewsController {

    @Value("${newsFromAPI}")
    private String newsFile;

    private final ObjectMapper mapper;
    private JsonFileReaderService fileReaderService;
    private FromStringToNewsConverterService fromStringToNewsConverterService;
    private NewsRepo newsRepo;

    @Autowired
    public NewsController(ObjectMapper mapper, JsonFileReaderService fileReaderService, FromStringToNewsConverterService fromStringToNewsConverterService, NewsRepo newsRepo) {
        this.mapper = mapper;
        this.fileReaderService = fileReaderService;
        this.fromStringToNewsConverterService = fromStringToNewsConverterService;
        this.newsRepo = newsRepo;
    }

    @GetMapping("/showNews")
    public void parseNews() throws IOException {
        String news = fileReaderService.fromJsonToString(newsFile);
        JSONObject jsonObject = new JSONObject(news);
        JSONArray jsonArray = jsonObject.getJSONArray("posts");
        List<News> newsList = fromStringToNewsConverterService.mapper(jsonArray.toString());

        for (int i = 0; i < newsList.size(); i++) {
            newsList.get(i).setJson(jsonArray.getJSONObject(i).toString());
        }

//        for (int i = 0; i < newsList.size(); i++) {
//            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//            JSONObject jsonObject2 = jsonObject1.getJSONObject("entities");
//            JSONArray jsonArray1 = jsonObject2.getJSONArray("persons");
//            Map<String, String> map = new HashMap<>();
//            for (int j = 0; j < jsonArray1.length() ; j++) {
//                map.put(jsonArray1.getJSONObject(j).getString("name"), jsonArray1.getJSONObject(j).getString("sentiment"));
//                map.put(jsonArray1.getJSONObject(j).getString("organization"), jsonArray1.getJSONObject(j).getString("location"));
//
//            }
//            map.forEach((key, value) -> System.out.println(key + ":" + value));
//
//        }

        newsRepo.saveAll(newsList);
    }
}
