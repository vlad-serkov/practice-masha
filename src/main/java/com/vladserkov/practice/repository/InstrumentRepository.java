package com.vladserkov.practice.repository;

import com.vladserkov.practice.domain.Instrument;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InstrumentRepository {


    private final String PATH_TO_FILE = "src/main/resources/data.csv";

    public void save(Instrument Instrument){
        File csvOutputFile = new File(PATH_TO_FILE);
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvOutputFile, true))) {
            pw.println(convertToCSV(Instrument));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Instrument> findAll() {

        Reader in;
        try {
            in = new FileReader(PATH_TO_FILE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Instrument> list = new ArrayList<>();
        Iterable<CSVRecord> records;
        try {
            records = CSVFormat.DEFAULT.parse(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (CSVRecord record : records) {
            list.add(new Instrument(
                    Integer.parseInt(record.get(0)),
                    record.get(1),
                    record.get(2),
                    Integer.parseInt(record.get(3)),
                    Integer.parseInt(record.get(4)),
                    Integer.parseInt(record.get(5))
            ));
        }
        return list;
    }

    public void deleteById(int serial)  {
        final List<Instrument> listAfter = findAll()
                .stream()
                .filter(Instrument -> Instrument.getId() != serial)
                .collect(Collectors.toList());
        saveAllByOverwritingFile(listAfter);
    }

    public void update(Instrument Instrument){
        final List<Instrument> Instruments = findAll();
        for (int i = 0; i<Instruments.size(); i++) {
            if (Instruments.get(i).getId() == Instrument.getId()) {
                Instruments.set(i, Instrument);
                break;
            }
        }
        saveAllByOverwritingFile(Instruments);
    }

    public Instrument getById(int serial) {
        final List<Instrument> all = findAll();
        for (Instrument Instrument: all
        ) {
            if (Instrument.getId() == serial) return Instrument;
        }
        throw new IllegalStateException("НЕВОЗМОЖНО");
    }

    public boolean selectExistInstrumentById(int id){
        final List<Instrument> all = findAll();
        for (Instrument Instrument: all
        ) {
            if (Instrument.getId() == id) return true;
        }
        return false;
    }

    private void saveAllByOverwritingFile(List<Instrument> list) {
        File csvOutputFile = new File(PATH_TO_FILE);
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvOutputFile, false))) {
            for (Instrument Instrument : list
            ) {
                pw.println(convertToCSV(Instrument));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String convertToCSV(Instrument Instrument) {

        String[] data = new String[]{
                String.valueOf(Instrument.getId()),
                String.valueOf(Instrument.getName()),
                String.valueOf(Instrument.getUnit()),
                String.valueOf(Instrument.getScale()),
                String.valueOf(Instrument.getSensitivity()),
                String.valueOf(Instrument.getMax_measure())
        };

        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }
    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
