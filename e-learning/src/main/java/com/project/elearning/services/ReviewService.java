package com.project.elearning.services;

import com.project.elearning.models.Reviews;
import com.project.elearning.models.Reviews;
import com.project.elearning.repository.LessonRepository;
import com.project.elearning.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ReviewService {
    public ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Reviews> findall(){
        return reviewRepository.findAll();
    }

    public Reviews findbyID(long theid){
        Optional<Reviews> reviews= reviewRepository.findById(theid);
        Reviews thereview=null;
        if(reviews.isPresent()){
            thereview=reviews.get();
        }else{
            throw new RuntimeException("not found"+theid);
        }
        return thereview;
    }

    public Reviews save(Reviews reviews){
        return reviewRepository.save(reviews);
    }


    public void delete(Reviews review){
        reviewRepository.delete(review);
    }

    public void deleteById(long theId)
    {
        reviewRepository.deleteById(theId);
    }
}

