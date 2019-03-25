package ru.af3412.istumanagment.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.af3412.istumanagment.dto.Task;
import ru.af3412.istumanagment.repository.DataRepository;

import java.io.IOException;

@Controller
public class Project {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final DataRepository dataRepository;

    @Autowired
    public Project(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @GetMapping("/project")
    public String initPage(Model model) {
        try {
            model.addAttribute("taskList", dataRepository.getArrayTask());
        } catch (IOException e) {
            System.out.println("Empty task file");
        }

        try {
            model.addAttribute("listEmployee", dataRepository.getArrayEmployee());
        } catch (IOException e) {
            System.out.println("Empty employee file");
        }

        return "project";
    }

    @PostMapping(value = "/saveTask")
    @ResponseBody
    public String saveBooking(@RequestBody Task task) throws IOException {
        return OBJECT_MAPPER.writeValueAsString(dataRepository.saveTask(task));
    }

}
