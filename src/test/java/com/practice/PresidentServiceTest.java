package com.practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.controller.PresidentController;
import com.practice.entity.President;
import com.practice.repo.CountryRepo;
import com.practice.repo.PresidentRepo;
import com.practice.service.FromStringToClassConverterService;
import com.practice.service.JsonFileReaderService;
import com.practice.service.PresidentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static util.InitJsonData.getDataFromJson;
import static util.InitJsonData.getListOfPresidentFromString;

@SpringBootTest
@ContextConfiguration(classes = {
        PresidentService.class,
        ObjectMapper.class,
        JsonFileReaderService.class,
        PresidentController.class})
class PresidentServiceTest {

    private static String all_data = getDataFromJson();
    private static List<President> presidentList = getListOfPresidentFromString();

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PresidentRepo presidentRepo;

    @MockBean
    private CountryRepo countryRepo;

    @Autowired
    private PresidentController presidentController;

    @Autowired
    private PresidentService presidentService;

    @MockBean
    private JsonFileReaderService jsonFileReaderService;

    @MockBean
    private FromStringToClassConverterService fromStringToClassConverterService;

    @BeforeEach
    void setUp() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    void addPresidentsTestCorrectData() throws IOException {
        when(jsonFileReaderService.fromJsonToString(any())).thenReturn(all_data);
        when(fromStringToClassConverterService.mapper(anyString())).thenReturn(presidentList);
        presidentService.addPresidents();
        verify(presidentRepo, times(1)).saveAll(presidentList);
    }

    @Test
    void addPresidentTestWrongFileCheckException() throws IOException {
        when(jsonFileReaderService.fromJsonToString("fakeJsonFile.json")).thenThrow(IOException.class);
        presidentService.addPresidents();
    }

    @Test
    void addPresidentTestWrongDataFromFileCheckException() throws IOException {
        when(jsonFileReaderService.fromJsonToString(any())).thenReturn(all_data);
        when(fromStringToClassConverterService.mapper("a")).thenThrow(JsonProcessingException.class);
        presidentService.addPresidents();
    }

    @Test
    void deletePresidentsByIdCheck() {
        President president = new President();
        president.setId(1L);
        when(presidentRepo.findById(1L)).thenReturn(Optional.of(president));
        presidentService.deletePresidents(1L);

        verify(presidentRepo, times(1)).findById(anyLong());
        verify(presidentRepo, times(1)).deleteById(anyLong());
    }
}