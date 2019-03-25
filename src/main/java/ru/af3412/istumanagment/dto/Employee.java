package ru.af3412.istumanagment.dto;

import lombok.Data;

@Data
public class Employee {
    private Integer id;
    private String name;
    private Integer hourRate;
    private String position;
}
