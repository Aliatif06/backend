package com.project.elearning.services;

import com.project.elearning.models.Courses;
import com.project.elearning.models.Lesson;
import com.project.elearning.repository.CourseRepository;
import com.project.elearning.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LessonService {
    public LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> findall(){
        return lessonRepository.findAll();
    }

    public Lesson findbyID(long theid){
        Optional<Lesson> lesson= lessonRepository.findById(theid);
        Lesson thelesson=null;
        if(lesson.isPresent()){
            thelesson=lesson.get();
        }else{
            throw new RuntimeException("not found"+theid);
        }
        return thelesson;
    }

    public Lesson save(Lesson lesson){
        return lessonRepository.save(lesson);
    }


    public void delete(Lesson lesson){
        lessonRepository.delete(lesson);
    }

    public void deleteById(long theId)
    {
        lessonRepository.deleteById(theId);
    }
}
