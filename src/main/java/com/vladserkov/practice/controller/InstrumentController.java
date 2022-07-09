package com.vladserkov.practice.controller;


import com.vladserkov.practice.domain.Instrument;
import com.vladserkov.practice.exceptionhandler.exception.SerialNotFoundException;
import com.vladserkov.practice.service.InstrumentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("instruments")
public class InstrumentController {
    private InstrumentService InstrumentService;


    @GetMapping
    public List<Instrument> findAllInstruments() {
        return InstrumentService.findAllInstruments();
    }

    @PostMapping
    public String save(@RequestBody Instrument instrument) throws SerialNotFoundException {
        return InstrumentService.save(instrument);
    }

    @DeleteMapping("/{serial}")
    public String deleteById(@PathVariable int serial) throws SerialNotFoundException {
        return InstrumentService.deleteById(serial);
    }
    @PutMapping("/{serial}")
    public String update(@PathVariable int serial, @RequestBody Instrument instrument ) throws SerialNotFoundException {
        return InstrumentService.update(serial, instrument);
    }

    @GetMapping("/{serial}")
    public Instrument getInstrumentBySerial(@PathVariable int serial) {
        return InstrumentService.getInstrumentBySerial(serial);
    }
}
