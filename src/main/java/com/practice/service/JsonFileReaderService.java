package com.practice.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class JsonFileReaderService {

    public String fromJsonToString(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
