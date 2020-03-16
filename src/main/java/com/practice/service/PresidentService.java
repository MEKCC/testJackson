package com.practice.service;

import com.practice.entity.President;
import com.practice.repo.CountryRepo;
import com.practice.repo.PresidentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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


    public List<President> addPresidents() {
        String contents = null;
        List<President> presidentList = null;
        try {
            contents = jsonFileReaderService.fromJsonToString(jsonFile);
            presidentList = fromStringToClassConverterService.mapper(contents);
            presidentList = this.setPresidentForEachCountry(presidentList);
            presidentRepo.saveAll(presidentList);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "File not found, can't add Presidents to table", e);
        }
        return presidentList;
    }

    public void deletePresidents(Long id) {
        if (presidentRepo.findById(id).isPresent()) {
            presidentRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Can't delete, president not found or incorrect data");
        }
    }

    public void updatePresidents(Long id) {
        if (presidentRepo.findById(id).isPresent()) {
            President president = presidentRepo.findById(id).get();
            president.setName("UPDATE NAME");
            president.setAge("UPDATE AGE");
            presidentRepo.save(president);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "President doesn't exist");
        }
    }

    public List<President> setPresidentForEachCountry(List<President> presidentList) {
        List<President> changedList = List.copyOf(presidentList);
        changedList.forEach(president -> president.getCountry().forEach(country -> country.setPresident(president)));
        return changedList;
    }
}
