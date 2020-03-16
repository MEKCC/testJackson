package com.practice.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.repo.PresidentRepo;
import com.practice.service.JsonFileReaderService;
import com.practice.service.PresidentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static util.InitJsonData.getDataFromJson;

@SpringBootTest
@ContextConfiguration(classes = {PresidentController.class, ObjectMapper.class, JsonFileReaderService.class})
class PresidentControllerTest {

//    private static String all_data_from_json = "src/test/resources/dataFromFile/testJsonFile.json";

    private static String all_data = getDataFromJson();

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PresidentRepo presidentRepo;

    @Autowired
    private PresidentController presidentController;

    @MockBean
    private PresidentService presidentService;

    @MockBean
    private JsonFileReaderService jsonFileReaderService;

    @BeforeEach
    void setUp() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    void addPresidentsTest() throws IOException {

        when(jsonFileReaderService.fromJsonToString(any())).thenReturn(all_data);
        System.out.println(all_data);


        presidentController.addPresidents();

//        verify(presidentRepo, times(1)).saveAll(anyList());

        verify(presidentService, times(1)).addPresidents();
    }

//    @Test
//    void addPresidentsTest() throws IOException {
//
//        presidentController.addPresidents();
//
////        verify(presidentRepo, times(1)).saveAll(anyList());
//
//        verify(presidentService, times(1)).addPresidents();
//    }
}