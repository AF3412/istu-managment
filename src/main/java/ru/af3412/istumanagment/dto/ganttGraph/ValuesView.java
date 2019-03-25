package ru.af3412.istumanagment.dto.ganttGraph;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValuesView {

    private String name;
    private String desc;
    private Value[] values;

}
