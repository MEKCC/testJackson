package com.practice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.entity.President;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
class FromStringToClassConverterService {

    @Autowired
    private ObjectMapper objectMapper;

    public List<President> mapper(String values) throws JsonProcessingException {
        return Arrays.asList(objectMapper.readValue(values, President[].class));
    }
}
