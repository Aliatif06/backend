package com.project.elearning.services;

import com.project.elearning.models.Enrollement;
import com.project.elearning.models.Reviews;
import com.project.elearning.repository.EnrollementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EnrollementService {

    public EnrollementRepository enrollementRepository;

    public EnrollementService(EnrollementRepository enrollementRepository){
        this.enrollementRepository=enrollementRepository;
    }

    public List<Enrollement> findall(){
        return enrollementRepository.findAll();
    }

    public Enrollement findByid(long theid){
        Optional<Enrollement> enroll= enrollementRepository.findById(theid);
        Enrollement theenroll=null;
        if(enroll.isPresent()){
            theenroll=enroll.get();
        }else{
            throw new RuntimeException("not found"+theid);
        }
        return theenroll;
    }

    public Enrollement save(Enrollement enroll){
        return enrollementRepository.save(enroll);
    }


    public void delete(Enrollement enoll){
        enrollementRepository.delete(enoll);
    }

    public void deleteById(long theId)
    {
        enrollementRepository.deleteById(theId);
    }

}
