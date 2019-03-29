package ru.af3412.istumanagment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CostDTO {

    private final String name;
    private final int cost;

}
