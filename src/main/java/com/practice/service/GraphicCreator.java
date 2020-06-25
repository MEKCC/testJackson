package com.practice.service;

import com.opencsv.CSVReader;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.opencsv.ICSVParser.DEFAULT_QUOTE_CHARACTER;
import static com.opencsv.ICSVParser.DEFAULT_SEPARATOR;

@Service
@Log4j2
public class GraphicCreator {

    private static final String FILE_FOR_GRAPHIC = "C:\\Users\\maksp\\IdeaProjects\\selfEducation\\testJackson\\A_SMOreg.csv";
    private static final int TITLE_LINE = 1;


    public List<Double> createGraphic() {
        List<Double> stocks = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(FILE_FOR_GRAPHIC),
                DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER, TITLE_LINE)) {

            readPricesFromFile(stocks, reader);
        } catch (IOException e) {
            log.warn("Error while reading prices from file", e);
        }
        return stocks;
    }

    private void readPricesFromFile(List<Double> stocks, CSVReader reader) throws IOException {
        String[] line;
        while ((line = reader.readNext()) != null) {
            stocks.add(Double.parseDouble(line[0]));
        }
    }
}
