package com.project.elearning.rest;

import com.project.elearning.models.Courses;
import com.project.elearning.models.Reviews;
import com.project.elearning.services.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/")
public class ReviewsController {
    private ReviewService reviewService;

    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public List<Reviews> findreviews(){
        return reviewService.findall();
    }

    @GetMapping("/reviews/{reviewid}")
    public Reviews getEmployee(@PathVariable int reviewid) {

        Reviews thereview = reviewService.findbyID(reviewid);

        if (thereview == null) {
            throw new RuntimeException("review id not found - " + reviewid);
        }

        return thereview;
    }


    @PostMapping("/reviews")
    public Reviews addUser(@RequestBody Reviews thereviews){
        thereviews.setId(0L);
        Reviews reviews=reviewService.save(thereviews);

        return reviews;

    }

    @PutMapping("/reviews")
    public Reviews updateUser(@RequestBody Reviews reviews) {


        Reviews thereview = reviewService.save(reviews);

        return thereview;
    }

    @DeleteMapping("/reviews/{reviewid}")
    public String deleteUser(@PathVariable int reviewid) {

        Reviews reviews = reviewService.findbyID(reviewid);

        // throw exception if null

        if (reviews == null) {
            throw new RuntimeException("User id not found - " + reviewid);
        }

        reviewService.deleteById(reviewid);

        return "Deleted course id - " + reviewid;
    }
}
