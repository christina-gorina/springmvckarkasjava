package springmvckarkas.service;

import org.springframework.stereotype.Service;
import springmvckarkas.model.Applicant;
import springmvckarkas.repository.ApplicantRepository;

import java.math.BigDecimal;

@Service
public class ApplicantService {
    private final ApplicantRepository repository;

    public ApplicantService(ApplicantRepository repository){
        this.repository = repository;
    }

    public Applicant get(BigDecimal id){
        return repository.get(id);
    }
}
