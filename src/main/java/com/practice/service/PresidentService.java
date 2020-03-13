package com.practice.service;

import com.practice.entity.President;
import com.practice.repo.CountryRepo;
import com.practice.repo.PresidentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PresidentService {

    @Value("${pathToJsonFile}")
    private String jsonFile;

    @Autowired
    private PresidentRepo presidentRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private JsonFileReaderService jsonFileReaderService;

    @Autowired
    private FromStringToClassConverterService fromStringToClassConverterService;

    public List<President> addPresidents() throws IOException {
        String contents = jsonFileReaderService.fromJsonToString(jsonFile);
        List<President> presidentList = fromStringToClassConverterService.converter(contents);
        presidentList = this.setPresidentForEachCountry(presidentList);
        presidentRepo.saveAll(presidentList);
        return presidentList;
    }

    public List<President> setPresidentForEachCountry(List<President> presidentList) {
        presidentList.forEach(president -> president.getCountry().forEach(country -> country.setPresident(president)));
        return presidentList;
    }
}
