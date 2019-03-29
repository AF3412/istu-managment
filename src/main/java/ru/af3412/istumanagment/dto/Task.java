package ru.af3412.istumanagment.dto;

import lombok.Data;

@Data
public class Task {

    private String projectName;
    private Integer id;
    private String name;
    private Integer employeeId;
    private String employeeName;
    private Long dateStart;
    private Long dateEnd;
    private String dateHumanStart;
    private String dateHumanEnd;
    private int daysCount;

}
