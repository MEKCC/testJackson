package com.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.entity.President;
import com.practice.repo.CountryRepo;
import com.practice.repo.PresidentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
public class PresidentController {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PresidentRepo presidentRepo;

    @Autowired
    private CountryRepo countryRepo;

    @GetMapping("/add")
    public void saveToDB() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("file5.json")));
        List<President> presidentList = Arrays.asList(mapper.readValue(contents, President[].class));

        presidentRepo.saveAll(presidentList);

    }
}
