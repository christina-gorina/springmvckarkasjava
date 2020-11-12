package springmvckarkas.repository;

import org.springframework.stereotype.Repository;
import springmvckarkas.model.Applicant;

import java.math.BigDecimal;

@Repository
public class ApplicantRepositoryImpl implements ApplicantRepository{
private final CrudApplicantRepository crudApplicantRepository;

    public ApplicantRepositoryImpl(CrudApplicantRepository crudApplicantRepository) {
        this.crudApplicantRepository = crudApplicantRepository;
    }

    @Override
    public Applicant get(BigDecimal id) {
        return crudApplicantRepository.findById(id).orElse(null);
    }
}
