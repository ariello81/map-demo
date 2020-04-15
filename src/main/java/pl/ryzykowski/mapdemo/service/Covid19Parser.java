package pl.ryzykowski.mapdemo.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.ryzykowski.mapdemo.model.Point;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class Covid19Parser {

    private static final String URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    public List<Point> getCovidData(){
        List<Point> points = new ArrayList<Point>();
        RestTemplate restTemplate = new RestTemplate();
        String values = restTemplate.getForObject(URL, String.class);

        StringReader stringReader = new StringReader(values);
        try {
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);
            for (CSVRecord record : parser){
                double lat = Double.parseDouble(record.get("Lat"));
                double lon = Double.parseDouble(record.get("Long"));
                String text = record.get(record.size()-1);
                points.add(new Point(lat, lon, text));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return points;
    }

}
