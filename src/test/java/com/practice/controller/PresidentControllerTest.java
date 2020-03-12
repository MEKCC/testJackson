package com.practice.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.entity.President;
import com.practice.repo.CountryRepo;
import com.practice.repo.PresidentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {PresidentController.class, ObjectMapper.class})
class PresidentControllerTest {

    private static String all_data_from_json = "src/test/resources/dataFromFile/testJsonFile.json";

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PresidentRepo presidentRepo;

    @MockBean
    private CountryRepo countryRepo;

    @Autowired
    private PresidentController presidentController;

    @BeforeEach
    void setUp() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    void saveToDB() throws IOException {
        presidentController.saveToDB();


        verify(presidentRepo, times(1)).saveAll(any(List.class));
        verify(presidentRepo, times(1)).saveAll(anyList());
        verify(presidentRepo, times(1)).saveAll(Mockito.<President>anyList());
        verify(presidentRepo, times(1)).saveAll(anyListOf(President.class));
        verify(presidentRepo, times(1)).saveAll(any());
    }
}