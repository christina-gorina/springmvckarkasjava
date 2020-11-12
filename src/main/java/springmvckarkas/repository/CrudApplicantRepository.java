package springmvckarkas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import springmvckarkas.model.Applicant;

import java.math.BigDecimal;

@Transactional(readOnly = true)
public interface CrudApplicantRepository extends JpaRepository<Applicant, BigDecimal> {

}
