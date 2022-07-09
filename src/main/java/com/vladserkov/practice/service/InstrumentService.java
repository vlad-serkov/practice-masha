package com.vladserkov.practice.service;

import com.vladserkov.practice.domain.Instrument;
import com.vladserkov.practice.exceptionhandler.exception.SerialNotFoundException;
import com.vladserkov.practice.repository.InstrumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InstrumentService {
    private InstrumentRepository InstrumentRepository;



    public String save(Instrument Instrument) throws SerialNotFoundException {
        if (InstrumentRepository.selectExistInstrumentById(Instrument.getId())) {
            throw new SerialNotFoundException(String.format("Instrument with serial: %s is already exist", Instrument.getId()));
        }
        InstrumentRepository.save(Instrument);
        return String.format("Instrument with serial: %s created", Instrument.getId());
    }

    public List<Instrument> findAllInstruments() {
        return InstrumentRepository.findAll();
    }

    public String deleteById(int serial) throws SerialNotFoundException {
        if (!InstrumentRepository.selectExistInstrumentById(serial)) {
            throw new SerialNotFoundException(String.format("Instrument with serial: %s doesn't exist", serial));
        }
        InstrumentRepository.deleteById(serial);
        return String.format("Instrument with serial: %s deleted", serial);
    }

    public String update(int serial,Instrument Instrument) throws SerialNotFoundException {
        if (!InstrumentRepository.selectExistInstrumentById(serial)) {
            throw new SerialNotFoundException(String.format("Instrument with serial: %s doesn't exist", serial));
        }
        if (Instrument.getId()!= serial) {
            throw new SerialNotFoundException(String.format("You can't change serial: %s", Instrument.getId()));
        }
        InstrumentRepository.update(Instrument);
        return String.format("Instrument with serial: %s updated", Instrument.getId());
    }

    public Instrument getInstrumentBySerial(int serial) {
        if (!InstrumentRepository.selectExistInstrumentById(serial)) {
            throw new IllegalStateException(String.format("Instrument with serial: %s doesn't exist", serial));
        }
        return InstrumentRepository.getById(serial);
    }


}
