package com.project.elearning.services;

import com.project.elearning.models.Courses;
import com.project.elearning.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    public CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Courses> findall(){
        return courseRepository.findAll();
    }

    public Courses findbyID(int theid){
        Optional<Courses> courses=courseRepository.findById(theid);
        Courses thecourse=null;
         if(courses.isPresent()){
            thecourse=courses.get();
         }else{
               throw new RuntimeException("not found"+theid);
         }
        return thecourse;
    }

    public Courses save(Courses courses){
        return courseRepository.save(courses);
    }


    public void delete(Courses courses){
        courseRepository.delete(courses);
    }

    public void deleteById(int theId)
    {
courseRepository.deleteById(theId);
    }


}
