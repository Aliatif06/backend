package com.project.elearning.rest;

import com.project.elearning.models.Courses;
import com.project.elearning.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Courses> findCourses(){
        return courseService.findall();
    }

    @GetMapping("/courses/{courseid}")
    public Courses getCourse(@PathVariable int courseid) {

        Courses thecourse = courseService.findbyID(courseid);

        if (thecourse == null) {
            throw new RuntimeException("Employee id not found - " + courseid);
        }

        return thecourse;
    }


    @PostMapping("/courses")
    public Courses addCourse(@RequestBody Courses thecourse){
        thecourse.setCourseid(0L);
        Courses courses=courseService.save(thecourse);

        return courses;

    }

    @PutMapping("/courses")
    public Courses updateCourse(@RequestBody Courses courses) {


        Courses courses1 = courseService.save(courses);

        return courses1;
    }

    @DeleteMapping("/courses/{courseid}")
    public String deleteCourse(@PathVariable int courseid) {

        Courses thecourse = courseService.findbyID(courseid);

        // throw exception if null

        if (thecourse == null) {
            throw new RuntimeException("User id not found - " + courseid);
        }

        courseService.deleteById(courseid);

        return "Deleted course id - " + courseid;
    }
}
