package ru.af3412.istumanagment.dto.ganttGraph;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Value {

    private Long from;
    private Long to;
    private String label;
    private String customClass;

    /*from: 1320192000000,
    to: 1322401600000,
    label: "Requirement Gathering",
    customClass: "ganttRed"*/
}
