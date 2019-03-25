package ru.af3412.istumanagment.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.af3412.istumanagment.dto.ganttGraph.ProjectDTO;
import ru.af3412.istumanagment.dto.ganttGraph.ValuesView;
import ru.af3412.istumanagment.repository.DataRepository;
import ru.af3412.istumanagment.utils.PrepareViewGanttUtil;

import java.io.IOException;

@Controller()
public class Index {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final DataRepository dataRepository;

    @Autowired
    public Index(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @GetMapping("/")
    public String initPage(Model model) {
        return "index";
    }

    @PostMapping("/getTasks")
    @ResponseBody
    public String getTasks() throws JsonProcessingException {
        ValuesView[] projects;
        try {
            projects = new PrepareViewGanttUtil().getProjectDTO(dataRepository.getArrayTask());
            System.out.println(projects);
        } catch (IOException e) {
            System.out.println("Empty task file");
            return "";
        }
        return OBJECT_MAPPER.writeValueAsString(projects);
    }

}
