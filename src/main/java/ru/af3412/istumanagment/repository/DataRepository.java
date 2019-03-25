package ru.af3412.istumanagment.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;
import ru.af3412.istumanagment.dto.Employee;
import ru.af3412.istumanagment.dto.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class DataRepository {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final static File DATA_BASE_EMPLOYEE = new File("target/employee.json");
    private final static File DATA_BASE_TASK = new File("target/task.json");

    public Employee[] getArrayEmployee() throws IOException {
        File tmp = getDataBase(DATA_BASE_EMPLOYEE);
        if (tmp.length() == 0) {
            return new Employee[0];
        } else {
            return OBJECT_MAPPER.readValue(getDataBase(DATA_BASE_EMPLOYEE), Employee[].class);
        }
    }

    public Task[] getArrayTask() throws IOException {
        File tmp = getDataBase(DATA_BASE_TASK);
        if (tmp.length() == 0) {
            return new Task[0];
        } else {
            return OBJECT_MAPPER.readValue(getDataBase(DATA_BASE_TASK), Task[].class);
        }
    }

    public Employee[] saveEmployee(Employee employee) throws IOException {
        List<Employee> listEmployee = new ArrayList<>(Arrays.asList(getArrayEmployee()));
        if (employee.getId() == null) {
            employee.setId(listEmployee.size() + 1);
            listEmployee.add(employee);
        } else {
            for (int i = 0; i < listEmployee.size(); i++) {
                Employee employee1 = listEmployee.get(i);
                if (employee1.getId().equals(employee.getId())) {
                    listEmployee.set(i, employee);
                    break;
                }
            }
        }
        FileUtils.writeStringToFile(DATA_BASE_EMPLOYEE, OBJECT_MAPPER.writeValueAsString(listEmployee), "UTF-8", false);
        return getArrayEmployee();
    }

    public Task[] saveTask(Task task) throws IOException {
        List<Task> listTask = new ArrayList<>(Arrays.asList(getArrayTask()));
        if (task.getId() == null) {
            task.setId(listTask.size() + 1);
            listTask.add(task);
        } else {
            for (int i = 0; i < listTask.size(); i++) {
                Task task1 = listTask.get(i);
                if (task1.getId().equals(task.getId())) {
                    listTask.set(i, task);
                    break;
                }
            }
        }
        FileUtils.writeStringToFile(DATA_BASE_TASK, OBJECT_MAPPER.writeValueAsString(listTask), "UTF-8", false);
        return getArrayTask();
    }

    private File getDataBase(File fileName) throws IOException {
        boolean dbExist = fileName.exists();
        if (!dbExist) {
            dbExist = fileName.createNewFile();
        }
        if (!dbExist) {
            throw new IOException();
        }
        return fileName;
    }

}
