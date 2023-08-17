package com.project.elearning.repository;

import com.project.elearning.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Courses,Integer> {

}
