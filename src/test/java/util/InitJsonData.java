package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InitJsonData {

    public static final String ALL_OBJECTS = "src/test/resources/dataFromFile/testJsonFile.json";

    public static String getDataFromJson() {
        return getDataFromJson(ALL_OBJECTS);
    }

    public static String getDataFromJson(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
