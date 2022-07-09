package com.vladserkov.practice.service;

import com.vladserkov.practice.domain.Instrument;
import com.vladserkov.practice.exceptionhandler.exception.SerialNotFoundException;
import com.vladserkov.practice.repository.InstrumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class InstrumentServiceTest {
    InstrumentService InstrumentService;

    @BeforeEach
    void init() {
        InstrumentService = new InstrumentService(new InstrumentRepository());
    }



    @Test
    void save() throws SerialNotFoundException {
        InstrumentService.save(new Instrument(3,"lol", "raw", 7,8,90));
    }

    @Test
    void findAllInstruments() {
        final List<Instrument> Instruments = InstrumentService.findAllInstruments();
        Instruments.forEach(System.out::println);

    }

    @Test
    void deleteById() throws SerialNotFoundException {
        InstrumentService.deleteById(51345);
    }

    @Test
    void update() throws SerialNotFoundException {
        InstrumentService.update(3,new Instrument(3,"lol", "loooooooooooooooooooooooooooooooooool", 7,8,90));
    }

    @Test
    void getInstrumentBySerial() {
        final Instrument Instrument = InstrumentService.getInstrumentBySerial(2);
        System.out.println(Instrument);
    }
}