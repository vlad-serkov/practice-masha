package com.vladserkov.practice.repository;

import com.vladserkov.practice.domain.Instrument;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class InstrumentRepositoryTest {

    @Test
    void save() {
        InstrumentRepository InstrumentRepository = new InstrumentRepository();
        InstrumentRepository.save(new Instrument(1,"lol", "raw", 7,8,90));
    }

    @Test
    void findAll() {
        InstrumentRepository InstrumentRepository = new InstrumentRepository();
        final List<Instrument> all = InstrumentRepository.findAll();
        for (Instrument Instrument: all
             ) {
            System.out.println(Instrument);
        }
    }
    @Test
    void deleteById() {
        InstrumentRepository InstrumentRepository = new InstrumentRepository();
         InstrumentRepository.deleteById(1);
    }
    @Test
    void update() {
        InstrumentRepository InstrumentRepository = new InstrumentRepository();

        InstrumentRepository.update(new Instrument(2,"lol", "raw", 7,8,90));
    }
    @Test
    void getById() {
        InstrumentRepository InstrumentRepository = new InstrumentRepository();
        final Instrument Instrument = InstrumentRepository.getById(2);
        System.out.println(Instrument);
    }

    @Test
    void select() {
        InstrumentRepository InstrumentRepository = new InstrumentRepository();
        final boolean b = InstrumentRepository.selectExistInstrumentById(2);
        System.out.println(b);
    }

    @Test
    void convertToCSV() {
    }


    @Test
    void escapeSpecialCharacters() {
    }
}