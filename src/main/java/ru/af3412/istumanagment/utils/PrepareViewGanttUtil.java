package ru.af3412.istumanagment.utils;

import ru.af3412.istumanagment.dto.Task;
import ru.af3412.istumanagment.dto.ganttGraph.Value;
import ru.af3412.istumanagment.dto.ganttGraph.ValuesView;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PrepareViewGanttUtil {

    public ValuesView[] getProjectDTO(Task[] tasks) {
        ValuesView[] valuesViews = new ValuesView[tasks.length];
        List<Task> tsk = Arrays.asList(tasks);
//        tsk.sort(Comparator.comparing(Task::getDateStart));
        for (int i = 0; i < tsk.size(); i++) {
            Task task = tsk.get(i);
            Value value = new Value(task.getDateStart(), task.getDateEnd(), " ", "ganttRed");
            String name = i == 0 ? "Проект" : "";
            ValuesView valuesView = new ValuesView(name, task.getName(), new Value[]{value});
            valuesViews[i] = valuesView;
        }
        return valuesViews;
    }


    /*
    var demoSource = [
        {
            name: "Проект",
            desc: "Анализ",
            values: [{
                from: 1320192000000,
                to: 1322401600000,
                label: "Requirement Gathering",
                customClass: "ganttRed"
            }]
        }, {
            desc: "Сбор информации",
            values: [{
                from: 1322611200000,
                to: 1323302400000,
                label: "Scoping",
                customClass: "ganttRed"
            }]
        }, {
            desc: "Создание прототипа",
            values: [{
                from: 1322611200000,
                to: 1323302400000,
                label: "Scoping",
                customClass: "ganttRed"
            }]
        } ];
        */


}
