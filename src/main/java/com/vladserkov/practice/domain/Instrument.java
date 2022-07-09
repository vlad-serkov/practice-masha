package com.vladserkov.practice.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class Instrument {
    private int id;
    private String name;
    private String unit;
    private int scale;
    private int sensitivity;
    private int max_measure;
}
