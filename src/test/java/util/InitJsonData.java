package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.entity.President;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InitJsonData {

    public static final String ALL_OBJECTS = "src/test/resources/dataFromFile/testJsonFile.json";

    public static String getDataFromJson() {
        return getDataFromJson(ALL_OBJECTS);
    }

    public static List<President> getListOfPresidentFromString(){
        try {
            return mapper(getDataFromJson());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static String getDataFromJson(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<President> mapper(String values) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(values, President[].class));
    }
}
