package ru.af3412.istumanagment.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.af3412.istumanagment.dto.CostDTO;
import ru.af3412.istumanagment.dto.Employee;
import ru.af3412.istumanagment.dto.Task;
import ru.af3412.istumanagment.dto.ganttGraph.ValuesView;
import ru.af3412.istumanagment.repository.DataRepository;
import ru.af3412.istumanagment.utils.PrepareViewGanttUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller()
public class Index {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final DataRepository dataRepository;

    @Autowired
    public Index(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @GetMapping("/")
    public String initPage() {
        return "index";
    }

    @PostMapping("/getTasks")
    @ResponseBody
    public String getTasks() throws JsonProcessingException {
        ValuesView[] projects;
        try {
            projects = new PrepareViewGanttUtil().getProjectDTO(dataRepository.getArrayTask());
        } catch (IOException e) {
            System.out.println("Empty task file");
            return "";
        }
        return OBJECT_MAPPER.writeValueAsString(projects);
    }

    @PostMapping("/getCost")
    @ResponseBody
    public String getCost() throws IOException {
        Employee[] employees = dataRepository.getArrayEmployee();
        Map<Employee, Integer> costValues = new HashMap<>(employees.length);
        Task[] tasks = dataRepository.getArrayTask();
        List<CostDTO> projectCost = new ArrayList<>(10);

        for (Task task : tasks) {
            Employee employee = getEmployer(task.getEmployeeId(), employees);
            costValues.merge(employee, employee.getHourRate() * task.getDaysCount(), Integer::sum);
            projectCost.add(new CostDTO("Этап " + task.getName(), employee.getHourRate() * task.getDaysCount()));
        }
        int sum = 0;
        for (Map.Entry<Employee, Integer> entry : costValues.entrySet()) {
            projectCost.add(new CostDTO("З/п " + entry.getKey().getName(), entry.getValue()));
            sum += entry.getValue();
        }
        projectCost.add(new CostDTO("Общая стоимость проекта", sum));
        return OBJECT_MAPPER.writeValueAsString(projectCost);
    }

    private Employee getEmployer(int employerId, Employee[] employees) {
        for (Employee employee : employees) {
            if (employee.getId() == employerId) {
                return employee;
            }
        }
        return new Employee();
    }


}
