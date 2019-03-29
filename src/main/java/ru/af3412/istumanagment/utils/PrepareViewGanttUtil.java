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
        tsk.sort(Comparator.comparing(Task::getDateStart));
        for (int i = 0; i < tsk.size(); i++) {
            Task task = tsk.get(i);
            Value value = new Value(task.getDateStart(), task.getDateEnd(), task.getEmployeeName(), "ganttRed");
            String name = i == 0 ? "Проект" : "";
            ValuesView valuesView = new ValuesView(name, task.getName(), new Value[]{value});
            valuesViews[i] = valuesView;
        }
        return valuesViews;
    }

}
