package com.project.elearning.rest;

import com.project.elearning.models.Courses;
import com.project.elearning.models.Enrollement;
import com.project.elearning.repository.EnrollementRepository;
import com.project.elearning.services.EnrollementService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class EnrollementController {

    private EnrollementService enrollementService;

    public EnrollementController(EnrollementService enrollementService) {
        this.enrollementService = enrollementService;
    }

    @GetMapping("/enrolls")
    public List<Enrollement> findenrollement(){
        return enrollementService.findall();
    }

    @GetMapping("/enrolls/{enrollid}")
    public Enrollement getenrollement(@PathVariable int enrollid) {

        Enrollement enrollement = enrollementService.findByid(enrollid);

        if (enrollement == null) {
            throw new RuntimeException("enrollement id not found - " + enrollid);
        }

        return enrollement;
    }


    @PostMapping("/enrolls")
    public Enrollement addenrollement(@RequestBody Enrollement enroll){
        enroll.setId(0L);
        Enrollement enrollement=enrollementService.save(enroll);

        return enrollement;

    }

    @PutMapping("/enrolls")
        public Enrollement updateenroll(@RequestBody Enrollement enroll) {


        Enrollement enrollement = enrollementService.save(enroll);

        return enrollement;
    }

    @DeleteMapping("/enrolls/{enrollid}")
    public String deleteenrollement(@PathVariable int enrollid) {

        Enrollement enroll = enrollementService.findByid(enrollid);

        // throw exception if null

        if (enroll == null) {
            throw new RuntimeException("User id not found - " + enrollid);
        }

        enrollementService.deleteById(enrollid);

        return "Deleted course id - " + enrollid;
    }

}
