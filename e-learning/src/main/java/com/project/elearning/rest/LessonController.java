package com.project.elearning.rest;

import com.project.elearning.models.Courses;
import com.project.elearning.models.Lesson;
import com.project.elearning.services.LessonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/")
public class LessonController {

    private LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/lessons")
    public List<Lesson> findUsers(){
        return lessonService.findall();
    }

    @GetMapping("/lessons/{lessonid}")
    public Lesson getEmployee(@PathVariable int lessonid) {

        Lesson thelesson = lessonService.findbyID(lessonid);

        if (thelesson == null) {
            throw new RuntimeException("Employee id not found - " + lessonid);
        }

        return thelesson;
    }


    @PostMapping("/lessons")
    public Lesson addUser(@RequestBody Lesson thelesson){
        thelesson.setId(0L);
        Lesson lesson=lessonService.save(thelesson);

        return lesson;

    }

    @PutMapping("/lessons")
    public Lesson updateUser(@RequestBody Lesson lesson) {


        Lesson thelesson = lessonService.save(lesson);

        return thelesson;
    }

    @DeleteMapping("/lessons/{lessonid}")
    public String deleteUser(@PathVariable int lessonid) {

        Lesson thelesson = lessonService.findbyID(lessonid);

        // throw exception if null

        if (thelesson == null) {
            throw new RuntimeException("User id not found - " + lessonid);
        }

        lessonService.deleteById(lessonid);

        return "Deleted course id - " + lessonid;
    }
}
