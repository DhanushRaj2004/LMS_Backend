package com.springBoot.lms.controller;

import com.springBoot.lms.model.CModule;
import com.springBoot.lms.service.CModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/module")
public class CModuleController {

    @Autowired
    private CModuleService cModuleService;

    /*
    * AIM : to add module in course with the help of course_id
    * PATH : /api/module/add/{course_id}
    * Method : POST
    * Input : Module as RequestBody,Course_id as PathVariable
    * Output : Module details added to course details by course_id
    */

    @PostMapping("/add/{course_id}")
    public CModule addModule(@RequestBody CModule module, @PathVariable int course_id){
        return cModuleService.addModule(module,course_id);
    }

    /*
    * AIM : Get module by course_id
    * PATH : /api/module/course?course_id=2
    * METHOD : GET
    * Input : course_id as RequestParam
    * Response : Module
    */

    @GetMapping("/course")
    public ResponseEntity<?> getModuleByCourse(@RequestParam int courseId){
        return ResponseEntity.status(HttpStatus.OK).body(cModuleService.getModuleByCourse(courseId));
    }
}
