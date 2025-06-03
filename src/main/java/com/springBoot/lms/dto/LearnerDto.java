package com.springBoot.lms.dto;

import com.springBoot.lms.model.Learner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LearnerDto {

    private int id;
    private String name;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public List<LearnerDto> getLearnerDto(List<Learner> learner){
        List<LearnerDto> learnerDtoList = new ArrayList<>();
        learner.stream().forEach(learners -> {
            LearnerDto dto = new LearnerDto();
            dto.setId(learners.getId());
            dto.setName(learners.getName());
            dto.setUsername(learners.getUser().getUsername());
            learnerDtoList.add(dto);
        });

        return learnerDtoList;
    }
}
